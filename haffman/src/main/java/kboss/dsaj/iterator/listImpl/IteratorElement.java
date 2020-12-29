package kboss.dsaj.iterator.listImpl;

import kboss.dsaj.position.Position;

import kboss.dsaj.iterator.ExceptionNoSuchElement;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.list.List;

public class IteratorElement implements Iterator{
    private List list;
    private Position nextPosition;
    public IteratorElement(){list = null;}
    public IteratorElement(List list){
        this.list = list;
        if(list.isEmpty()) nextPosition = null;
        else nextPosition = list.first();
    }
    @Override
    public boolean hasNext() {
        return nextPosition != null;
    }
    @Override
    public Object getNext() throws ExceptionNoSuchElement {
        if(!hasNext()) throw new ExceptionNoSuchElement("no more elements");
        Position currentPosition = nextPosition;
        if(currentPosition == list.last()) nextPosition = null;
        else nextPosition = list.getNext(currentPosition);
        return currentPosition.getElement();
    }
}

