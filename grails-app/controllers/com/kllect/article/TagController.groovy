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

//        new Tag(name: "aaaa", count:1, updated_at: new Date().toString()).save(flush:true);
//
//        def tags = Tag.findAllByName("aaaa")
//        [tag: tags]

        def tags = Tag.findAll()
        [tag: tags]
    }


}
