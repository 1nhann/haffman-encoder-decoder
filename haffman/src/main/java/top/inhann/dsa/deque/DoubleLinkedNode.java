package top.inhann.dsa.deque;

import top.inhann.dsa.position.Position;

public class DoubleLinkedNode implements Position{
    private Object element;
    private DoubleLinkedNode previous;
    private DoubleLinkedNode next;
    public DoubleLinkedNode(Object element,DoubleLinkedNode previous, DoubleLinkedNode next){
        this.element = element;
        this.previous = previous;
        this.next = next;
    }
    public DoubleLinkedNode(){
        this(null, null, null);
    }
    public Object getElement() {
        return element;
    }
    public DoubleLinkedNode getNext(){
        return next;
    }
    public DoubleLinkedNode getPrevious() {
        return previous;
    }
    public Object setElement(Object element) {
        this.element = element;
        return element;
    }
    public void setNext(DoubleLinkedNode next) {
        this.next = next;
    }
    public void setPrevious(DoubleLinkedNode previous) {
        this.previous = previous;
    }
}