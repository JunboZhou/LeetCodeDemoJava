package _04_栈;

/***
 * 实现 栈
 */

import java.util.ArrayList;

public class Stack<E> {

    private ArrayList<E> list = new ArrayList<E>();

    public void clear() {
        list.clear();
    }

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public void push(E element) {
        list.add(list.size() - 1, element);
    }

    public E pop() {
        return list.remove(list.size() - 1);
    }

    public E top() {
        return list.get(list.size() - 1);
    }
}
