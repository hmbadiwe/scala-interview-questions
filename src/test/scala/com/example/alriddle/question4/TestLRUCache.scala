package com.example.alriddle.question4

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * Created by hmbadiwe on 12/12/14.
 */
class TestLRUCache extends FlatSpec with ShouldMatchers {
  behavior of "LRUCache"

  it should  "not allow you to create a bogus cache" in {
       intercept[IllegalArgumentException]{
         new LRUCache[Double,Int](-1)
       }
  }
  it should "properly store and retrieve objects even within a cache with no lru" in {
    val lRUCache = new LRUCache[String,String](0)
    lRUCache.put("Hopes", "Dreams")
    lRUCache.put("Leno", "Letterman")
    lRUCache.put("Celtics", "Lakers")

    lRUCache.get("Hopes") shouldBe Some("Dreams")
    lRUCache.get("Leno") shouldBe Some("Letterman")
    lRUCache.get("Celtics") shouldBe Some("Lakers")
    lRUCache.get("Raspberries") shouldBe None
  }

  it should "properly store and retrieve objects even within a cache with an lru " in {
    val lRUCache = new LRUCache[String,String](2)
    lRUCache.put("Hopes", "Dreams")
    lRUCache.cacheSize shouldBe 1
    lRUCache.bulkSize shouldBe 0

    lRUCache.put("Leno", "Letterman")
    lRUCache.put("Celtics", "Lakers")
    lRUCache.cacheSize shouldBe 2
    lRUCache.bulkSize shouldBe 1


  }
  it should "move data from bulk to cache " in {
    val lRUCache = new LRUCache[String,String](2)
    lRUCache.put("Hopes", "Dreams")
    lRUCache.put("Leno", "Letterman")
    lRUCache.put("Celtics", "Lakers")
    lRUCache.cacheSize shouldBe 2
    lRUCache.bulkSize shouldBe 1

    lRUCache.get("Hopes") shouldBe Some("Dreams")
    lRUCache.cacheSize shouldBe 2
    lRUCache.bulkStore.get("Hopes") shouldBe null
    lRUCache.cache.get("Leno") shouldBe null


  }
}
