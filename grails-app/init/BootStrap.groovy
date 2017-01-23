import com.kllect.Article
import com.kllect.Topic
import com.kllect.User
import grails.util.Environment
import org.apache.commons.lang3.text.WordUtils

class BootStrap {

    def init = { servletContext ->

        Environment.executeForCurrentEnvironment {
            development {


                if (!User.count()){
                    println "Create users"
                    createUsers()
                }

                if (!Topic.count()){
                    println "Create topics"
                    def c = Article.createCriteria()
                    def topics = c.list {
                        projections {
                            distinct "tags"
                        }
                    }

                    for (def topic: topics){
                        if (topic instanceof List<String> && topic.size()==1){
                            Topic t = new Topic(name: topic[0], display_name: capitalize(topic[0]))
                            if (!t.save(flush: true)) {
                                t.errors.allErrors.each {
                                    log.error(it)
                                }
                            }else{
                                println t.display_name + " successfully saved"
                            }
                        }
                    }

                }


                println "bootstrap ended"
            }
        }
    }
    def destroy = {
    }


    def createUsers(){
        User u = new User(email: "mingliang.ma@hotmail.com", uid: "nglCQxu1eaYtDcr5HfZRRB4WKUm1")
        u.save()

        User u1 = new User(email: "mingliang.ma@gmail.com", uid: "xRf90vIblzZm2pwuYjEuoHK3rvt2")
        u1.save()
    }

    private static String capitalize(String words){

        return WordUtils.capitalizeFully(words.replace("_", " "));
    }
}
