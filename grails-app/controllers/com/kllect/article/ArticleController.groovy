package com.kllect.article

import com.google.gson.JsonSyntaxException
import com.kllect.Article
import com.kllect.ArticleTopicRelevancy
import com.kllect.HiddenStatus
import com.kllect.Topic
import com.kllect.User
import com.kllect.auth.JWTPayload
import com.kllect.auth.KllectError
import org.apache.commons.lang3.ArrayUtils
import org.bson.Document
import org.bson.types.ObjectId

import java.util.regex.Pattern

class ArticleController {

    def authService
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

    def listVideoByTag() {

        String tag = params.tag
        Map findParams = setParams()

        def c = Article.createCriteria()
        def articles
        if (tag == "apps") {
            articles = c.list(findParams) {
                eq("hidden_status", HiddenStatus.NOT_HIDDEN.status)
                or {
                    eq("raw_tags", tag)
                    ilike("title", "% app %")
                    ilike("title", "% apps %")
                }
            }
        } else if (tag == "apple"){
            List<Map> and = []
            and.add([extraction_method: "youtube_channel"])
            and.add([hidden_status: HiddenStatus.NOT_HIDDEN.status])
            and.add(
                    ['$or': [
                            ["raw_tags.0": Pattern.compile('^apple$', Pattern.CASE_INSENSITIVE)],
                            [ "raw_tags.1": Pattern.compile('^apple$', Pattern.CASE_INSENSITIVE) ],
                            [ "raw_tags.2": Pattern.compile('^apple$', Pattern.CASE_INSENSITIVE) ],
                            [ "raw_tags.3": Pattern.compile('^apple$', Pattern.CASE_INSENSITIVE) ],
                            [ "raw_tags.4": Pattern.compile('^apple$', Pattern.CASE_INSENSITIVE) ],
                            [ "raw_tags.5": Pattern.compile('^apple$', Pattern.CASE_INSENSITIVE) ]
                    ]
                    ]
            )

            Map query = ['$and': and
                        ]

            articles = Article.collection.find(query).skip(findParams.offset).sort(["publish_date": -1]).limit(findParams.max).asList()

        }else if (tag == "snapchat"){
            List<Map> and = []
            and.add([extraction_method: "youtube_channel"])
            and.add([hidden_status: HiddenStatus.NOT_HIDDEN.status])
            and.add(
                    ['$or': [
                            ["raw_tags.0": Pattern.compile('^snapchat$', Pattern.CASE_INSENSITIVE)],
                            ["raw_tags.1": Pattern.compile('^snapchat$', Pattern.CASE_INSENSITIVE)],
                            ["raw_tags.2": Pattern.compile('^snapchat$', Pattern.CASE_INSENSITIVE)],
                            ["raw_tags.3": Pattern.compile('^snapchat$', Pattern.CASE_INSENSITIVE)],
                            ["raw_tags.4": Pattern.compile('^snapchat$', Pattern.CASE_INSENSITIVE)],
                            ["raw_tags.5": Pattern.compile('^snapchat$', Pattern.CASE_INSENSITIVE)]
                    ]
                    ]
            )
            Map query = ['$and': and
            ]

            articles = Article.collection.find(query).skip(findParams.offset).sort(["publish_date": -1]).limit(findParams.max).asList()

        }else{
            articles = c.list(findParams) {
                eq("hidden_status", HiddenStatus.NOT_HIDDEN.status)
                eq("tags", tag)
            }

        }


        int offset = findParams.offset + findParams.max
        String nextPagePath = "articles/topic/"+tag+"?offset="+offset

        if (articles.get(0) instanceof Article){
            [articles: articles, articleCount: articles.size(), nextPagePath:nextPagePath]
        }else{
            render(view:'listVideoByTagDocumentType', model:[articles: articles, articleCount: articles.size(), nextPagePath:nextPagePath])
        }

    }

    private Map setParams() {
        String sort = "publish_date"
        String order = "desc"
        int max = Math.min(params.max ? params.max.toInteger() : 10, 100)
        int offset = params.offset?params.offset.toInteger():0

        return [sort: sort, order: order, max:max, offset:offset]
    }

