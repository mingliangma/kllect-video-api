package kllect

class UrlMappings {

    static mappings = {

        "/articles"(controller: 'article', action:'listVideoArticles')
        "/articles/site/$site"(controller: 'article', action:'listVideoArticlesBySiteName')
        "/articles/topic/$tag"(controller: 'article', action:'listVideoByTag')
        "/articles/tag/$tag"(controller: 'article', action:'listVideoByTag') //to be deprecated
        "/article/$id"(controller: 'article', action:'getArticle')
        "/article/$id/relevancy"(controller: 'article', action:'setArticleRelevancy')
        "/tags"(controller: 'tag', action:'listAllTags')//to be deprecated
        "/topics"(controller: 'interest'){
            action = [GET:"listAllTags", POST:"saveUserInterests"]
        }

        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
