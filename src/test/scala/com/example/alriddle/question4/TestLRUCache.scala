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
    val cache = new LRUCache[String,String](0)
    cache.put("Hopes", "Dreams")
    cache.put("Leno", "Letterman")
    cache.put("Celtics", "Lakers")

    cache.get("Hopes") shouldBe Some("Dreams")
    cache.get("Leno") shouldBe Some("Letterman")
    cache.get("Celtics") shouldBe Some("Lakers")
    cache.get("Raspberries") shouldBe None
  }

  it should "properly store and retrieve objects even within a cache with an lru " in {
    val cache = new LRUCache[String,String](2)
    cache.put("Hopes", "Dreams")
    cache.cacheSize shouldBe 1
    cache.bulkSize shouldBe 0

    cache.put("Leno", "Letterman")
    cache.put("Celtics", "Lakers")
    cache.cacheSize shouldBe 2
    cache.bulkSize shouldBe 1




  }
}
