package com.kllect.auth

import com.google.gson.JsonSyntaxException
import com.kllect.user.InvalidUserInputException

class AuthController {
    def authService

    def verifyToken() {
        String token = request.JSON.token
        JWTPayload jwtPayload
        try {
            jwtPayload = authService.verifyToken(token)

        }catch(JsonSyntaxException e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 400
            render(view:'authFailed', model:[error: "Token is corrupted"])
        }catch (InvalidUserInputException e){
            log.error("InvalidUserInputException: "+e.message)
            response.status= 400
            render(view:'authFailed', model:[error: e.getMessage()])
        }catch (Exception e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 401
            render(view:'authFailed', model:[error: e.getMessage()])
        }

        println jwtPayload
        render(view:'verifyToken', model: [jwtPayload: jwtPayload])
    }
}
