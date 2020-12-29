package kboss.dsaj.deque;

import kboss.dsaj.queue.ExceptionQueueEmpty;

public interface Deque {
    public int getSize();
    public boolean isEmpty();
    public Object first() throws ExceptionQueueEmpty;
    public Object last() throws ExceptionQueueEmpty;
    public void insertFirst(Object element);
    public void insertLast(Object element);
    public Object removeFirst() throws ExceptionQueueEmpty;
    public Object removeLast() throws ExceptionQueueEmpty;
    public void traversal();
}