package com.kllect.article

import com.kllect.Article
import com.kllect.VideoArticle
import grails.converters.*
import kllect2.Ming
import org.bson.types.ObjectId

class ArticleController {
    def listVideoArticles() {
        def articles = Article.findAllByIs_video(true)
//        String idString = "56ec73f451ac840baf4fea4e"
//        def articles = Article.get(new ObjectId("56ec73f451ac840baf4fea4e"))
//        respond articles
        [article: articles]
    }

    def getArticle(){
        def article = Article.get(new ObjectId(params.id))
        [article:article]
    }
}
