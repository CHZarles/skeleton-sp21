package deque;

import java.util.Iterator;

public interface Deque<T> {
   public void addFirst(T t);
   public void addLast(T t);
   public boolean isEmpty();
   public T removeFirst();
   public T removeLast();
   public T get(int index);
   public int size();
   public void printDeque();

   public boolean equals(Object o);
   public Iterator<T> iterator();


}
