package com.kllect.user

import com.google.gson.JsonSyntaxException
import com.kllect.DateUtil
import com.kllect.User
import com.kllect.auth.JWTPayload
import com.kllect.Topic
import org.bson.types.ObjectId

class InterestController {

    def authService

    def saveUserInterests() {
        String token = request.JSON.token
        def topicIds = request.JSON.topicIds
        String birthdate = request.JSON.birthdate

        println "Token: "+token
        println "topicIds: "+topicIds
        println "birthdate: "+birthdate
        println "request.JSON: "+request.JSON

        JWTPayload jwtPayload
        try {
            jwtPayload = authService.verifyToken(token)

            User u = User.findByUid(jwtPayload.user_id)
            if (!u) {
                if (birthdate) {
                    u = saveNewUser(jwtPayload, DateUtil.parseStringToDate(birthdate))
                } else {
                    u = saveNewUser(jwtPayload)
                }
            }

            addUserInterests(u, topicIds)
            u.save(flush: true)
            render(view:'saveUserInterests')
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
    }

    def updateUserInterests(){

        String token = request.JSON.token
        List<String> topicIds = request.JSON.topicIds

        JWTPayload jwtPayload
        try {
            jwtPayload = authService.verifyToken(token)

            validateTopicIds(topicIds)

            User u = User.findByUid(jwtPayload.user_id)

            if (u == null){
                throw new NoUserFoundException("uid not found: "+jwtPayload.user_id)
            }

            removeUserInterests(u, topicIds)
            addUserInterests(u, topicIds)

            u.save(flush: true)
            render(view:'updateUserInterests')
        }catch(JsonSyntaxException e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 400
            render(view:'authFailed', model:[error: "Token is corrupted"])
        }catch (InvalidUserInputException e){
            log.error("InvalidUserInputException: "+e.message)
            response.status= 400
            render(view:'authFailed', model:[error: "InvalidUserInputException: "+e.getMessage()])
        }catch (Exception e){
            log.error("JsonSyntaxException: "+e.message)
            response.status= 401
            render(view:'authFailed', model:[error: e.getMessage()])
        }
    }


    def listAllTopics() {
        def topics = Topic.findAll(sort:"name")
        [topics: topics]

    }

    private boolean validateTopicIds(topicIds){
        for (String topicId: topicIds) {
            if (!ObjectId.isValid(topicId)) {
                throw new InvalidUserInputException("invalid topic ID: "+topicId)
            }
        }
        return true
    }

    private User saveNewUser(JWTPayload jwtPayload) {
        return saveNewUser(jwtPayload, null)
    }

    private User saveNewUser(JWTPayload jwtPayload, Date birthdate) {
        User u = User.findByUid(jwtPayload.user_id)
        if (u == null) {
            u = new User(email: jwtPayload.email, uid: jwtPayload.user_id, birthdate:birthdate)
        }
        return u
    }

    private void addUserInterests(User u, interests) {
        for (String topic : interests) {

            Topic t = Topic.get(new ObjectId(topic))
            if (t&&!hasUserSelectedTopic(u, t)) {
                u.addToInterests(t)
            }
        }
    }


    private void removeUserInterests(User u, List<String> topicIds) {
        List<String> userTopicsId = new ArrayList<>()
        List<Topic> topicsToBeRemoved = new ArrayList<>()
        for (Topic topic : u.interests) {

            if (topicIds.contains(topic.id.toString())) {
                userTopicsId.add(topic.id.toString())
            } else {
                topicsToBeRemoved.add(topic)
            }
        }

        for (Topic topic : topicsToBeRemoved) {
            u.removeFromInterests(topic)

            for (User user : topic.users) {
                if (user.id == u.id) {
                    topic.removeFromUsers(user)
                    if (!topic.save(flush: true)) {
                        topic.errors.allErrors.each {
                            log.error(it)
                        }
                    }
                    break
                }
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
