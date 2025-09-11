package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node<T> {
        T first;
        Node<T> next;
        Node<T> prev;

        Node(T first, Node<T> next, Node<T> prev) {
            this.first = first;
            this.next = next;
            this.prev = prev;
        }
    }

    int size = 0;
    private Node<T> sentinel; // next point to head, prev point to tail

    public LinkedListDeque() {
        sentinel = new Node<T>(null, null, null);
    }

    @Override
    public void addFirst(T t) {
        Node<T> newNode = new Node<T>(t, null, null);
        if (isEmpty()) {
            sentinel.next = newNode;
            newNode.prev = newNode;
            newNode.next = sentinel;
            sentinel.prev = newNode;
            size += 1;
            return;
        }
        Node<T> oldHead = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next = newNode;
        newNode.next = oldHead;
        oldHead.prev = newNode;

        size += 1;
    }

    @Override
    public void addLast(T t) {
        Node<T> newNode = new Node<T>(t, null, null);
        if (isEmpty()) {
            sentinel.next = newNode;
            newNode.prev = sentinel;
            newNode.next = sentinel;
            sentinel.prev = newNode;
            size += 1;
            return;
        }
        Node<T> oldTail = sentinel.prev;
        newNode.prev = oldTail;
        oldTail.next = newNode;
        newNode.next = sentinel;
        sentinel.prev = newNode;


        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return sentinel.prev == null && sentinel.next == null;
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T val = sentinel.next.first;
        if (size == 1) {
            sentinel.next = null;
            sentinel.prev = null;
        } else {
            Node<T> delHead = sentinel.next;
            sentinel.next = delHead.next;
            delHead.next.prev = sentinel;
        }
        size -= 1;

        return val;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T val = sentinel.prev.first;
        if (size == 1) {
            sentinel.prev = null;
            sentinel.next = null;
        } else {
            Node<T> delTail = sentinel.prev;
            sentinel.prev = delTail.prev;
            delTail.prev.next = sentinel;
        }
        size -= 1;
        return val;
    }

    public T get(int index) {
        if (isEmpty() ||  index < 0 || index >= size)
            return null;
        // iter list
        Node<T> p = sentinel.next;
        for (int i = 0; i <= index ; i++) {
            p = p.next;
        }
        return p.first;
    }

    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        if (isEmpty()) {
            System.out.println("null");
        }
        Node<T> p = sentinel.next;
        while (p != sentinel) {
            System.out.print(p.first + "  <-> ");
            p = p.next;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || index >= size) return null;
        return getRecursiveHelper(sentinel.next, index);
    }

    private T getRecursiveHelper(Node<T> node, int index) {
        if (index == 0) {
            return node.first;
        }
        return getRecursiveHelper(node.next, index - 1);
    }
}
