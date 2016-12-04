**API: get all topics**

GET URL: http://api.app.kllect.com/topics

```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://api.app.kllect.com/topics"
```
The expected result should be:
```
[
  {
      "topic": "artificial_intelligence",
      "id": "57fee9bc04e539e9b2141aa8"
    },
    {
      "topic": "driverless_cars",
      "id": "57fee9bc04e539e9b2141aa9"
    },
    {
      "topic": "drone",
      "id": "57fee9bc04e539e9b2141aaa"
    },
    {
      "topic": "ecommerce",
      "id": "57fee9bc04e539e9b2141aab"
    }
]
```

**API: Store user interests**

POST URL: http://api.app.kllect.com/topics

Request Body:
```
{
    token:  "eyJasdf.fdasdfedf.M7OGrZrawM",
	topicIds: ["57fee9bc04e539e9b2141aab", "57fee9bc04e539e9b2141aaa"]
}
```

Reponse Body - Successful:

    response status: 200
```
{
  "message": "successful"
}
```

Reponse Body - Token expired:

    response status: 401
```
{
  "message": "jwt expired"
}
```

Reponse Body - Bad token:

    response status: 400
```
{
  "message": "Token is corrupted"
}
```

**API: Store video topic relevancy**

POST URL: http://api.app.kllect.com/article/$articleId/relevancy?topic=($topicName)?isRelevant=(true or false)

Request Body:
```
{
    token:  "eyJasdf.fdasdfedf.M7OGrZrawM"
}
```


```
curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '	{token:eyJasdf.fdasdfedf.M7OGrZrawM}' "http://localhost:8080/article/581fffeadea4597b6104d3a7/relevancy?topic=drone&isRelevant=false"
```

Reponse Body - Successful:

    response status: 200
```
{
  "message": "successful"
}
```

Reponse Body - Fail:

    response status: 401
```
{
  "message": "invalid article id"
}
```

**API: get videos by topic**

GET URL: http://api.app.kllect.com/articles/topic/$topicParam

```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://api.app.kllect.com/articles/topic/wearable_tech"
```

The expected result should be:
```
{
  "articles": [
    {
          "id": "582b6966520fea06fb9783ea",
          "title": "The Birth of Emotional Intelligence in India | Sneh Vaswani | TEDxCRCE",
          "siteNeme": "youtube.com",
          "parseDate": "2016-11-15T20:00:38Z",
          "publishDate": "2016-11-15T19:37:39Z",
          "taggedDate": "2016-11-15T20:01:13Z",
          "articleUrl": "https://youtu.be/1enO9bD514g",
          "articleBaseUrl": null,
          "youtubeUrl": "https://youtu.be/1enO9bD514g",
          "description": "The friends we have are more than just the people we interact with...",
          "isVideo": true,
          "videoSelector": null,
          "imageUrl": "https://i.ytimg.com/vi/1enO9bD514g/mqdefault.jpg",
          "interest": "youtube_channel",
          "tags": [
            "artificial_intelligence"
          ],
          "category": "technology",
          "publisher": "TEDx Talks",
          "duration": 837
    },
    {
      "id": "582b312d520fea06c99c007b",
      "title": "Healthcare as an information business | Udi Manber | TEDxSanFrancisco",
      "siteNeme": "youtube.com",
      "parseDate": "2016-11-15T16:00:45Z",
      "publishDate": "2016-11-15T15:48:50Z",
      "taggedDate": "2016-11-15T16:01:17Z",
      "articleUrl": "https://youtu.be/V6Ksz1zOCSU",
      "articleBaseUrl": null,
      "youtubeUrl": "https://youtu.be/V6Ksz1zOCSU",
      "description": "Udi knows everything about \"Search\" and how information gets taped to create and improve intelligent systems. In his talk he shares how we need to access and open the data in order to create an \"smart\" and \"efficient\" system for healthcare and patients. \n\nUdi Manber is a computer scientist known for his work on search, He is also one of the authors of agrep and GLIMPSE.\r\n Udi hold a master degree in mathematics and a PhD in computer sciences. He started to develop search software in the late 80s when nearly no one was interested, but he was patient enough to wait 10 years when it finally paid off in a big way. He joined Amazon as Chief Algorithm officer in 2002, lead the search groups at Yahoo, Google and Youtube.\r\nIn 2015 he left Google to join the National Institutes of Health, where he is trying to use his expertise to improve access to medical knowledge.\n\nThis talk was given at a TEDx event using the TED conference format but independently organized by a local community. Learn more at http://ted.com/tedx",
      "isVideo": true,
      "videoSelector": null,
      "imageUrl": "https://i.ytimg.com/vi/V6Ksz1zOCSU/mqdefault.jpg",
      "interest": "youtube_channel",
      "tags": [
        "artificial_intelligence"
      ],
      "category": "technology",
      "publisher": "TEDx Talks",
      "duration": 904
    }
  ],
  "articleCount": 10,
  "nextPagePath": "articles/tag/artificial_intelligence?offset=10"
}
```


