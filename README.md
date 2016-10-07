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
      "id": "57ec39b81db9d900633b6b2c",
      "title": "internet of things in business intelligence",
      "siteNeme": "youtube.com",
      "parseDate": "Wed Sep 28 21:44:24 UTC 2016",
      "publishDate": "2016-09-26T12:07:06.000Z",
      "articleUrl": "https://youtu.be/VcR7fKyhbG4",
      "articleBaseUrl": null,
      "youtubeUrl": "https://youtu.be/VcR7fKyhbG4",
      "description": "A small presentation on internet of things in ...",
      "isVideo": true,
      "videoSelector": null,
      "imageUrl": "https://i.ytimg.com/vi/VcR7fKyhbG4/mqdefault.jpg",
      "interest": "search_internet_of_things",
      "tags": [
        "internet_of_things",
        "artificial_intelligence"
      ],
      "category": "technology",
      "publisher": null
    },
    {
      "id": "57ec257e1db9d9003f4a1c62",
      "title": "Invasion of Artificial Intelligence",
      "siteNeme": "youtube.com",
      "parseDate": "Wed Sep 28 20:18:06 UTC 2016",
      "publishDate": "2016-09-13T05:44:48.000Z",
      "articleUrl": "https://youtu.be/Z5NKkfkuI-c",
      "articleBaseUrl": null,
      "youtubeUrl": "https://youtu.be/Z5NKkfkuI-c",
      "description": "A fun but not so light-hearted look at the invasion of...",
      "isVideo": true,
      "videoSelector": null,
      "imageUrl": "https://i.ytimg.com/vi/Z5NKkfkuI-c/mqdefault.jpg",
      "interest": "search_artificial_intelligence",
      "tags": [
        "artificial_intelligence"
      ],
      "category": "technology",
      "publisher": null
    }
  ],
  "articleCount": 10,
  "nextPagePath": "articles/tag/artificial_intelligence?offset=10"
}
```