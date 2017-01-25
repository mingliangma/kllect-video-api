package com.kllect.user

import com.google.gson.JsonSyntaxException
import com.kllect.DateUtil
import com.kllect.User
import com.kllect.auth.JWTPayload
import grails.rest.*
import grails.converters.*

class UserController {
    def verifyService
    def getUserProfile(){
        User user = User.findByUid(params.uid)
        if (user == null){
            throw new NoUserFoundException("uid not found: "+params.uid)
        }
        [user:user]
    }

    def saveUserProfile(){
        try {
            String token = request.JSON.token
            String name = request.JSON.name
            String gender = request.JSON.gender
            String profilePicUrl = request.JSON.profilePicUrl
            String city = request.JSON.city
            String country = request.JSON.country

            JWTPayload jwtPayload

            jwtPayload = verifyService.verifyToken(token)

            User user = User.findByUid(jwtPayload.user_id)
            if (user == null) {
                user = new User(email: jwtPayload.email, uid: jwtPayload.user_id, name: name, gender: gender, profilePicUrl: profilePicUrl, city:city, country:country)
            }else{
                user.setProfilePicUrl(profilePicUrl)
                user.setName(name)
                user.setGender(gender)
                user.setCity(city)
                user.setCountry(country)
                user.setEmail(jwtPayload.email)
            }

//            user.save(flush: true)

            if (!user.save(flush: true)) {
                user.errors.allErrors.each {
                    log.error(it)
                    response.status=500
                    render(view:'error', model:[error: it])
                    return
                }
            }


            render(view:'getUserProfile', model: [user:user])
        }catch(JsonSyntaxException e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 400
            render(view:'/exception', model:[error: "Token is corrupted"])
        }catch (InvalidUserInputException e){
            log.error("InvalidUserInputException: "+e.message)
            response.status= 400
            render(view:'/exception', model:[error: e.getMessage()])
        }catch (Exception e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 401
            render(view:'/exception', model:[error: e.getMessage()])
        }
    }

    def validateSaveUserProfile( reqeust){
        if (!reqeust.JSON.token){
            throw new InvalidUserInputException("Token not found. Please provide access token")
        }
    }
}
