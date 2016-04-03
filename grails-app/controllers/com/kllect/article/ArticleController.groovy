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

    def getArticle(){
        def article = Article.get(new ObjectId(params.id))
        [article:article]
    }
}
