package kllect2

class UrlMappings {

    static mappings = {

        "/articles"(controller: 'article', action:'listVideoArticles')
        "/articles/site/$site"(controller: 'article', action:'listVideoArticlesBySiteName')
        "/article/$id"(controller: 'article', action:'getArticle')
        "/"(controller: 'application', action:'index')
        "500"(view: '/error')
        "404"(view: '/notFound')
    }
}
