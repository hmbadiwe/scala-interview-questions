package com.example.alriddle.question3

import org.scalatest.{ ShouldMatchers, FlatSpec}


/**
 * Created by hmbadiwe on 12/7/14.
 */
class TestEvaluateHand extends FlatSpec with ShouldMatchers with HandTestData{
  behavior of "Hand Evaluator"

  it should "recognize Five of a Kind" in {
        import AcesHighOrderingStrategy._
        FiveOfAKindEval( fiveOfAKindKing) shouldBe Option(FiveOfAKind)
  }
  it should "recognize a Straight Flush with an aces high strategy" in {
    import AcesHighOrderingStrategy._
    StraightEval( straightFlush ) shouldBe Option(StraightFlush)
  }
  it should "recognize Four of a Kind" in {
    import AcesHighOrderingStrategy._
    FourOfAKindEval( fourOfAKindSpades ) shouldBe Option(FourOfAKind)
  }

}
