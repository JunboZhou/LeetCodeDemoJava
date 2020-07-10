package _05_队列.circle;

import java.util.concurrent.ForkJoinPool;

/**
 * 循环队列
 * 利用数组实现
 */
public class CircleQueue <E>{

    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleQueue() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[index(i)] = null;
        }
        front = 0;
        size = 0;
    }

    // 入队
    public void enQueue(E element) {
        ensureCapacity(size + 1);
        elements[index(size)] = element;
        size++ ;
    }

    // 出队
    public E deQueue() {
        E frontElement = elements[front];
        elements[front] = null;
        front = index(1);
        size-- ;
        return frontElement;
    }

    // 队列头元素
    public E front() {
        return elements[front];
    }

    /***
     *  索引 0  1   2   3   4   5   6
     *  元素       11   22  33  44  55
     *  此时如果往 [11, 22, 33, 44, 55] 后面加元素 应该加到 索引为 0  的位置
     *  需要进行索引转换
     * @return
     */
    private int index(int index) {
        return (front + index) % elements.length;
    }

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        // 扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object();
        for (int i = 0; i < elements.length; i++) {
            newElements[i] = elements[i];
        }

        elements = newElements;
        // 重置头节点
        front = 0;
    }
}
