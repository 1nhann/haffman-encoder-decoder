package kboss.dsaj.queue.arrayImpl;

import kboss.dsaj.queue.ExceptionQueueEmpty;
import kboss.dsaj.queue.ExceptionQueueFull;
import kboss.dsaj.queue.Queue;

public class QueueImpl implements Queue{
    public static final int CAPACITY = 1000;
    protected int capacity;
    protected Object[] elements;
    protected int inIndex = 0;
    protected int outIndex = 0;

    public QueueImpl(int cap){capacity = cap;elements = new Object[capacity];}
    public QueueImpl(){this(CAPACITY);}

    @Override
    public int getSize() {
        return (inIndex - outIndex + capacity)%capacity;
    }
    @Override
    public boolean isEmpty() {
        return inIndex == outIndex;
    }
    @Override
    public void enqueue(Object element) throws ExceptionQueueFull {
        if(getSize() == capacity) throw new ExceptionQueueFull("queue full");
        elements[inIndex] = element;
        inIndex = (inIndex+1)%capacity;
    }
    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        Object ele = elements[outIndex];
        outIndex = (outIndex+1)%capacity;
        return ele;
    }
    @Override
    public Object front() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        return elements[outIndex];
    }

    @Override
    public void traversal() {
        for(int i = outIndex; i < inIndex; i++){
            System.out.println(elements[i] + "  ");
        }
        System.out.println();
    }
}