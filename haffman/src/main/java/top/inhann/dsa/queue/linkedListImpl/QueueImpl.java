package top.inhann.dsa.queue.linkedListImpl;

import top.inhann.dsa.queue.ExceptionQueueEmpty;
import top.inhann.dsa.queue.ExceptionQueueFull;
import top.inhann.dsa.queue.Queue;

public class QueueImpl implements Queue{
    public static class Node{
        private Object element;
        private Node next;
        public Object getElement() {
            return element;
        }
        public Node getNext() {
            return next;
        }
        public void setElement(Object element) {
            this.element = element;
        }
        public void setNext(Node next) {
            this.next = next;
        }
        public Node(){
            this(null, null);
        }
        public Node(Object element, Node next){
            this.element = element;
            this.next = next;
        }
    }

    protected Node head;//out elements
    protected Node tail;//in elements
    protected int size;

    public QueueImpl(){
        head = null;
        tail = null;
        size = 0;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public void enqueue(Object element) throws ExceptionQueueFull {
        Node node = new Node(element, null);
        if(isEmpty()) {head = node;}
        else {tail.setNext(node);}
        tail = node;
        size++;
    }
    @Override
    public Object dequeue() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        Object ele = head.getElement();
        head = head.getNext();
        size--;
        if(isEmpty())tail = null;
        return ele;
    }
    @Override
    public Object front() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        return head.getElement();
    }
    @Override
    public void traversal() {
        Node node = head;
        while(node != null){
            System.out.println(node.getElement() + " ");
            node = node.getNext();
        }
    }
    @Override
    public int getSize() {
        return size;
    }
}