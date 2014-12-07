package com.example.alriddle.question2

/**
 * Created by hmbadiwe on 12/6/14.
 */

object RichBracketCharHelper{
  val listParens : Map[Char,Char] = Map('(' -> ')', '[' -> ']', '{'-> '}' )

  implicit class RichBracketChar(val self : Char){

    def isOpenParens : Boolean = listParens.keySet.contains(self)
    def isCloseParens : Boolean = listParens.values.toSeq.contains(self)
    //def isOpposite( oppChar : Char ) : Boolean = listParens get self map { _ == oppChar } getOrElse false
    def isOpposite( oppChar : Char ) : Boolean = listParens get self exists {  _ == oppChar }
  }
}

