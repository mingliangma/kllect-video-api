package com.kllect.article

import com.kllect.Article
import com.kllect.VideoArticle
import grails.converters.*
import kllect2.Ming
import org.bson.types.ObjectId

class ArticleController {
    def listVideoArticles() {
        def articles = Article.findAllByIs_video(true, [max: 30, sort: "parse_date", order: "desc"])
        [article: articles]
    }

    def listVideoArticlesBySiteName(){
        String siteName = params.site
        def articles = Article.findAllBySite_name(siteName, [max: 30, sort: "parse_date", order: "desc"])
        [article: articles]
    }

    def listVideoByInterest(){
        String interest = params.interest
        println interest
        def articles = Article.findAllByExtraction_method(interest, [max: 40, sort: "parse_date", order: "desc"])
        [article: articles]
    }

    def getArticle(){
        def article = Article.get(new ObjectId(params.id))
        [article:article]
    }
}
