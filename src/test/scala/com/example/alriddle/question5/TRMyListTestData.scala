package com.example.alriddle.question5

/**
 * Created by hmbadiwe on 12/13/14.
 */
trait TRMyListTestData {
  val nameYearsInLeagueTupleList = TRMyList(("Johnny Football", 1), ("RG III", 1), ("Megatron", 8), ("Beast Mode", 8))
  val myStringList : TRMyList[String] = TRMyList("My", "Name", "Is", "...")
  val myIntList : TRMyList[Int] = TRMyList(100, 101, 102, 103 )
}
