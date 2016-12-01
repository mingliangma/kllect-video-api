package com.kllect.user

import com.kllect.auth.JWTPayload
import grails.rest.*
import grails.converters.*

class InterestController {

    def verifyService
    def saveUserInterests() {
        String token = request.JSON.token
        JWTPayload jwtPayload;
        try {
            jwtPayload = verifyService.verifyToken(token)
        }catch (Exception e){
            log.error(e.message)
            response.status= 400
            Map resp = [message: e.message]
        }



    }
}
