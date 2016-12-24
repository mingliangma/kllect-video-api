package com.kllect

import org.bson.types.ObjectId

class ArticleTopicRelevancy {

    ObjectId id

    ObjectId articleId
    String articleTitle

    ObjectId topicId
    String topicName

    ObjectId userId

    Date updated_at

    boolean isRelevant
}
