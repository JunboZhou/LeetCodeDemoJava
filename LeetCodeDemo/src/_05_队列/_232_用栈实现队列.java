package _05_队列;

import java.util.Stack;

/**
 * 用栈实现队列
 * https://leetcode-cn.com/problems/implement-queue-using-stacks/
 *
 */
public class _232_用栈实现队列 {
    /** Initialize your data structure here. */

    public Stack<Integer> inStack = new Stack<>();
    public Stack<Integer> outStack = new Stack<>();

//    public MyQueue() {
//
//    }

    /** 入队 */
    public void push(int x) {
        while (!outStack.isEmpty()) {
            inStack.push(outStack.pop());
        }
        inStack.push(x);
    }

    /** 从队列首部移除元素 */
    public int pop() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.pop();
    }

    /** 返回队列首部的元素 */
    public int peek() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.peek();
    }

    /** 返回队列是否为空 */
    public boolean empty() {
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
        return outStack.isEmpty();
    }
}
