package com.example.alriddle.question1

import org.scalatest.{FlatSpec, ShouldMatchers}

/**
 * Created by hmbadiwe on 12/6/14.
 */
class TestFibonacciAlgo extends FlatSpec with ShouldMatchers{

  behavior of "Fibonacci algorithm"
  import FibonacciAlgo._
  
  it should "properly calculate the fibonacci sequence naively" in {
    intercept[IllegalArgumentException]{
      fibonacci(-1)
    }
    intercept[IllegalArgumentException]{
      fibonacci(-20)
    }
    fibonacci(0) should be (0)
    fibonacci(1) should be (1)
    fibonacci(10) should be (55)

  }
  it should "properly calculate the fibonacci sequence tail-recursively" in {
    intercept[IllegalArgumentException]{
      fibonacci2(-5)
    }
    intercept[IllegalArgumentException]{
      fibonacci2(-100)
    }
    fibonacci2(0) should be (0)
    fibonacci2(1) should be (1)
    fibonacci2(10) should be (55)

  }
  it should "properly calculate the fibonacci sequence with streams" in {
    intercept[IllegalArgumentException]{
      streamBasedGenerator(-5)
    }
    intercept[IllegalArgumentException]{
      streamBasedGenerator(-100)
    }
    streamBasedGenerator(0) should be (0)
    streamBasedGenerator(1) should be (1)
    streamBasedGenerator(10) should be (55)

  }

}
