Angie's List Scala Challenges
=============================

This project contains a series of Scala challenges that will allow you to demonstrate your skill level with Scala. All the problems are optional. Complete as many as you can.

The project is littered with opportunities for you to demonstrate higher degrees of technical skill. This is your opportunity to wow us!

You will be graded on:

(listed in order of importance)

Correctness - your implementation works<br/>
DRY-ness - your implementation employs appropriate ammounts of abstration to avoid repetition of code<br/>
Expressiveness - your implementation conveys the way it works clearly<br/>
Elegance - your implementation has a certain je ne sais quoi<br/>
Bonus: Using "a Functional Approach" - immutability, referential transparency, usage of the right higher-order functions<br/>
Bonus: Tests - scala-test is included in the build, writing tests is always appreciated<br/>
Super-Duper Bonus: Functional Testing - using a property-based testing approach (by adding ScalaCheck to sbt and using the test framework)<br/>
Bonus: Type Design - designing types/type constructors to support logical use cases and improve correctness<br/>

You will NOT be graded on:

Doing everything in one line or expression<br/>
Time taken for completion<br/>
#### IMPORTANT NOTE:
Some of these problems are HARD or expect strong Functional Programming skills.<br/>
Admiting you don't know the answer or skipping that problem doesn't automatically exclude you from being considered.

### Instructions

Once you complete the challenges, please submit the following:

 * Organize the code in a sensible manner
 * This directory zipped up (remember to remove the target directory)
 * A text file:
    + At the top, provide how long it took to complete
    + With solutions and/or notes for any of the challenges
    + If you chose to skip an exercise, explain why

## Challenges

### 1. Fibonacci

The fibonacci sequence is like so: 1, 1, 2, 3, 5, 8, 13, 21, ...<br/>
It is defined as follows:<br/>
fibonacci(1) = 1<br/>
fibonacci(2) = 1              <br/>
fibonacci(n) = fibonacci(n - 1) + fibonacci(n - 2)<br/>

####Tasks:

##### Implement a function fibonacci(n: Int): BigInt naively<br/>
The fibonacci algorithms are in com.example.alriddle.question1.FibonacciAlgo

What resources does your implementation of the naive function waste?<br/>
- This implementation wastes stack space.

Implement fibonacci(n) with BigO(n). Call it fibonacci2
There is Scala collection that is well suited to solving this problem.<br/>
- This can be implemented with a Stream in the Scala collections implementation.<br/>
- In fact, a fibonacci implementation is provided which I copied verbatim. Why fix it if it ain't broke? :-)


## 2. Balancing Parentheses & Brackets

The given input of brackets and parentheses input is considered balanced when:

It is empty OR
The content inside all pairs of corresponding parentheses/brackets is balanced
Here are some examples:

isEverythingBalanced("") == true

isEverythingBalanced("([{}])") == true

isEverythingBalanced("[]([{}[]()])") == true

isEverythingBalanced("[") == false

isEverythingBalanced("}") == false

isEverythingBalanced("({)}") == false

isEverythingBalanced("}{") == false

isEverythingBalanced("){") == false



Tasks:

  Implement a function isEverythingBalanced(text: String): Boolean using any approach

  NOTE: A solution that uses regular expressions is considered INCORRECT.
  Bonus: Implement it recursively and immutably
  Bonus: Implement it non-recursively and immutably; call it isEverythingBalancedAlt


## 3. Object Oriented Programming

Design an object model (a set of interfaces) for Poker. Do not implement the game. The purpose of the model is to fascilitate an online Poker game system.

Food for thought:

How should game entities be represented?
How should game semantics be represented?
Can the type system help us enforce a higher degree of correctness?
How can your model prevent abuse/fraud/errors?
Decide how much of the problem you want to model
HINT: Take a progressive approach. We want a cohesive model; not necessarily an all-encompassingly complete one
Alternative: If you're not familiar or comfortable with Poker, you can model another problem of your choosing. Be sure to choose a complex enough and well scoped problem. With your domain, include the aim of your modeling. In the case of Poker, we want the model to help drive an online poker engine.

Tasks:

Create the necessary classes and traits
You're welcome to implement some things here, but it's not required
Bonus: where would you use implicit features of Scala to make this implementation better?
Bonus: discuss the issues with using implicit features of Scala?
4: Data Structures

Design a "last recently used cache" with the following properties:

a constructor that takes the recently used cache size fastCacheSize: Int
a retrieval method: def get(key: K): Option[V]
an update method: def put(key: K, value: V): Unit
the cache will not evict entries - this means that it grows unboundedly
BigO(1) get of the fastCacheSize recently used keys; BigO(fastCacheSize) == BigO(1)
BigO(log n) get of any key
BigO(log n) put of any key
5: Functional Programming Practice

Implement a list with the below methods.

Food for thought:

Use immutable data structures.
Structural sharing is a MUST.
Tasks:

Implement a recursive version of:
map in MyList
filter in MyList
zip in MyList
foldLeft in MyList
distinct in MyList
groupBy in MyList
Bonus: Make them tail-recursive in TRMyList where possible
Bonus: Implement ONLY foldLeft recursively in FLMyList; implement other methods in terms of foldLeft where possible
Super-Duper Bonus: BigO(1) append and prepend (when amortized)
6: Functional Programming Theory

Questions:

What makes a function pure? Give examples
What is referential transparency? What are its benefits?
How does a functional program deal with side-effects?
