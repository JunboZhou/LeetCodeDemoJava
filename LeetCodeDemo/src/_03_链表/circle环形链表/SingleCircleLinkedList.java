package _03_链表.circle环形链表;

import _03_链表.sigle单向链表.SingleLinkList;

/**
 * 实现单向循环列表
 */
public class SingleCircleLinkedList<E> {

    private Node<E> first;
    // 元素数量
    private int size;
    static final int ELEMENT_NOT_FOUND = -1;

    private static class Node<E> {
        E elment;
        Node<E> next;

        public Node(E elment, Node<E> next) {
            this.elment = elment;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(elment).append("_").append(next.elment);
            return sb.toString();
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
     * get
     */
    public E get(int index) {
        return node(index).elment;
    }
    /**
     * set
     */
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.elment;
        node.elment = element;
        return oldElement;
    }
    /**
     * 往index位置添加
     */
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if (index == 0) {
            Node<E> newFirst = new Node<E>(element, first);
            Node<E> last = (size == 0) ? newFirst : node(size - 1);
            last.next = newFirst;
            first = newFirst;
        } else {
            Node<E> pre = node(index - 1);
            Node<E> newNode = new Node<E>(element, pre.next);
            pre.next = newNode;
        }
        size++ ;
    }

    /**
     * 删除
     */
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node = first;
        if (index == 0) {
            if (size == 1) {
                first = null;
            } else {
                Node<E> last = node(size - 1);
                first = first.next;
                last.next = first;
            }
        } else {
            Node<E> pre = node(index - 1);
            node = pre.next;
            pre.next = node.next;
        }
        size-- ;
        return node.elment;
    }

    /**
     * 获取元素索引
     */
    public int indexOf(E element) {
        if (element == null) {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (node.elment == null) return i;
                node = node.next;
            }
        } else {
            Node<E> node = first;
            for (int i = 0; i < size; i++) {
                if (element.equals(node.elment)) return i;
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }
    /**
     * 获取index位置对应的节点对象
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
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
            string.append(node);
            node = node.next;
        }
        string.append("]");
        return string.toString();
    }
}
