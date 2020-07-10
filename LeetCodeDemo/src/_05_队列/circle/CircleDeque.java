package _05_队列.circle;

import jdk.nashorn.internal.ir.OptimisticLexicalContext;
import sun.plugin.cache.OldCacheEntry;

import java.net.Inet4Address;

/**
 * 循环双端队列
 */
public class CircleDeque<E> {

    private int front;
    private int size;
    private E[] elements;
    private static final int DEFAULT_CAPACITY = 10;

    public CircleDeque() {
        elements = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            elements[i] = null;
        }
        size = 0;
        front = 0;
    }

    /**
     * 从尾部入队
     * @param element
     */
    public void enQueueRear(E element) {
        ensureCapacity(size + 1);

        elements[index(size)] = element;
        size++ ;
    }

    /**
     * 从头部出队
     */
    public E deQueueFront() {
        E first = elements[front];
        elements[front] = null;
        front = index(1);
        size-- ;
        return first;
    }

    /**
     * 从头部入队
     * @param element
     */
    public void enQueueFront(E element) {
        ensureCapacity(size + 1);
        front = index(-1);
        elements[front] = element;
        size++;
    }

    /**
     * 从尾部出队
     */
    public E deQueueRear() {
        E lastElement = elements[index(size - 1)];
        elements[index(size - 1)] = null;
        size-- ;
        return lastElement;
    }

    public E front() {
        return elements[front];
    }

    public E rear() {
        return elements[index(size - 1)];
    }

    private int index(int index) {
        index += front;
        //
        /***
         * 考虑这种情况
         *  索引 0   1    2    3    4     5   6
         *  元素 11  22   33   44   55    66
         *  往头部入队的时候 front = 0 ; index(-1) + front = -1 < 0
         */
        if (index < 0) return index + elements.length;
        return index % elements.length;
    }

    /**
     * 保证要有capacity的容量
     * @param capacity
     */
    private void ensureCapacity(int capacity) {
        int oldCapacity = elements.length;
        if (oldCapacity >= capacity) return;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        E[] newElements = (E[]) new Object[newCapacity];
        for (int i = 0; i < oldCapacity; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;

        // 重置头节点位置
        front = 0;
    }
}
