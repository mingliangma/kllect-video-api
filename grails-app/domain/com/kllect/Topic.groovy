package com.kllect

import org.bson.types.ObjectId

class Topic {
    ObjectId id
    String name
    String display_name
    static hasMany = [users:User]
    static belongsTo = User
    static mapping = {
        name index:true, indexAttributes: [unique:true, dropDups:true]
    }
}
