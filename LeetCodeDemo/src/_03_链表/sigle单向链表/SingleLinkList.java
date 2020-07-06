package _03_链表.sigle单向链表;

import javax.jws.WebParam;
import javax.swing.*;
import javax.xml.soap.Node;
import javax.xml.stream.events.NotationDeclaration;

public class SingleLinkList<E> {
    // 元素数量
    private int size;
    // 头节点
    private Node<E> first;
    static final int ELEMENT_NOT_FOUND = -1;

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }
    }

    /**
     * 清空
     */
    public void clear() {
        first = null;
        size = 0;
    }

    /**
     * 是否为空
     */
    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    /**
     * get
     * @param index
     * @return
     */
    public E get(int index) {
        return node(index).element;
    }

    /**
     * set 返回旧值
     * @param index
     * @param element
     */
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    /**
     * 尾部添加
     * @param index
     * @param element
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            first = new Node<E>(element, first);
        } else {
            Node<E> pre = node(index-1);
            pre.next = new Node<E>(element, pre.next);
        }
        size++;
    }

    /**
     * 指定索引删除
     */
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            first = first.next;
        } else {
            Node<E> pre = node(index-1);
            node = pre.next;
            pre.next = node.next;
         }
        size --;
        return node.element;
    }

    /**
     * 元素返回索引值
     */
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.element == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.element)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    /***
     * 获取index位置的节点对象
     */
    private Node<E> node(int index) {
        rangeCheck(index);

        Node<E> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    /**
     * 检查索引
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            outOfBounds(index);
        }
    }

    private void outOfBounds(int index) {
        throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    private void rangeCheckForAdd(int index) {
        if (index < 0 || index > size) {
            outOfBounds(index);
        }
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();
        string.append("size=").append(size).append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) string.append(",");
            string.append(node.element);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
