package com.example.stackqueueapplication

import java.util.*

class MyQueue {

    lateinit var stackSecond: Stack<Int>
    /** Initialize your data structure here. */


    /** Push element x to the back of queue. */
    fun push(x: Int) {
        var myStack: Stack<Int> = Stack()
        myStack.push(x)
        stackSecond = Stack()
        for (i in myStack.size-1 downTo 0) {
            stackSecond.add(myStack[i])
        }
    }

    /** Removes the element from in front of queue and returns that element. */
    fun pop(): Int {
        return stackSecond.pop()
    }

    /** Get the front element. */
    fun peek(): Int {
        return stackSecond.peek()
    }

    /** Returns whether the queue is empty. */
    fun empty(): Boolean {
        return stackSecond.empty()
    }

}
