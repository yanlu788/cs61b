package deque;

import java.util.Iterator;
import java.util.Objects;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {

    public class LinkedDequeIterator implements Iterator<T> {
        private int pos;

        public LinkedDequeIterator() {
            pos = 0;
        }

        @Override
        public boolean hasNext() {
            return pos < size;
        }

        @Override
        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LinkedListDeque)) return false;

        LinkedListDeque<?> other = (LinkedListDeque<?>) o;  // 通配符
        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++) {
            // 现在需要Object类型，因为不知道other的具体泛型
            Object x = this.get(i);
            Object y = other.get(i);
            if (!Objects.equals(x, y)) return false;
        }
        return true;
    }

    public class IntNode {
        public T item;
        public IntNode pre;
        public IntNode next;

        public IntNode(T f, IntNode p, IntNode n) {
            item = f;
            pre = p;
            next = n;
        }
    }

    private IntNode sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        sentinel.pre = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T x) {
        IntNode now = new IntNode(x, sentinel, sentinel.next);
        sentinel.next.pre = now;
        sentinel.next = now;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        IntNode now = new IntNode(x, sentinel.pre, sentinel);
        sentinel.pre.next = now;
        sentinel.pre = now;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0)
            return true;
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        IntNode now = sentinel;
        while (now.next != sentinel) {
            now = now.next;
            System.out.print(now.item + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        IntNode now = sentinel.next;
        sentinel.next.next.pre = sentinel;
        sentinel.next = sentinel.next.next;
        if (now == sentinel)
            return null;
        size -= 1;
        return now.item;
    }

    @Override
    public T removeLast() {
        IntNode now = sentinel.pre;
        sentinel.pre.pre.next = sentinel;
        sentinel.pre = sentinel.pre.pre;
        if (now == sentinel)
            return null;
        size -= 1;
        return now.item;
    }

    @Override
    public T get(int index) {
        IntNode now = sentinel;
        for (int i = 0; i <= index; i += 1) {
            now = now.next;
            if (now == sentinel)
                return null;
        }
        return now.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(IntNode current, int index) {
        // 基准情况：到达目标索引
        if (index == 0) {
            return current.item;
        }
        // 递归情况：移动到下一个节点，索引减1
        return getRecursiveHelper(current.next, index - 1);
    }
}
