package com.example.alriddle.question3


/**
 * Created by hmbadiwe on 12/7/14.
 */
trait HandTestData {
       val fiveOfAKindKing = Hand(
         Set(
           OrdinalCard( Jack, Diamonds ),
           OrdinalCard( Jack, Hearts ),
           OrdinalCard( Jack, Spades ),
           OrdinalCard( Jack, Clubs ),
           BlackJoker
       )
     )
      val straightFlush = Hand(
      Set(
        OrdinalCard( Seven, Diamonds),
        OrdinalCard( Eight, Diamonds),
        OrdinalCard( Six, Diamonds ),
        OrdinalCard( Five, Diamonds),
        OrdinalCard( Four, Diamonds)
      )
      )
      val fourOfAKindSpades = Hand(
        Set(
          OrdinalCard( Seven, Spades ),
          OrdinalCard( Eight, Spades ),
          OrdinalCard( Six, Spades ),
          OrdinalCard( Five, Spades ),
          OrdinalCard( Four, Spades )
        )
      )
      val randomSetWithAce = Set(
        OrdinalCard(Seven, Spades),
        OrdinalCard(Ace, Hearts),
        OrdinalCard(King, Diamonds),
        OrdinalCard(Queen, Spades),
        OrdinalCard(Four, Clubs)
      )
      val randomListWithAce = List(
        OrdinalCard(Queen, Hearts),
        OrdinalCard(Deuce, Hearts),
        OrdinalCard(Seven, Spades),
        OrdinalCard(Ace, Hearts),
        OrdinalCard(Jack, Hearts),
        OrdinalCard(King, Diamonds),
        OrdinalCard(Six, Spades),
        OrdinalCard(Five, Spades),
        OrdinalCard(Queen, Diamonds),
        OrdinalCard(Four, Clubs),
        OrdinalCard(Jack, Diamonds)
      )

}
