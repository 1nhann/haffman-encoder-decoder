package top.inhann.dsa.list.dlinkedImpl;

import top.inhann.dsa.list.List;
import top.inhann.dsa.deque.DoubleLinkedNode;
import top.inhann.dsa.iterator.Iterator;
import top.inhann.dsa.iterator.listImpl.IteratorElement;
import top.inhann.dsa.iterator.listImpl.IteratorPosition;
import top.inhann.dsa.list.ExceptionListEmpty;
import top.inhann.dsa.list.ExceptionPositionInvalid;
import top.inhann.dsa.position.Position;
import top.inhann.dsa.vector.ExceptionBoundaryViolation;

public class ListImpl implements List{
    protected int size;
    protected DoubleLinkedNode header;
    protected DoubleLinkedNode tailer;
    public ListImpl(){
        size = 0;
        header = new DoubleLinkedNode();
        tailer = new DoubleLinkedNode();
        header.setNext(tailer);
        tailer.setPrevious(header);
    }
    protected DoubleLinkedNode checkPosition(Position position) throws ExceptionPositionInvalid{
        if(position == null) throw new ExceptionPositionInvalid("position passed is null");
        if(position == header) throw new ExceptionPositionInvalid("position passed is header");
        if(position == tailer) throw new ExceptionPositionInvalid("position passed is tailer");
        DoubleLinkedNode node = (DoubleLinkedNode) position;
        return node;
    }

    public int getSize() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public Position first() throws ExceptionPositionInvalid {
        if(isEmpty()) throw new ExceptionListEmpty("list empty");
        return header.getNext();
    }
    @Override
    public Position last() throws ExceptionPositionInvalid {
        if(isEmpty()) throw new ExceptionListEmpty("list empty");
        return tailer.getPrevious();
    }
    @Override
    public Position getNext(Position position) throws ExceptionPositionInvalid, ExceptionBoundaryViolation {
        DoubleLinkedNode node = checkPosition(position);
        DoubleLinkedNode next = node.getNext();
        if(next == tailer) throw new ExceptionBoundaryViolation("boundary violation");
        return next;
    }
    @Override
    public Position getPrevious(Position position) throws ExceptionBoundaryViolation, ExceptionPositionInvalid {
        DoubleLinkedNode node = checkPosition(position);
        DoubleLinkedNode previous = node.getPrevious();
        if(previous == null) throw new ExceptionBoundaryViolation("boundary violation");
        return previous;
    }
    @Override
    public Position insertBefore(Position position, Object element) throws ExceptionPositionInvalid {
        DoubleLinkedNode node = checkPosition(position);
        size++;
        DoubleLinkedNode newNode = new DoubleLinkedNode(element,node.getPrevious(),node);
        node.getPrevious().setNext(newNode);
        node.setPrevious(newNode);
        return newNode;
    }
    @Override
    public Position insertAfter(Position position, Object element) throws ExceptionPositionInvalid {
        DoubleLinkedNode node = checkPosition(position);
        size++;
        DoubleLinkedNode newNode = new DoubleLinkedNode(element,node,node.getNext());
        node.getNext().setPrevious(newNode);
        node.setNext(newNode);
        return newNode;
    }
    @Override
    public Position insertFirst(Object element) {
        size++;
        DoubleLinkedNode newNode = new DoubleLinkedNode(element,header,header.getNext());
        header.getNext().setPrevious(newNode);
        header.setNext(newNode);
        return newNode;
    }
    @Override
    public Position insertLast(Object element) {
        size++;
        DoubleLinkedNode newNode = new DoubleLinkedNode(element,tailer.getPrevious(),tailer);
        tailer.getPrevious().setNext(newNode);
        tailer.setPrevious(newNode);
        return newNode;
    }
    @Override
    public Object remove(Position position) throws ExceptionPositionInvalid {
        DoubleLinkedNode node = checkPosition(position);
        size--;
        DoubleLinkedNode preNode = node.getPrevious();
        DoubleLinkedNode nextNode = node.getNext();
        preNode.setNext(nextNode);
        nextNode.setPrevious(preNode);
        node.setNext(null);
        node.setPrevious(null);
        return node.getElement();
    }
    @Override
    public Object removeFirst() {
       return remove(header.getNext());
    }
    @Override
    public Object removeLast() {
        return remove(tailer.getPrevious());
    }
    @Override
    public Object replace(Position position, Object element) throws ExceptionPositionInvalid {
        DoubleLinkedNode node = checkPosition(position);
        Object ele = node.getElement();
        node.setElement(element);
        return ele;
    }
    @Override
    public Iterator positions() {
        return new IteratorPosition(this);
    }
    @Override
    public Iterator elements() {
        return new IteratorElement(this);
    }
}