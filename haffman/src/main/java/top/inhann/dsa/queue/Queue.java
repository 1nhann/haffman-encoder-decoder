package top.inhann.dsa.queue;

public interface Queue {
    public int getSize();
    public boolean isEmpty();
    public Object front() throws ExceptionQueueEmpty;
    public void enqueue(Object element) throws ExceptionQueueFull;
    public Object dequeue() throws ExceptionQueueEmpty;
    public void traversal();
}