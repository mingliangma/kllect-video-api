package kllect

class UrlMappings {

    static mappings = {

        "/articles"(controller: 'article', action:'listVideoArticles')
        "/articles/site/$site"(controller: 'article', action:'listVideoArticlesBySiteName')
        "/articles/interest/$interest"(controller: 'article', action:'listVideoByInterest')
        "/articles/interests"(controller: 'interest', action:'saveUserInterests')
        "/articles/tag/$tag"(controller: 'article', action:'listVideoByTag')
        "/article/$id"(controller: 'article', action:'getArticle')
        "/tags"(controller: 'tag', action:'listAllTags')
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
