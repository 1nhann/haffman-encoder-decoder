package top.inhann.dsa.deque;

import top.inhann.dsa.queue.ExceptionQueueEmpty;

public class DequeImpl implements Deque{
    protected DoubleLinkedNode header;
    protected DoubleLinkedNode tailer;
    protected int size;

    public DequeImpl(){
        header = new DoubleLinkedNode();
        tailer = new DoubleLinkedNode();
        header.setNext(tailer);
        tailer.setPrevious(header);
        size = 0;
    }
    public int getSize() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public Object first() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        return header.getNext().getElement();
    }
    @Override
    public Object last() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        return tailer.getPrevious().getElement();
    }
    @Override
    public void insertFirst(Object element) {
        DoubleLinkedNode node = new DoubleLinkedNode(element, header, header.getNext());
        header.getNext().setPrevious(node);
        header.setNext(node);
        size++;
    }
    @Override
    public void insertLast(Object element) {
        DoubleLinkedNode node = new DoubleLinkedNode(element, tailer.getPrevious(), tailer);
        tailer.getPrevious().setNext(node);
        tailer.setPrevious(node);
        size++;
    }
    @Override
    public Object removeFirst() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        Object ele = header.getNext().getElement();
        header.getNext().getNext().setPrevious(header);
        header.setNext(header.getNext().getNext());
        size--;
        return ele;
    }
    @Override
    public Object removeLast() throws ExceptionQueueEmpty {
        if(isEmpty()) throw new ExceptionQueueEmpty("queue empty");
        Object ele = tailer.getPrevious().getElement();
        tailer.getPrevious().getPrevious().setNext(tailer);
        tailer.setPrevious(tailer.getPrevious().getPrevious());
        size--;
        return ele;
    }
    @Override
    public void traversal() {
        DoubleLinkedNode node = header.getNext();
        while(node != tailer){
            System.out.println(node.getElement() + "  ");
            node = node.getNext();
        }
        System.out.println();
    }
}