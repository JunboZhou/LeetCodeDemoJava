package _05_队列;

import java.util.LinkedList;
import java.util.List;

/***
 * 双端队列
 */
public class Deque <E> {
    private LinkedList<E> list = new LinkedList<>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void clear() {
        list.clear();
    }

    // 队尾入队
    public void enQueueRear(E element) {
        list.add(list.size()- 1, element);
    }

    // 从队头出队
    public E deQueueFront() {
        return list.remove(0);
    }

    // 从队头入队
    public void enQueueFront(E element) {
        list.add(0, element);
    }

    // 从队尾出队
    public E deQueueRear() {
        return list.remove(list.size()-1);
    }

    // 获得队头元素
    public E front() {
        return list.get(0);
    }

    // 获得队尾元素
    public E rear() {
        return list.peekLast();
    }
}
