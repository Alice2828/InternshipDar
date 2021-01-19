package com.example.stackqueueapplication

import java.util.*

class MyStack {
    var myQueue: Queue<Int> = LinkedList()

    /** Initialize your data structure here. */

    /** Push element x onto stack. */
    fun push(x: Int) {
        myQueue.add(x)
    }

    /** Removes the element on top of the stack and returns that element. */
    fun pop(): Int {
        val pop = myQueue.last()
        myQueue.remove(myQueue.last())
        return pop
    }

    /** Get the top element. */
    fun top(): Int {
        return myQueue.last()
    }

    /** Returns whether the stack is empty. */
    fun empty(): Boolean {
        return myQueue.isEmpty()
    }

}
