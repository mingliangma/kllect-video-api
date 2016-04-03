package com.kllect

import org.bson.types.ObjectId

class Article {
    ObjectId id
    String title
    String site_name
    Date parse_date
    String publish_date
    String article_url
    String article_base_url
    String youtube_url
    String description
    Boolean is_video
    String video_selector
    static constraints = {
    }
}
