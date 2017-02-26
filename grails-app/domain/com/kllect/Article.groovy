package com.kllect

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
    boolean is_corrupted
    int hidden_status

    static mapping = {
        article_url index:true, indexAttributes: [unique:true, dropDups:true]
        title index:true, indexAttributes: [unique:true, dropDups:true]
        tags index:true
    }

    static constraints = {
        video_selector nullable: true
        article_base_url nullable: true
        is_corrupted nullable: true
        hidden_status nullable: true
    }

    HiddenStatus getHiddenStatus(){
        HiddenStatus.byStatus(hidden_status)
    }
}
