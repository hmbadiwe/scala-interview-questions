package com.example.alriddle.question3

import org.scalatest.{ShouldMatchers, FlatSpec}


/**
 * Created by hmbadiwe on 12/9/14.
 */
class TestSortHand extends FlatSpec with ShouldMatchers with HandTestData{
  behavior of "Card Sorter"
  it should "properly sort cards with ace as a high card" in {
       import AcesHighOrderingStrategy._
       randomListWithAce.sorted should contain inOrder (
         OrdinalCard(Four, Clubs),
         OrdinalCard(Jack, Diamonds),
         OrdinalCard(Queen, Diamonds),
         OrdinalCard(King, Diamonds),
         OrdinalCard(Deuce, Hearts),
         OrdinalCard(Jack, Hearts),
         OrdinalCard(Queen, Hearts),
         OrdinalCard(Ace, Hearts),
         OrdinalCard(Five, Spades),
         OrdinalCard(Six, Spades),
         OrdinalCard(Seven, Spades)
       )

  }
  it should "properly sort cards with ace as a low card" in {
    import AcesLowOrderingStrategy._
    randomListWithAce.sorted should contain inOrder (
      OrdinalCard(Four, Clubs),
      OrdinalCard(Jack, Diamonds),
      OrdinalCard(Queen, Diamonds),
      OrdinalCard(King, Diamonds),
      OrdinalCard(Ace, Hearts),
      OrdinalCard(Deuce, Hearts),
      OrdinalCard(Jack, Hearts),
      OrdinalCard(Queen, Hearts),
      OrdinalCard(Five, Spades),
      OrdinalCard(Six, Spades),
      OrdinalCard(Seven, Spades)
      )


  }
  it should "determine the most number of consecutive cards with aces high" in {
    import AcesHighOrderingStrategy._
    mostConsecutiveInRank(
      Set(
        OrdinalCard(Five, Clubs),
        OrdinalCard(Four, Clubs),
        OrdinalCard(Ace, Clubs),
        OrdinalCard(Jack, Clubs),
        OrdinalCard(Queen, Clubs),
        OrdinalCard(King, Clubs),
        OrdinalCard(Three, Clubs)
    )) shouldBe 4
  }
  it should "determine the most number of consecutive cards with aces low" in {
    import AcesLowOrderingStrategy._
    mostConsecutiveInRank(
      Set(
        OrdinalCard(Deuce, Spades),
        OrdinalCard(Four, Spades),
        OrdinalCard(Ace, Spades),
        OrdinalCard(Jack, Spades),
        OrdinalCard(Queen, Spades),
        OrdinalCard(Five, Spades),
        OrdinalCard(King, Spades),
        OrdinalCard(Three, Spades)
      )) shouldBe 5
  }
  it should "determine the most number of consecutive cards with aces low and different ranks" in {
    import AcesLowOrderingStrategy._
    mostConsecutiveInRank(
      Set(
        OrdinalCard(Deuce, Spades),
        OrdinalCard(Four, Diamonds),
        OrdinalCard(Ace, Clubs),
        OrdinalCard(Jack, Spades),
        OrdinalCard(Queen, Spades),
        OrdinalCard(Five, Clubs),
        OrdinalCard(King, Spades),
        OrdinalCard(Three, Spades)
      )) shouldBe 3
  }

}
