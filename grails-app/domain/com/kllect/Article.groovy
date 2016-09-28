package com.kllect

import org.bson.types.ObjectId

class Article {
    ObjectId id
    String title
    String site_name
    String parse_date
    String publish_date
    String tagged_date
    String article_url
    String article_base_url
    String youtube_url
    String description
    Boolean is_video
    String video_selector
    String image_url
    String extraction_method
    String category
    String[] tags;

    static mapping = {
        article_url index:true, indexAttributes: [unique:true, dropDups:true]
        title index:true, indexAttributes: [unique:true, dropDups:true]
        tags index:true
    }

    static constraints = {
    }
}
