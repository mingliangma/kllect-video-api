package com.kllect.article


import grails.test.mixin.integration.Integration
import grails.transaction.*
import org.grails.web.json.JSONElement
import org.jsoup.Jsoup
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import static grails.web.http.HttpHeaders.*
import static org.springframework.http.HttpStatus.*
import spock.lang.*
import geb.spock.*
import grails.plugins.rest.client.RestBuilder

@Integration
@Rollback
class ArticleFunctionalSpec extends GebSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "Test Business Insider"() {
        when:"The home page is requested"
            def resp = restBuilder().get("$baseUrl/articles/site/BusinessInsider")

        then:"The response is correct"
            resp.status == OK.value()
            resp.headers[CONTENT_TYPE] == ['application/json;charset=UTF-8']

            List jsonList= resp.json

            for (JSONElement e: jsonList){
                e.siteName == "BusinessInsider"
                e.isVideo == true

                String articleUrl = e["articleUrl"]
                articleUrl.startsWith("http://www.businessinsider.com")

                println "articleUrl: "+articleUrl
                Document doc = Jsoup.connect(articleUrl).ignoreHttpErrors(true).get();
                Elements videoDoc = doc.select("div.ooyala-video-player")
                videoDoc.size() < 0

            }
    }

    RestBuilder restBuilder() {
        new RestBuilder()
    }
}
