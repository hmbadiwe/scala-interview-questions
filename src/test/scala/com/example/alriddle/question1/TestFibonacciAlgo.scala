package com.example.alriddle.question1

import org.scalatest.{FlatSpec, ShouldMatchers}

/**
 * Created by hmbadiwe on 12/6/14.
 */
class TestFibonacciAlgo extends FlatSpec with ShouldMatchers{

  behavior of "Fiboncci algorithm"

  it should "properly calculate the fibonacci sequence naively" in {
    intercept[IllegalArgumentException]{
      FibonacciAlgo.fibonacci(-1)
    }
    intercept[IllegalArgumentException]{
      FibonacciAlgo.fibonacci(-20)
    }
    FibonacciAlgo.fibonacci(0) should be (0)
    FibonacciAlgo.fibonacci(1) should be (1)
    FibonacciAlgo.fibonacci(10) should be (55)

  }
  it should "properly calculate the fibonacci sequence tail-recursively" in {
    intercept[IllegalArgumentException]{
      FibonacciAlgo.fibonacci2(-5)
    }
    intercept[IllegalArgumentException]{
      FibonacciAlgo.fibonacci2(-100)
    }
    FibonacciAlgo.fibonacci2(0) should be (0)
    FibonacciAlgo.fibonacci2(1) should be (1)
    FibonacciAlgo.fibonacci2(10) should be (55)

  }
  it should "properly calculate the fibonacci sequence with streams" in {
    intercept[IllegalArgumentException]{
      FibonacciAlgo.streamBasedGenerator(-5)
    }
    intercept[IllegalArgumentException]{
      FibonacciAlgo.streamBasedGenerator(-100)
    }
    FibonacciAlgo.streamBasedGenerator(0) should be (0)
    FibonacciAlgo.streamBasedGenerator(1) should be (1)
    FibonacciAlgo.streamBasedGenerator(10) should be (55)

  }

}
