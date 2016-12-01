**API: get all tags**
```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://api.app.kllect.com/tags"
```
The expected result should be:
```
[
  {
    "tagName": "artificial_intelligence"
  },
  {
    "tagName": "battery"
  },
  {
    "tagName": "biotech"
  },
  {
    "tagName": "driverless_cars"
  },
  {
    "tagName": "drone"
  },
  {
    "tagName": "ecommerce"
  },
  {
    "tagName": "internet_of_things"
  },
  {
    "tagName": "manufacturing"
  },
  {
    "tagName": "nanotech"
  },
  {
    "tagName": "others"
  },
  {
    "tagName": "smartphones"
  },
  {
    "tagName": "social_networks"
  },
  {
    "tagName": "virtual_reality_and_augmented_reality"
  },
  {
    "tagName": "wearable_tech"
  }
]
```


**API: get videos by tag**

API URL: http://api.app.kllect.com/articles/tag/$tagParam

```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://api.app.kllect.com/articles/tag/wearable_tech"
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