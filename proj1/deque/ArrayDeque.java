package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    private T[] array;
    private int head;
    private int tail;


    public ArrayDeque() {
        array = (T[]) new Object[8];
        // tail point to the position that have no element
        head = tail = 0;
    }

    private void resize(int newSize) {
        T[] newArray = (T[]) new Object[newSize];
        int size = size();
        if (tail < head) {
            // Copy from head to end
            int rightLen = array.length - head;
            System.arraycopy(array, head, newArray, 0, rightLen);
            // Copy from 0 to tail-1
            System.arraycopy(array, 0, newArray, rightLen, tail);
        } else {
            // Copy from head to tail-1
            System.arraycopy(array, head, newArray, 0, size);
        }
        head = 0;
        tail = size;
        array = newArray;
    }

    @Override
    public void addFirst(T t) {
        // check capacity
        if (size() == array.length) {
            resize(array.length * 2);
        }
        int idx = (head - 1 + array.length) % array.length;
        head = idx;
        array[idx] = t;
    }

    @Override
    public void addLast(T t) {
        if (size() == array.length - 1) {
            resize(array.length * 2);
        }
        array[tail] = t;
        tail = (tail + 1 + array.length) % array.length;

    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T val = array[head];
        head = (head + 1) % array.length;

        // shrink
        if (array.length > 16 && size() * 4 < array.length) {
            resize(array.length / 2);
        }
        return val;
    }


    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        tail = (tail - 1 + array.length) % array.length;
        T val = array[tail];
        return val;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size())
            return null;
        // calc diff
        int idx = (head + index) % array.length;
        return array[idx];
    }

    @Override
    public int size() {
        if (tail < head) {
            return tail + array.length - head;
        }
        return tail - head;
    }

    @Override
    public void printDeque() {

        for (int i = head; i != tail; i = (i + 1) % array.length) {
            System.out.print(array[i] + " <-> ");
        }

    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }
}
