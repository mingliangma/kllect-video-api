package kllect

class UrlMappings {

    static mappings = {

        "/articles"(controller: 'article', action:'listVideoArticles')
        "/articles/site/$site"(controller: 'article', action:'listVideoArticlesBySiteName')
        "/articles/recommending"(controller: 'article', action:'listVideoByRecommending')
        "/articles/topic/$tag"(controller: 'article', action:'listVideoByTag')
        "/articles/tag/$tag"(controller: 'article', action:'listVideoByTag') //to be deprecated
        "/article/$id"(controller: 'article', action:'getArticle')
        "/article/$id/relevancy"(controller: 'article', action:'setArticleRelevancy')
        "/topics"(controller: 'interest'){
            action = [GET:"listAllTopics", POST:"saveUserInterests", PUT:"updateUserInterests"]
        }
        "/user/$uid"(controller: 'user', action:'getUserProfile')


        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
