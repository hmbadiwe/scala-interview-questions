package com.example.alriddle

/**
 * Created by hmbadiwe on 12/7/14.
 */
package object question3 {
  sealed trait Suit{
    val name : String
    override def toString = name
  }
  sealed trait RedSuit extends Suit
  sealed trait BlackSuit extends Suit
  sealed trait Joker
  sealed trait Rank{
    val value : Int
    val name : String
  }


  object Ace extends Rank{ val value = 1; val name = "Ace"}
  object Deuce extends Rank{ val value = 2; val name = "Deuce"}
  object Three extends Rank{ val value =3; val name = "Three" }
  object Four extends Rank{ val value = 4; val name = "Four"}
  object Five extends Rank{ val value = 5; val name = "Five"}
  object Six extends Rank{ val value = 6; val name = "Six"}
  object Seven extends Rank{ val value = 7; val name = "Seven"}
  object Eight extends Rank{ val value = 8; val name = "Eight"}
  object Nine extends Rank{ val value = 9; val name = "Nine"}
  object Ten extends Rank{ val value = 10; val name = "Ten"}
  object Jack extends Rank{ val value = 11; val name = "Jack"}
  object Queen extends Rank{ val value = 12; val name = "Queen"}
  object King extends Rank{ val value = 13; val name = "King"}

  object Diamonds extends RedSuit{ val name = "Diamonds"}
  object Hearts extends RedSuit{ val name = "Hearts"}
  object Spades extends BlackSuit{ val name = "Spades"}
  object Clubs extends BlackSuit{ val name = "Clubs"}
  
  sealed trait Card
  sealed trait JokerCard extends Card{ self : Suit =>  }
  case class OrdinalCard( rank : Rank, suit : Suit) extends Card{
    override def toString = rank.name + " of " + suit.name
  }
  object OrdinalCard{
    implicit def defaultCardToInt( card : OrdinalCard ) : Int = card.rank.value
  }

  object RedJoker extends JokerCard with RedSuit{ val name = "Red Joker"}
  object BlackJoker extends JokerCard with BlackSuit{ val name = "Black Joker"}

  trait WildCardStrategy {
    val wildCards : Set[ Card ]
  }
  object WildCardStrategy{
    implicit object JokersWild extends WildCardStrategy{
      val wildCards : Set[Card] = Set( BlackJoker, RedJoker)
    }
  }
  trait CardOrderingStrategy extends Ordering[OrdinalCard]{
    def compare( x: OrdinalCard, y: OrdinalCard ): Int = {
      if( x.suit == y.suit)x.toInt compareTo y.toInt
      else x.suit.name compareTo y.suit.name
    }
  }


  object AcesHighOrderingStrategy {
    implicit def acesHighCardToRank( card : OrdinalCard ) : Int = {
      if( card.rank == Ace )14
      else card.rank.value
    }
    implicit val cardOrderingStrategy = new CardOrderingStrategy{
      override def compare( x: OrdinalCard, y: OrdinalCard ): Int = {
        if( x.suit == y.suit)x.toInt compareTo y.toInt
        else x.suit.name compareTo y.suit.name
      }
    }

  }

  object AcesLowOrderingStrategy {
    implicit val cardOrderingStrategy = new CardOrderingStrategy{}
  }



  object Deck {
    import scala.util.Random
    val rankList = List(Ace, Deuce, Three, Four, Five, Six, Seven, Eight, Nine, Ten, Jack, Queen, King)
    val suitList = List( Diamonds, Hearts, Spades, Clubs )
    lazy val DeckNoJoker : Set[Card] = {
      val x = for {
        rank <- rankList
        suit <- suitList
      }yield(OrdinalCard(rank, suit))
      x.toList.toSet
    }
    lazy val FullDeck : Set[Card] = Set(RedJoker, BlackJoker) ++ DeckNoJoker

    def shuffleDeck : Set[Card] = Random.shuffle(DeckNoJoker)
    def shuffleFullDeck : Set[Card] = Random.shuffle(FullDeck)
  }

  case class Hand( cards : Set[Card]){
    require(cards.size == 5, "Must have a set of 5 cards")
    def replace( replaceCards : Set[Card], withCards : Set[Card]) : Hand = {
      require( !(replaceCards & withCards).isEmpty, "Can not replace the same cards" )
      Hand( cards -- replaceCards ++ withCards)
    }
  }

  sealed trait HandRank
  object FiveOfAKind extends HandRank
  object StraightFlush extends HandRank
  object FourOfAKind extends HandRank
  object FullHouse extends HandRank

  trait RuleEval{
    def apply( hand : Hand )(implicit w : WildCardStrategy, ordering : Ordering[OrdinalCard], fn : OrdinalCard => Int) : Option[HandRank]
  }

  def mostConsecutiveInRank( cards : Set[OrdinalCard])(implicit ordering : Ordering[OrdinalCard], fn : OrdinalCard => Int) : Int = {
     val sortedCards = cards.toList.sorted
     val listOfLists = sortedCards.foldLeft(List.empty[List[OrdinalCard]]){ (accum, card) =>
       val returnList = accum.lastOption.getOrElse(List.empty[OrdinalCard])
       if( returnList.isEmpty){
          accum :+ (card :: returnList)
       }
       else{
         val last: OrdinalCard = returnList.last
         if( last.toInt + 1 == card.toInt && last.suit == card.suit){
             accum.init :+ (accum.last :+ card)
          }
         else{
            accum :+ List(card)
          }
       }
     }
    listOfLists.map{_.size}.max
  }

  object FiveOfAKindEval extends RuleEval{
    override def apply( hand: Hand )(implicit wildCardStrategy : WildCardStrategy, ordering : Ordering[OrdinalCard], fn : OrdinalCard => Int): Option[HandRank] = {

      val tameCards = hand.cards -- wildCardStrategy.wildCards
      val jokerSplit = tameCards.groupBy{ c => if( c.isInstanceOf[JokerCard])"joker" else "ordinal"}
      val jokers = jokerSplit.get("joker").getOrElse(Set.empty[Card])
      val ordinals = jokerSplit.get( "ordinal" ).getOrElse(Set.empty[Card])

      if( jokers.isEmpty ){
        val ordSet = ordinals.map{ c => c.asInstanceOf[OrdinalCard].rank }.toSet
        if( ordSet.size == 1 ) Option(FiveOfAKind)
        else None
      }
      else None
    }
  }
  object StraightEval extends RuleEval{
    override def apply( hand: Hand )(implicit wildCardStrategy : WildCardStrategy,  ordering : Ordering[OrdinalCard], fn : OrdinalCard => Int): Option[HandRank] = {
      val wildCards = wildCardStrategy.wildCards & hand.cards
      val tameCards = hand.cards -- wildCards
      val tameOrdinalCards = tameCards.filter{_.isInstanceOf[OrdinalCard]}.map{_.asInstanceOf[OrdinalCard]}
      val mostConsecutive = mostConsecutiveInRank(tameOrdinalCards)
      val countStraight = mostConsecutive + wildCards.size
      if(  countStraight == 5 ) Option(StraightFlush)
      else if( countStraight == 4 ) Option(FourOfAKind)
      else None
    }
  }
  object FourOfAKindEval extends RuleEval{
    override def apply( hand: Hand )(implicit wildCardStrategy : WildCardStrategy,  ordering : Ordering[OrdinalCard], fn : OrdinalCard => Int): Option[HandRank] = {
      val wildCards = wildCardStrategy.wildCards & hand.cards
      val tameCards = hand.cards -- wildCards
      val tameOrdinalCards = tameCards.filter{_.isInstanceOf[OrdinalCard]}.map{_.asInstanceOf[OrdinalCard]}
      val groupedCards = tameOrdinalCards.toList.groupBy(_.suit)
      val maxSuit = groupedCards.toList.maxBy(_._2.size)
      val countStraight = maxSuit._2.size + wildCards.size
      if(  countStraight >= 4 ) Option(FourOfAKind)
      else None
    }
  }


}
