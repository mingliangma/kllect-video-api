package com.kllect.article

import com.kllect.Tag

//to be deprecated
class TagController {

    def listAllTags() {
        def tags = Tag.findAll()
        [tag: tags]
    }


}
