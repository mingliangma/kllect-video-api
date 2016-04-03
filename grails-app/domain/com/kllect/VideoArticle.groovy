package com.kllect

import org.bson.types.ObjectId

class VideoArticle {
    ObjectId id
    String siteName
    String description
    static mapWith = "mongo"
}
