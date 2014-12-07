package com.example.alriddle.question2

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * Created by hmbadiwe on 12/6/14.
 * testing my bracket balancer algorithm
 */
class TestBracketBalancer extends FlatSpec with ShouldMatchers{
       behavior of "BracketBalancer"
      import BracketBalancer._

      it should "reject null" in {
           intercept[IllegalArgumentException]{
             isEverythingBalanced(null)
           }
      }
      it should "recognize a balanced empty string" in {
         isEverythingBalanced("") shouldBe true
      }
      it should "recognize a concentric set of parentheses" in {
        isEverythingBalanced("([{}])") shouldBe true
      }
      it should "recognize a non-concentric set of parentheses" in {
        isEverythingBalanced("[]([{}[]()])") shouldBe true
      }
      it should "fail for an unbalanced single paranthesis" in {
        isEverythingBalanced("[") shouldBe false
        isEverythingBalanced("}") shouldBe false
      }
      it should "fail for unbalanced multiple parantheses" in {
        isEverythingBalanced("({)}") shouldBe false
        isEverythingBalanced("}{") shouldBe false
        isEverythingBalanced("){") shouldBe false
      }

}
