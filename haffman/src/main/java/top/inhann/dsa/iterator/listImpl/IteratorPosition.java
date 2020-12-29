package top.inhann.dsa.iterator.listImpl;

import top.inhann.dsa.iterator.ExceptionNoSuchElement;
import top.inhann.dsa.iterator.Iterator;
import top.inhann.dsa.list.List;
import top.inhann.dsa.position.Position;

public class IteratorPosition implements Iterator{
    private List list;
    private Position nextPosition;
    public IteratorPosition(){list = null;}
    public IteratorPosition(List list){
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
        if(!hasNext()) throw new ExceptionNoSuchElement("no next element");
        Position currentPosition = nextPosition;
        if(currentPosition == list.last()) nextPosition = null;
        else nextPosition = list.getNext(currentPosition);
        return currentPosition;
    }
}
