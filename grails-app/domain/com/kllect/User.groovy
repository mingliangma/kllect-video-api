package com.kllect

import org.bson.types.ObjectId


class User {
    ObjectId id
    String email
    String uid
    Date birthdate
    String name
    String gender
    String profilePicUrl
    String city
    String country

    static hasMany = [interests:Topic]
    static mapping = {
        uid index:true, indexAttributes: [unique:true, dropDups:true]
        interests batchSize: 10

    }
    static constraints = {
        birthdate nullable: true
        name nullable: true
        gender nullable: true
        profilePicUrl nullable: true
        city nullable: true
        country nullable: true
    }
}
