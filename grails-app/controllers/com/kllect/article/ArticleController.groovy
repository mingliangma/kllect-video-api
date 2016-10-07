package com.kllect.article

import com.kllect.Article
import org.bson.types.ObjectId

class ArticleController {

    def listVideoArticles() {
        def articles = Article.findAllByIs_video(true, [max: 20, sort: "parse_date", order: "desc"])
        [article: articles, articleCount: articles.size()]
    }

    def listVideoArticlesBySiteName(){
        String siteName = params.site
        def articles = Article.findAllBySite_name(siteName, [max: 30, sort: "parse_date", order: "desc"])
        [article: articles]
    }

    def listVideoByInterest(){
        String interest = params.interest
        println interest
        def articles = Article.findAllByTags("smartphones", [max: 40, sort: "parse_date", order: "desc"])
        [article: articles]
    }

    def getArticle(){
        def article = Article.get(new ObjectId(params.id))
        [article:article]
    }

    def listVideoByTag(){
        String tag = params.tag
        params.sort = "tagged_date"
        params.order = "desc"
        params.max = Math.min(params.max ?params.max.toInteger(): 10, 100)
        def articles = Article.findAllByTags(tag, params)
        int offset = params.offset!=null?params.offset.toInteger()+params.max.toInteger():params.max.toInteger()
        String nextPagePath = "articles/tag/"+tag+"?offset="+offset
        [articles: articles, articleCount: articles.size(), nextPagePath:nextPagePath]
    }

    def index(Integer max){
        params.max = Math.min(max ?: 10, 100)
        respond Article.list(params), model:[articleCount: Article.count()]
    }
}