**API: get recommending videos**

GET URL: http://api.app.kllect.com/articles/recommending?uid=($firebase_user_id)


```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://api.app.kllect.com/articles/recommending?uid=VXynnzand5RLjmSI5zJUwX2TyDs2"
```

The expected result should be:
```
{
  "articles": [
    {
          "id": "582b6966520fea06fb9783ea",
          "title": "The Birth of Emotional Intelligence in India | Sneh Vaswani | TEDxCRCE",
          "siteNeme": "youtube.com",
          "parseDate": "2016-11-15T20:00:38Z",
          "publishDate": "2016-11-15T19:37:39Z",
          "taggedDate": "2016-11-15T20:01:13Z",
          "articleUrl": "https://youtu.be/1enO9bD514g",
          "articleBaseUrl": null,
          "youtubeUrl": "https://youtu.be/1enO9bD514g",
          "description": "The friends we have are more than just the people we interact with...",
          "isVideo": true,
          "videoSelector": null,
          "imageUrl": "https://i.ytimg.com/vi/1enO9bD514g/mqdefault.jpg",
          "interest": "youtube_channel",
          "tags": [
            "artificial_intelligence"
          ],
          "category": "technology",
          "publisher": "TEDx Talks",
          "duration": 837
    },
    {
      "id": "582b312d520fea06c99c007b",
      "title": "Healthcare as an information business | Udi Manber | TEDxSanFrancisco",
      "siteNeme": "youtube.com",
      "parseDate": "2016-11-15T16:00:45Z",
      "publishDate": "2016-11-15T15:48:50Z",
      "taggedDate": "2016-11-15T16:01:17Z",
      "articleUrl": "https://youtu.be/V6Ksz1zOCSU",
      "articleBaseUrl": null,
      "youtubeUrl": "https://youtu.be/V6Ksz1zOCSU",
      "description": "Udi knows everything about \"Search\" and how information gets taped to create and improve intelligent systems. In his talk he shares how we need to access and open the data in order to create an \"smart\" and \"efficient\" system for healthcare and patients. \n\nUdi Manber is a computer scientist known for his work on search, He is also one of the authors of agrep and GLIMPSE.\r\n Udi hold a master degree in mathematics and a PhD in computer sciences. He started to develop search software in the late 80s when nearly no one was interested, but he was patient enough to wait 10 years when it finally paid off in a big way. He joined Amazon as Chief Algorithm officer in 2002, lead the search groups at Yahoo, Google and Youtube.\r\nIn 2015 he left Google to join the National Institutes of Health, where he is trying to use his expertise to improve access to medical knowledge.\n\nThis talk was given at a TEDx event using the TED conference format but independently organized by a local community. Learn more at http://ted.com/tedx",
      "isVideo": true,
      "videoSelector": null,
      "imageUrl": "https://i.ytimg.com/vi/V6Ksz1zOCSU/mqdefault.jpg",
      "interest": "youtube_channel",
      "tags": [
        "artificial_intelligence"
      ],
      "category": "technology",
      "publisher": "TEDx Talks",
      "duration": 904
    }
  ],
  "articleCount": 10,
  "nextPagePath": "articles/tag/artificial_intelligence?offset=10"
}
```
