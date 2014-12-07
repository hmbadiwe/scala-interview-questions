package com.example.alriddle.question1

/**
 * Created by hmbadiwe on 12/6/14.
 */

import scala.annotation.tailrec
import scala.math.BigInt.int2bigInt

object FibonacciAlgo {
  /** Lifted from the Scaladocs example for a fibonacci implementation using streams
   *
   */
  private val fibonacciStream : Stream[BigInt] = BigInt(0) #:: BigInt(1) #::fibonacciStream.zip( fibonacciStream.tail ).map{ z => z._1 + z._2}
  private def defaultWrapAroundBadIndex( index: Int )( fn: (Int) => BigInt ): BigInt = {
    require( index >= 0, "argument to Fibonacci generator should be greater than or equal to zero" )
    fn( index )
  }

  def fibonacci( index: Int ): BigInt = defaultWrapAroundBadIndex( index ) { index =>
    if (index <= 1) index
    else fibonacci( index - 1 ) + fibonacci( index - 2)
  }

  def fibonacci2( index: Int ): BigInt = defaultWrapAroundBadIndex( index ) { index =>

    @tailrec
    def tailRecursiveHelper( a: BigInt, b: BigInt, count: Int ): BigInt = {
      if (count <= 0) a
      else tailRecursiveHelper( b, a + b, count - 1 )
    }

    tailRecursiveHelper( 0, 1, index )
  }
  def streamBasedGenerator( index : Int ) : BigInt = defaultWrapAroundBadIndex(index){ index =>
    (fibonacciStream take (index + 1)).last
  }

}

