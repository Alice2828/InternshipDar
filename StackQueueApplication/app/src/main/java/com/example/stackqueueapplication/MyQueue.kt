package com.example.stackqueueapplication

import java.util.*

class MyQueue {
    var myStack: Stack<Int> = Stack()

    /** Initialize your data structure here. */


    /** Push element x to the back of queue. */
    fun push(x: Int) {
        myStack.push(x)
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        val pop = myStack.firstElement()
        myStack.remove(myStack[0])
        return pop
    }

    /** Get the front element. */
    fun peek(): Int {
        return myStack.firstElement()
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return myStack.isEmpty()

    }

}
