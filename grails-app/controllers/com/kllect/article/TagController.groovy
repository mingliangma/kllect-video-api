package com.kllect.article

import com.kllect.Article
import com.kllect.Tag
import com.mongodb.DBCollection
import grails.rest.*
import grails.converters.*
import org.springframework.data.mongodb.core.query.Criteria
import org.springframework.data.mongodb.core.query.Query

class TagController {

    def listAllTags() {
        def tags = Tag.findAll()
        [tag: tags]
    }


}
