package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T>, Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;

        public ArrayDequeIterator() {
            pos = nextFirst + 1;
        }

        @Override
        public boolean hasNext() {
            return pos != nextLast;
        }

        @Override
        public T next() {
            T returnItem = items[pos];
            pos = (pos + 1) % items.length;
            return returnItem;
        }
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o instanceof ArrayDeque) {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            if (this.size != other.size) {
                return false;
            }
            for (int i = 0; i < size; i += 1) {
                T x = this.get(i);
                T y = other.get(i);
                if (x != y) return false;
            }
            return true;
        }
        return false;
    }

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    @Override
    public void addFirst(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = x;
        nextFirst = (nextFirst - 1 + items.length) % items.length;
        size += 1;
    }

    @Override
    public void addLast(T x) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = x;
        nextLast = (nextLast + 1) % items.length;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int i;
        for (i = 0; i < size; i += 1) {
            int index = (nextFirst + 1 + i) % items.length;
            System.out.print(items[index] + " ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (isEmpty())
            return null;
        if ((size == items.length / 4) && (size >= 16)) {
            resize(items.length / 4);
        }
        int index = (nextFirst + 1) % items.length;
        T item = items[index];
        items[index] = null;
        nextFirst = index;
        size -= 1;
        return item;
    }

    @Override
    public T removeLast() {
        if (isEmpty())
            return null;
        if ((size == items.length / 4) && (size >= 16)) {
            resize(items.length / 4);
        }
        int index = (nextLast - 1 + items.length) % items.length;
        T item = items[index];
        items[index] = null;
        nextLast = index;
        size -= 1;
        return item;
    }

    @Override
    public T get(int index) {
        if (size < 0 || index >= size)
            return null;
        int actualIndex = (nextFirst + 1 + index) % items.length;
        return items[actualIndex];
    }

    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i += 1) {
            int index = (nextFirst + 1 + i) % items.length;
            newItems[i] = items[index];
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }
}
