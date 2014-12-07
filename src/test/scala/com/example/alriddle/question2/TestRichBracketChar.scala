package com.example.alriddle.question2

import org.scalatest.{ShouldMatchers, FlatSpec}

/**
 * Created by hmbadiwe on 12/6/14.
 * Unit test for testing my rich char classes
 */
class TestRichBracketChar extends FlatSpec with ShouldMatchers{
    import RichBracketCharHelper._
     behavior of "RichBracketChar"
    it should "recognize closing parentheses" in {
      '{'.isCloseParens shouldBe false
      ')'.isCloseParens shouldBe true
      ']'.isCloseParens shouldBe true
      '['.isCloseParens shouldBe false
      'a'.isCloseParens shouldBe false
      '!'.isCloseParens shouldBe false
    }
  it should "recognize open parentheses" in {
    '{'.isOpenParens shouldBe true
    ')'.isOpenParens shouldBe false
    ']'.isOpenParens shouldBe false
    '['.isOpenParens shouldBe true
    'a'.isOpenParens shouldBe false
    '!'.isOpenParens shouldBe false
  }
    it should "recognize its opposite" in {
      '{' isOpposite '}' shouldBe true
      '(' isOpposite ')' shouldBe true
      '[' isOpposite ']' shouldBe true
    }


}
