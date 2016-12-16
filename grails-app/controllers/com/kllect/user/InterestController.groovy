package com.kllect.user

import com.google.gson.JsonSyntaxException
import com.kllect.User
import com.kllect.auth.JWTPayload
import com.kllect.Topic
import org.bson.types.ObjectId

class InterestController {

    def verifyService

    def saveUserInterests() {
        String token = request.JSON.token
        def topicIds = request.JSON.topicIds

        log.info("Token: "+token)
        log.info("topicIds: "+topicIds)

        println "Token: "+token
        println "topicIds: "+topicIds

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

        User u = saveNewUser(jwtPayload)
        try {
            saveInterests(topicIds, u)
        }catch (InvalidUserInputException e){
                log.error("InvalidUserInputException: "+e.message)
                response.status= 400
                render(view:'authFailed', model:[error: e.getMessage()])
                return
            }
        if (!u.save(flush: true)) {
            u.errors.allErrors.each {
                log.error(it)
            }
        }
    }


    def listAllTopics() {
        def topics = Topic.findAll()
        [topics: topics]

    }

    private User saveNewUser(JWTPayload jwtPayload) {
        User u = User.findByUid(jwtPayload.user_id)
        if (u == null) {
            u = new User(email: jwtPayload.email, uid: jwtPayload.user_id)
        }
        u
    }

    private void saveInterests(interests, User u) {
        for (String topic : interests) {

            if (ObjectId.isValid(topic)) {
                log.info( "topic: " + topic)
                Topic t = Topic.get(new ObjectId(topic))
                if (t == null) {
                    log.error("Topic does not exist")
                    throw new InvalidUserInputException("Topic "+topic+" does not exist")
                } else {
                    if (hasUserSelectedTopic(u, t)) {
                        continue
                    }
                }
                u.addToInterests(t)
            }else{
                throw new InvalidUserInputException("Invalid topic Id ");
            }
        }
    }

    private boolean hasUserSelectedTopic(User u, Topic t) {

        for (Topic topic : u.interests) {
            if (topic.name == t.name) {
                return true
            }
        }
        return false
    }
}
