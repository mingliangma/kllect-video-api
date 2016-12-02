package com.kllect.user

import com.google.gson.JsonSyntaxException
import com.kllect.auth.JWTPayload
import org.springframework.validation.Errors

class InterestController {

    def verifyService
    def saveUserInterests() {
        String token = request.JSON.token
        JWTPayload jwtPayload;
        try {
            jwtPayload = verifyService.verifyToken(token)
        }catch(JsonSyntaxException e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 400
            render(view:'authFailed', model:[error: "Token is corrupted"])
            return
        }catch (Exception e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 401
            render(view:'authFailed', model:[error: e.getMessage()])
            return
        }


    }
}
