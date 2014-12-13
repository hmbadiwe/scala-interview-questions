package com.example.alriddle.question5

/**
 * Created by hmbadiwe on 12/12/14.
 */
sealed trait TRMyList[+T]{
  val head : Option[MyNode[T]]

  def reverse : TRMyList[T] = {
    def reverseHelp( accum : Option[MyNode[T]], optionNode : Option[MyNode[T]] ) : Option[MyNode[T]] = {
      if( optionNode == None) accum
      else {
        reverseHelp( Some( MyNode( optionNode.get.value, accum)), optionNode.get.nextNode)
      }
    }
    TRMyList(reverseHelp(None, head))
  }

  def foldLeft[U]( accum : U )( fn : (U, T) => U ) : U = {
    def foldLeftHelp( accum : U, optionNode : Option[MyNode[T]] ) : U = {
        if( optionNode == None ) accum
        else {
            val fnResult = fn( accum, optionNode.get.value)
            foldLeftHelp( fnResult, optionNode.get.nextNode )
        }
    }
    foldLeftHelp( accum, head  )
  }
  def get( index : Int) : Option[T] = {
    require( index >= 0 )
     def getHelp(  index : Int, optionNode : Option[MyNode[T]] ) : Option[T] = {
       if( optionNode == None )None
       else{
         if( index == 0 ) optionNode.map( _.value )
         else getHelp( index - 1, optionNode.get.nextNode)
       }
     }
     getHelp( index, head )
  }
  def append[U >: T]( value : U) : TRMyList[U] = {
      val nodeList = reverse.foldLeft(MyNode(value, None)){(accum,elem)=>
         MyNode(elem, Option(accum))
      }
    TRMyList(Some(nodeList))
  }
  def prepend[U >: T]( value : U) : TRMyList[U] = TRMyList(Some(MyNode(value, head)))

  def toList : List[T] = {
    foldLeft(List.empty[T]){ (accum, elem) => elem :: accum}.reverse
  }

  def map[U]( fn : T => U) : TRMyList[U] = {
    val optionalNodeList = foldLeft(Option.empty[MyNode[U]]){ (accum, elem) =>
       if( elem == None) accum
      else Option( MyNode( fn(elem), accum ) )
    }

    TRMyList(optionalNodeList).reverse
  }

  def filter( fn : T => Boolean ) : TRMyList[T] = {
    val filteredNodes = foldLeft(Option.empty[MyNode[T]]){ (accum, elem) =>
      if( fn(elem) ) {
         Some(MyNode(elem, accum))
      }
      else{
        accum
      }
    }
   TRMyList( filteredNodes ).reverse
  }

  def distinct : TRMyList[T] = {
    val distinctSet = foldLeft(Set.empty[T]){ ( accum, elem ) => accum + elem }
    TRMyList(distinctSet.toSeq:_*)
  }

  def size : Int = foldLeft(0){(accum,elem) => accum + 1}

  def groupBy[U]( fn : T => U ) : Map[U, TRMyList[T]] = {
    foldLeft( Map.empty[U,TRMyList[T]]){ (accum,elem) =>
      val key = fn(elem)
      accum.get(key).map{ list => accum.updated(key,list.prepend(elem))} getOrElse {
        accum + ( key -> TRMyList(elem) )
      }
    }
  }
  def zip[U]( theirs : TRMyList[U]) : TRMyList[(T, U)] = {
    val minSize = Math.min( size, theirs.size )
    if( minSize == 0 ) TRMyList.empty[(T,U)]
    else{
      val takeFromMine = take(minSize)
      val takeFromTheirs = theirs.take(minSize)
      
      val listTuplePair = takeFromMine.foldLeft( ( takeFromTheirs.head, List.empty[ (T, U) ]) ) { ( accum, elem ) =>
        accum._1.map { t =>
          (t.nextNode, (elem, t.value) :: accum._2)
        } getOrElse ((None, accum._2))
      }
      TRMyList(listTuplePair._2 :_*).reverse
    }
  }

  def take( number : Int ) : TRMyList[T]= {
    val takeTuple = foldLeft( ( number, Option.empty[MyNode[T]] ) )( (accum, elem) =>{
         if( accum._1 == 0 ){
           ( (0, accum._2) )
         }
         else{
           ( ( accum._1 -1, Option(MyNode(elem, accum._2 ) ) ) )
         }
    })
    TRMyList(takeTuple._2).reverse
  }
}

object TRMyList{
  def apply[T]( n : Option[MyNode[T]]) : TRMyList[T] = new TRMyList[T] { val head = n}
  def apply[T]( elem : T* ) : TRMyList[T]  = new TRMyList[T]{
    val head = Option {
      val revList = elem.toList.reverse
      revList.tail.foldLeft( MyNode(revList.head, None)){ (accum,elem) => MyNode(elem, Option(accum)) }
    }
  }
  def empty[T]  : TRMyList[T] = new TRMyList[T] { val head = Option.empty[MyNode[T]]}
}


sealed case class MyNode[+T]( value : T, nextNode : Option[MyNode[T]])
