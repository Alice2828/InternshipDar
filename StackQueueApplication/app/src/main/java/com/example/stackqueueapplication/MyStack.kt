package com.example.stackqueueapplication

import java.util.*

class MyStack {
    var myStack: Queue<Int> = ArrayDeque()

    /** Initialize your data structure here. */

    /** Push element x onto stack. */
    fun push(x: Int) {
        myStack.add(x)
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        val pop = myStack.last()
        myStack.remove(myStack.last())
        return pop
    }

    /** Get the top element. */
    fun top(): Int {
        return myStack.last()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return myStack.isEmpty()
    }

}
