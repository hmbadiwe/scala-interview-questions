package com.example.alriddle.question5

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * Created by hmbadiwe on 12/12/14.
 */
class TestTRMylist extends FlatSpec with ShouldMatchers with TRMyListTestData{
     behavior of "TRMyList"
  it should "retrieve the size of the list" in {
    myStringList.size shouldBe 4
  }
  it should "support retrieval of elements of the same type" in {
    myStringList.get(0) shouldBe Some("My")
    myStringList.get(1) shouldBe Some("Name")
    myStringList.get(6) shouldBe None
  }
  it should "support the ability to convert to a list"  in {
    myStringList.toList should contain inOrderOnly ( "My", "Name", "Is", "...")
  }

  it should "support reversal of elements in the list" in {
    val reverseIntList = myIntList.reverse
    reverseIntList.get(0) shouldBe Some(103)
    reverseIntList.get(3) shouldBe Some(100)
  }
  it should "support prepending of elements to the list" in {
    val prependedIntList = myIntList prepend  99
    prependedIntList.get(0) shouldBe Some(99)

  }
  it should "support appending of elements to the list" in {
    val appendedList = myIntList append  104
    appendedList.get(4) shouldBe Some(104)

  }
  it should "support map operations" in {
    val myStringTupleIntList = myIntList.map{ x => (x, x.toString)}
    myStringTupleIntList.toList should contain inOrder ((100, "100"),(101, "101"),(102, "102"),(103, "103"))
  }
  it should "support filter operations" in {
    val nameYearsInLeagueTupleList = TRMyList(("Johnny Football", 1), ("RG III", 1), ("Megatron", 8))
    val veterans = nameYearsInLeagueTupleList.filter( x => x._2 > 1)
    veterans.size shouldBe 1
    veterans.get(0) shouldBe Some(("Megatron", 8 ))
    veterans.filter{_._1 == "Garo Yepremian"}.size shouldBe 0
  }

  it should "support the ability to generate a distinct list" in {
    val nameYearsInLeagueTupleList = TRMyList(("Johnny Football", 1), ("RG III", 1), ("Megatron", 8), ("Megatron", 8), ("Johnny Football", 1))
    val distinctPlayers = nameYearsInLeagueTupleList.distinct.toList
    distinctPlayers should contain only( ("Johnny Football", 1), ("RG III", 1), ("Megatron", 8) )
  }
  it should "support foldLeft operations" in {
    val myIntList : TRMyList[Int] = TRMyList(100, 101, 102)
    myIntList.foldLeft(0){(accum,elem) => accum + elem } shouldBe 303
  }
  it should "support the groupby operation" in {
    val groupedPlayers = nameYearsInLeagueTupleList.groupBy{ x => if( x._2 == 1 ) "Rookie" else "Veteran"}
    groupedPlayers.size shouldBe 2
    val optionalRookies = groupedPlayers.get("Rookie")
    optionalRookies should not be None
    optionalRookies foreach { rookies =>
      rookies.size shouldBe 2
      rookies.toList should contain only ( ("Johnny Football", 1), ("RG III", 1) )
    }

    val optionalVeterans = groupedPlayers.get("Veteran")
    optionalVeterans should not be None
    optionalVeterans foreach {  veterans =>
      veterans.size shouldBe 2
      veterans.toList should contain only ( ("Megatron", 8), ("Beast Mode", 8) )
    }

  }
  it should "support the zip operation" in {


    val overFlowzippedNameYears = nameYearsInLeagueTupleList.zip( TRMyList(1,2,3,4,5,6) )
    overFlowzippedNameYears.size shouldBe 4
    overFlowzippedNameYears.toList should contain inOrderOnly ((("Johnny Football", 1) ,1), (("RG III", 1),2), (("Megatron", 8),3), (("Beast Mode", 8),4))

    val smallZippedNameYears = nameYearsInLeagueTupleList.zip( TRMyList(1,2) )
    smallZippedNameYears.size shouldBe 2
    smallZippedNameYears.toList should contain inOrderOnly ((("Johnny Football", 1) ,1), (("RG III", 1), 2))


    val emptyList = nameYearsInLeagueTupleList.zip( TRMyList.empty[String] )
    emptyList.size shouldBe 0

  }

  it should "support the take operation" in {
    nameYearsInLeagueTupleList.take(2).toList should contain inOrderOnly (("Johnny Football", 1), ("RG III", 1))
    nameYearsInLeagueTupleList.take(10).toList should contain inOrderOnly (("Johnny Football", 1), ("RG III", 1), ("Megatron", 8), ("Beast Mode", 8))
  }
}
