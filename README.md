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


### 2. Balancing Parentheses & Brackets

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


### 3. Object Oriented Programming

Design an object model (a set of interfaces) for Poker. Do not implement the game. The purpose of the model is to fascilitate an online Poker game system.

Food for thought:

How should game entities be represented?<br/>
How should game semantics be represented? <br/>
Can the type system help us enforce a higher degree of correctness?<br/>
How can your model prevent abuse/fraud/errors?<br/>
Decide how much of the problem you want to model   <br/>
HINT: Take a progressive approach. We want a cohesive model; not necessarily an all-encompassingly complete one<br/>
Alternative: If you're not familiar or comfortable with Poker, you can model another problem of your choosing. Be sure to choose a complex enough and well scoped problem.<br/>
With your domain, include the aim of your modeling. In the case of Poker, we want the model to help drive an online poker engine.

Tasks:

Create the necessary classes and traits. <br/>
You're welcome to implement some things here, but it's not required. <br/>
Bonus: where would you use implicit features of Scala to make this implementation better? <br/>
Bonus: discuss the issues with using implicit features of Scala?<br/>

*The necessary classes are in com.example.alriddle.question3. The package object contains most of the implementation*
*I used implicits to define potential wild cards in assessing the strength of hands. One could define all the jokers, deuces, etc implicitly as wild cards* <br/>
*I also used implicits to convert an OrdinalCard to an Int. I wanted to be able to determine how an Ace was evaluated for Aces High, Aces Low Scenarios.*<br/>
*There's still some cleaning up to do. I was hoping to override the implicit defs I had defined. A better way would be to use an implicit function that converts a card into an Int<br/>*

### 4: Data Structures

Design a "last recently used cache" with the following properties:

a constructor that takes the recently used cache size fastCacheSize: Int<br/>
a retrieval method: def get(key: K): Option[V]   <br/>
an update method: def put(key: K, value: V): Unit <br/>
the cache will not evict entries - this means that it grows unboundedly  <br/>
BigO(1) get of the fastCacheSize recently used keys; BigO(fastCacheSize) == BigO(1)<br/>
BigO(log n) get of any key <br/>
BigO(log n) put of any key  <br/>

*This was solved with the class com.example.alriddle.question4.LRUCache*<br/>
*It was created using the java implementation of a LinkedHashMap for the cache and a treemap for the bulk data store*

### 5: Functional Programming Practice

Implement a list with the below methods.<br/>

Food for thought:

Use immutable data structures.<br/>
Structural sharing is a MUST.<br/>
Tasks:

Implement a recursive version of:

map in MyList  <br/>
filter in MyList <br/>
zip in MyList    <br/>
foldLeft in MyList <br/>
distinct in MyList <br/>
groupBy in MyList  <br/>
Bonus: Make them tail-recursive in TRMyList where possible <br/>
Bonus: Implement ONLY foldLeft recursively in MyList; implement other methods in terms of foldLeft where possible <br/>
Super-Duper Bonus: BigO(1) append and prepend (when amortized)<br/>

*The results are in com.example.alriddle.question5.TRMyList. There are accompanying unit tests. All the functions are tail recursively implemented with foldLeft.*<br/>
*The only issue was getting BigO(1) append to work with immutable structures. Seems impossible.*


6: Functional Programming Theory

Questions:

What makes a function pure? Give examples <br/>
What is referential transparency? What are its benefits?
*A function is pure if calling it with referential transparent arguments is also referential transparent.*<br/>
*Referential transparency allows one to substitute a function with the value of the function's result in an application*<br/>

Example:<br/>

```
    def refTranspFunc[T]( a : T, b : T, anotherReferentiallyTransparentFunction : (T,T)  => T ) = anotherReferentiallyTransparentFunction( a, b )
```
<br/>
```
    refTranspFunc("My name ", "is Henry", (x:String,y:String) => (x+y))
```

<br/>
*In the above function, if a, b and anotherReferentiallyTransparentFunction are all referentially transparent,*</br>
*refTranspFunc is also referentially transparent.* <br/>
*Referentially Rransparency is achieved if the functions do not modify state outside their scope as well as enforcing immutability in data structures.*<br/>
* String addition is referentially transparent.*

How does a functional program deal with side-effects?


*A functional program should use immutable data structures. Instead of modifying data structures in place, new copies of data should be created.* <br/>
*A common example of a side effect is logging or printing to the console.*<br/>
*Scalaz provides an IO Monad that allows one to capture IO operations in an immutable data structure which is returned by the program.* <br/>
*The implication is that this IO Monad would have to be passed to/returned by every function that needs IO operations.*