    //When videos are unavailable or corrupted to watch, client will call this method to mark video to be corrupted.
    def markCorruptedVideo(){

        def validation = markCorruptedVideoValidation(params, request.JSON.token)

        JWTPayload jwtPayload
        if (validation instanceof KllectError){
            KllectError error = (KllectError) validation
            response.status= error.httpStatus
            render(view:'error', model:[error: error.message])
            return
        }

        String articleId = params.id

        Map newDoc = ['$set': ["hidden_status": HiddenStatus.CORRUPT.status]]
        Map query = ["_id": new ObjectId(articleId)]
        Article.collection.update(query, newDoc)

        render(view:'success')
    }

    def listVideoByRecommending(){

        String uid = params.uid

        def validation = recommendingArticleValidation(params)
        if (validation instanceof KllectError){
            KllectError error = (KllectError) validation
            response.status= error.httpStatus
            render(view:'error', model:[error: error.message])
            return
        }

        User user = User.findByUid(uid)

        def tags = getInterestNamesFromUser(user)
        Map findParams = setParams()

        def c = Article.createCriteria()
        def articles = c.list(findParams) {
            eq("hidden_status", HiddenStatus.NOT_HIDDEN.status)
            'in'("tags", tags)
        }

        int offset = findParams.offset + findParams.max
        String nextPagePath = "articles/recommending?offset="+offset+"&uid="+uid

        render(view:'listVideoByTag', model:[articles: articles, articleCount: articles.size(), nextPagePath:nextPagePath])
    }

    def getInterestNamesFromUser(User user){
        def topics = []
        for (Topic topic: user.interests){
            topics.add(topic.getName())
        }
        return topics
    }

    void countTags(List<Article> articles){
        Map c = [:]
        for (Article a: articles){

            for (String t: a.tags){
                if (c.containsKey(t)){
                    int count = c.get(t)
                    c.put(t, count+1)
                }else{
                    c.put(t, 1)
                }
            }
        }
        c.each{ k, v -> println "${k}:${v}" }
    }

    def index(Integer max){
        params.max = Math.min(max ?: 10, 100)
        respond Article.list(params), model:[articleCount: Article.count()]
    }

    def setArticleRelevancy(){
        println "setArticleRelevancy() starts..."
        println "token"
        String token = request.JSON.token
        def validation = relevancyArticleValidation(params, token)

        String articleId = params.id
        String topicName = params.topic
        String isRelevant = params.isRelevant

        println "token: "+token
        println "articleId: "+articleId
        println "topicName: "+topicName
        println "isRelevant: "+isRelevant

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
                isRelevant: isRelevant == "true",
                updated_at: new Date()
        )
        if (!rel.save(flush: true)) {
            rel.errors.allErrors.each {
                log.error(it)
                response.status=500
                render(view:'error', model:[error: it])
                return
            }
        }

        if(user.email == "mingliang.ma@gmail.com"){
            if (isRelevant == "false"){
                Map newDoc = ['$set': ["hidden_status": HiddenStatus.UNRELATED_CATEGORY.status]]
                Map query = ["_id": article.id]
                Article.collection.update(query, newDoc)
            }
        }
        render(view:'success')
    }

    def recommendingArticleValidation(params){
        if (params.uid == null){
            return new KllectError("firebase uid not exist", "", 400)
        }

        User user = User.findByUid(params.uid)
        if (user == null){
            return new KllectError("The user does not exists", "", 401)
        }
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
            jwtPayload = authService.verifyToken(token)
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

    def markCorruptedVideoValidation(params, String token){
        if (params.id == null){
            return new KllectError("article id not exist", "", 400)
        }

        JWTPayload jwtPayload;
        try {
            jwtPayload = authService.verifyToken(token)
        }catch(JsonSyntaxException e){
            log.error("JsonSyntaxException: "+e.message)
            return new KllectError("Token is corrupted", "", 400)
        }catch (Exception e){
            log.error("JsonSyntaxException: "+e.message)
            return new KllectError(e.getMessage(), "", 401)
        }
        return jwtPayload
    }

}
