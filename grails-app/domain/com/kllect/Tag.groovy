package com.kllect

import org.bson.types.ObjectId

class Tag {
    ObjectId id
    String name
    long count
    String updated_at

    static mapping = {
        name index:true, indexAttributes: [unique:true, dropDups:true]
    }
    static constraints = {
    }
}
