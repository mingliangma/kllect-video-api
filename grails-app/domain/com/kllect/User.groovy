package com.kllect

import org.bson.types.ObjectId


class User {
    ObjectId id
    String email
    String uid
    static hasMany = [interests:Topic]
    static mapping = {
        uid index:true, indexAttributes: [unique:true, dropDups:true]
        interests batchSize: 10
    }
}
