package com.kllect.article

import com.google.gson.JsonSyntaxException
import com.kllect.Article
import com.kllect.ArticleTopicRelevancy
import com.kllect.Topic
import com.kllect.User
import com.kllect.auth.JWTPayload
import com.kllect.auth.KllectError
import org.bson.types.ObjectId

class ArticleController {

    def verifyService
    def listVideoArticles() {
        def articles = Article.findAllByIs_video(true, [max: 20, sort: "parse_date", order: "desc"])
        [article: articles, articleCount: articles.size()]
    }

    def listVideoArticlesBySiteName(){
        String siteName = params.site
        def articles = Article.findAllBySite_name(siteName, [max: 30, sort: "parse_date", order: "desc"])
        [article: articles]
    }

    def getArticle(){
        def article = Article.get(new ObjectId(params.id))
        [article:article]
    }

    def listVideoByTag(){
        String tag = params.tag
        params.sort = "publish_date"
        params.order = "desc"
        params.max = Math.min(params.max ?params.max.toInteger(): 10, 100)
        def articles = Article.findAllByTags(tag, params)
        int offset = params.offset!=null?params.offset.toInteger()+params.max.toInteger():params.max.toInteger()
        String nextPagePath = "articles/topic/"+tag+"?offset="+offset
        [articles: articles, articleCount: articles.size(), nextPagePath:nextPagePath]
    }

    def index(Integer max){
        params.max = Math.min(max ?: 10, 100)
        respond Article.list(params), model:[articleCount: Article.count()]
    }

    def setArticleRelevancy(){
        String token = request.JSON.token
        def validation = relevancyArticleValidation(params, token)

        String articleId = params.id
        String topicName = params.topic
        String isRelevant = params.isRelevant


        JWTPayload jwtPayload
        if (validation instanceof KllectError){
            KllectError error = (KllectError) validation
            response.status= error.httpStatus
            render(view:'error', model:[error: error.message])
            return
        }else if (validation instanceof JWTPayload){
            jwtPayload = validation as JWTPayload
        }

        Article article = Article.get(new ObjectId(articleId))
        Topic topic = Topic.findByName(topicName)
        User user = User.findByUid(jwtPayload.user_id)
        ArticleTopicRelevancy rel = new ArticleTopicRelevancy(
                articleId: article.id,
                articleTitle: article.title,
                topicId: topic.id,
                topicName: topic.name,
                userId: user.id,
                isRelevant: isRelevant == "true"
        )
        if (!rel.save(flush: true)) {
            rel.errors.allErrors.each {
                log.error(it)
            }
        }
        render(view:'../success')
    }


    def relevancyArticleValidation(params, String token){

        if (params.id == null){
            return new KllectError("article id not exist", "", 400)
        }

        if (params.topic == null){
            return new KllectError("topicName not exist", "", 400)
        }

        if (params.isRelevant == null){
            return new KllectError("isRelevant not exist", "", 400)
        }

        if (!(params.isRelevant == "true" || params.isRelevant == "false")){
            return new KllectError("isRelevant can only take value true or false", "", 400)
        }

        if (!ObjectId.isValid(params.id)){
            return new KllectError("Invalid article id", "", 400)
        }

        if (Topic.findByName(params.topic) == null){
            return new KllectError("The topic does not exists", "", 400)
        }

        JWTPayload jwtPayload;
        try {
            jwtPayload = verifyService.verifyToken(token)
        }catch(JsonSyntaxException e){
            log.error("JsonSyntaxException: "+e.message)
            return new KllectError("Token is corrupted", "", 400)
        }catch (Exception e){
            log.error("JsonSyntaxException: "+e.message)
            return new KllectError(e.getMessage(), "", 401)
        }

        User user = User.findByUid(jwtPayload.user_id)
        if (user == null){
            return new KllectError("The user does not exists", "", 401)
        }
        return jwtPayload
    }

}
