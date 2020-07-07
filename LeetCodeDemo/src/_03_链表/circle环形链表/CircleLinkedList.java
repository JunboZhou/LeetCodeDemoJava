package _03_链表.circle环形链表;

public class CircleLinkedList<E> {
    private Node<E> first;
    private Node<E> last;
    private Node<E> current;
    // 元素数量
    private int size;
    static final int ELEMENT_NOT_FOUND = -1;

    private static class Node<E> {
        E elment;
        Node<E> pre;
        Node<E> next;

        public Node(E elment, Node<E> pre, Node<E> next) {
            this.elment = elment;
            this.pre = pre;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (pre == null) {
                sb.append("null");
            }
            sb.append(elment).append("_");
            if (next == null) {
                sb.append("null");
            } else {
                sb.append(next.elment);
            }
            return sb.toString();
        }
    }

    public void reset() {
        current = first;
    }

    public E next() {
        if (current == null) return null;
        current = current.next;
        return current.elment;
    }

    public E remove() {
        if (current == null) return null;
        Node<E> next = current.next;
        E element = remove(current);
        if (size == 0) {
            current = null;
        } else {
            current = next;
        }
        return element;
    }

    public void clear() {
        size = 0;
        first = null;
        last = null;
    }

    public E get(int index) {
        return node(index).elment;
    }

    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.elment;
        node.elment = element;
        return oldElement;
    }

    /***
     * 添加元素
     */
    public void add(int index, E element) {

        if (index == size) {  // 往最后面添加元素
            Node<E> oldLast = last;
            last = new Node<E>(element, oldLast, first);
            if (oldLast == null) { // 之前链表是空的
                first = first;
                first.pre = first;
                first.next = first;
            } else {
                oldLast.next = last;
                first.pre = last;
            }
        } else {
            Node<E> next = node(index);
            Node<E> pre = next.pre;
            Node<E> node = new Node<E>(element, pre, next);
            pre.next = node;
            next.pre = node;
            if (next == first) {   // 插入到 index == 0
                first = node;
            }
        }
        size++ ;
    }
    /**
     * 移除当前索引 element
     */
    public E remove(int index) {
        rangeCheck(index);
        return remove(node(index));
    }

    private E remove(Node<E> node) {
        if (size == 1) {
            first = null;
            last = null;
        } else {
            Node<E> pre = node.pre;
            Node<E> next = node.next;
            pre.next = next;
            next.pre = pre;
            if (node == first) {
                first = next;
            }
            if (node == last) {
                last = pre;
            }
        }

        size-- ;
        return node.elment;
    }

    /**
     * 获取index位置对应的节点对象
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        if (index < (size >> 1)) {
            Node<E> node = first;
            for (int i = 0; i < index; i++) {
                node = node.next;
            }
            return node;
        } else {
            Node<E> node = last;
            for (int i = size-1; i > index; i--) {
                node = node.pre;
            }
            return node;
        }
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
