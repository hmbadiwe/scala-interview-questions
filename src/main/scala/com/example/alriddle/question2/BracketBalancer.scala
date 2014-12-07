package com.example.alriddle.question2

/**
 * Created by hmbadiwe on 12/6/14.
 */
object BracketBalancer {
      import RichBracketCharHelper._

      def isEverythingBalanced( parenStr : String) : Boolean = {
        require( parenStr != null, "No nulls allowed" )
        val returnValue = parenStr.foldLeft( List.empty[Char] ){ ( accum :List[Char], charInSeq : Char) =>
           if( charInSeq.isCloseParens ){
            accum.lastOption map { c =>
              if( c isOpposite charInSeq )accum.init
              else accum :+ charInSeq
            } getOrElse accum :+ charInSeq
           }
           else accum :+ charInSeq
        }
        returnValue.isEmpty
      }
      def isEverythingBalancedAlt( parenStr : String ) : Boolean = ???
}
