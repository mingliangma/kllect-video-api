package com.kllect.user

import com.kllect.User
import grails.rest.*
import grails.converters.*

class UserController {

    def getUserProfile(){
        User user = User.findByUid(params.uid)
        if (user == null){
            throw new NoUserFoundException("uid not found: "+params.uid)
        }
        [user:user]
    }
}
