package com.example.alriddle.question4

import java.util.Map.Entry
import java.util.{LinkedHashMap => JLHMap}
import java.util.{TreeMap => JTreeMap}

/**
 * Created by hmbadiwe on 12/12/14.
 */
class LRUCache[K,V]( fastCacheSize : Int ) {
  require(fastCacheSize >= 0, s"Seriously? ${fastCacheSize} ???")

  val bulkStore : JTreeMap[K,V] = new JTreeMap[K,V]()
  val cache : JLHMap[K, V] = new JLHMap[K,V](10){
    override def removeEldestEntry( entry: Entry[ K, V ] ): Boolean = {
      if( size() > fastCacheSize ) {
         bulkStore.put( entry.getKey, entry.getValue )
         true
      }
      else false
    }
  }
  def get( key : K) : Option[V] = {
        Option(cache.get(key)) orElse {
          Option(bulkStore.get(key))
        } match {
          case None => None
          case Some(x) => cache.put( key, x); bulkStore.remove(key); Some(x)
        }
  }
  def put( key : K, value : V ) = cache.put( key, value )
  def cacheSize : Int = cache.size()
  def bulkSize : Int = bulkStore.size()

}
