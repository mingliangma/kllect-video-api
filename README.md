
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
```
curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://api.app.kllect.com/articles/tag/wearable_tech"
```

The expected result should be:
```
[
  {
    "id": "57eb31757e243f831859235f",
    "title": "5 New Best Tech Gadgets WEARABLE DRONE & SMART DRONES Coming in 2016",
    "siteNeme": "youtube.com",
    "parseDate": "Tue Sep 27 22:56:53 EDT 2016",
    "publishDate": "2016-09-23T13:49:55.000Z",
    "articleUrl": "https://youtu.be/9o4fPJZd7N0",
    "articleBaseUrl": null,
    "youtubeUrl": "https://youtu.be/9o4fPJZd7N0",
    "description": "technology\ntechnology ",
    "isVideo": true,
    "videoSelector": null,
    "imageUrl": "https://i.ytimg.com/vi/9o4fPJZd7N0/mqdefault.jpg",
    "interest": "search_wearable_technology",
    "tags": [
      "wearable_tech",
      "drone"
    ],
    "category": "technology"
  },
  {
    "id": "57eb31747e243f831859235c",
    "title": "TOP 5   Wearable gadgets technology   late half of 2014",
    "siteNeme": "youtube.com",
    "parseDate": "Tue Sep 27 22:56:52 EDT 2016",
    "publishDate": "2016-09-23T13:49:19.000Z",
    "articleUrl": "https://youtu.be/NTU7UvsoTbg",
    "articleBaseUrl": null,
    "youtubeUrl": "https://youtu.be/NTU7UvsoTbg",
    "description": "technology\ntechnology ",
    "isVideo": true,
    "videoSelector": null,
    "imageUrl": "https://i.ytimg.com/vi/NTU7UvsoTbg/mqdefault.jpg",
    "interest": "search_wearable_technology",
    "tags": [
      "wearable_tech"
    ],
    "category": "technology"
  },
  {
    "id": "57eb31527e243f83185920d2",
    "title": "Top 3 Nano Technology 2017",
    "siteNeme": "youtube.com",
    "parseDate": "Tue Sep 27 22:56:18 EDT 2016",
    "publishDate": "2016-08-03T02:52:30.000Z",
    "articleUrl": "https://youtu.be/pIVtXdYYncQ",
    "articleBaseUrl": null,
    "youtubeUrl": "https://youtu.be/pIVtXdYYncQ",
    "description": "Thank You for watching, Please like and subscribe for more videos.\n\n\n\n\n\nTags:\ntechnology machines, agriculture technology machines\nultra-ever dry part 2, ultra-ever dry part iiCan You Walk on Water? (Non-Newtonian Fluid Pool, Non-Newtonian Fluid Pool, non newtonian fluid pool, Ultra Ever dry, Spray-on clothing, spray on clothes, liquipel, top nano technology, superhydrophobic, nano technology, new technology, cool technology, technology, cool science\ntechnology, new technology, inventions you didn't know existed, inventions, 5 new technology inventions you didn't know existed, new inventions, new, future hd, gadgets, amazing inventions, 5 new inventions, trixin, ksp moho, best new inventions, light saber, new technology inventions, 5 new inventions you didn't know existed, exploring mohole, mohole, moho, exploring, uranus, ksp atomic bomb, atomic bomb, nuclear bomb, ksp nuclear bomb, ksp nuclear, smartphone, kerbal space program, ksp, how technology changed our world, how has technology changed education, how technology changed our lives, technology changes everything, 5 ways technology has changed the world, how technology has changed the world for kids, technology has changed the world, technology that changed the world, new technology that will change the world, tech that has changed tha world\n\n\n\nUltra Ever dry\nSpray-on clothing\nLiquipel",
    "isVideo": true,
    "videoSelector": null,
    "imageUrl": "https://i.ytimg.com/vi/pIVtXdYYncQ/mqdefault.jpg",
    "interest": "search_nano_technology",
    "tags": [
      "wearable_tech",
      "nanotech"
    ],
    "category": "technology"
  },
  {
    "id": "57eb31547e243f83185920f8",
    "title": "Wearable Gadgets Nanotechnology 2015  You Tube",
    "siteNeme": "youtube.com",
    "parseDate": "Tue Sep 27 22:56:20 EDT 2016",
    "publishDate": "2016-09-20T21:38:07.000Z",
    "articleUrl": "https://youtu.be/7Ul7QoHA6vk",
    "articleBaseUrl": null,
    "youtubeUrl": "https://youtu.be/7Ul7QoHA6vk",
    "description": "These technology will change the human life in future.",
    "isVideo": true,
    "videoSelector": null,
    "imageUrl": "https://i.ytimg.com/vi/7Ul7QoHA6vk/mqdefault.jpg",
    "interest": "search_nano_technology",
    "tags": [
      "wearable_tech",
      "nanotech"
    ],
    "category": "technology"
  }
]
```