package com.kllect

import org.bson.Document
import org.bson.types.ObjectId
import com.kllect.HiddenStatus

class Article {
    ObjectId id
    String title
    String site_name
    Date parse_date
    Date publish_date
    Date tagged_date
    String article_url
    String article_base_url
    String youtube_url
    String description
    Boolean is_video
    String video_selector
    String image_url
    String extraction_method
    String category
    String publisher
    int duration
    String[] tags
    int hidden_status

    static mapping = {
        article_url index:true, indexAttributes: [unique:true, dropDups:true]
        title index:true, indexAttributes: [unique:true, dropDups:true]
        tags index:true
    }

    static constraints = {
        video_selector nullable: true
        article_base_url nullable: true
        hidden_status nullable: true
    }

    Article(Document d){

        id = d.get("_id")
        title = d.get("title")
        site_name = d.get("site_name")
        parse_date = d.get("parse_date")
        publish_date = d.get("publish_date")
        tagged_date = d.get("tagged_date")
        article_url = d.get("article_url")
        article_base_url = d.get("article_base_url")
        youtube_url = d.get("youtube_url")
        description = d.get("description")
        is_video = d.get("is_video")
        video_selector = d.get("video_selector")
        image_url = d.get("image_url")
        extraction_method = d.get("extraction_method")
        category = d.get("category")
        publisher = d.get("publisher")
        duration = d.get("duration")
        tags = d.get("tags")
        hidden_status = d.get("hidden_status")
    }

    HiddenStatus getHiddenStatus(){
        HiddenStatus.byStatus(hidden_status)
    }
}
