package kboss.dsaj.tree;

import kboss.dsaj.iterator.ExceptionNoSuchElement;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.list.List;
import kboss.dsaj.list.dlinkedImpl.ListImpl;
import kboss.dsaj.position.Position;
import kboss.dsaj.queue.Queue;
import kboss.dsaj.queue.linkedListImpl.QueueImpl;

public class IteratorTree implements Iterator {

    public static final int PRE_ORDER = 5;
    public static final int POST_ORDER = 20;
    public static final int LEVEL_ORDER = 50;
    private List list;
    private Position nextPosition;
    int style;

    public IteratorTree(int style, TreeLinkedNode beginNode){
        list = new ListImpl();
        this.style = style;
        switch(style){
            case PRE_ORDER :{
                preOrderIterator(beginNode);
            }
            case POST_ORDER :{
                postOrderIterator(beginNode);
            }
            case LEVEL_ORDER :{
                levelOrderIterator(beginNode);
            }
        }
        nextPosition = list.first();
    }

    protected void preOrderIterator(TreeLinkedNode beginNode){
        if(beginNode == null) return;
        list.insertLast(beginNode);
        for(TreeLinkedNode sub = beginNode.getFirstChild();sub != null;sub = sub.getNextSibling()){
            this.preOrderIterator(sub);           
        }
    }
    /**protected void inOrderIterator(TreeLinkedNode beginNode){
        if(beginNode == null) return;
        for(TreeLinkedNode sub = beginNode.getFirstChild();sub != null;sub = sub.getNextSibling()){
            this.inOrderIterator(sub);
            list.insertLast(beginNode);           
        }
    }*/
    protected void postOrderIterator(TreeLinkedNode beginNode){
        if(beginNode == null) return;
        for(TreeLinkedNode sub = beginNode.getFirstChild();sub != null;sub = sub.getNextSibling()){
            this.postOrderIterator(sub);           
        }
        list.insertLast(beginNode);
    }
    protected void levelOrderIterator(TreeLinkedNode beginNode){
        if(beginNode == null) return;
        Queue queue = new QueueImpl();
        queue.enqueue(beginNode);
        while(!queue.isEmpty()){
            TreeLinkedNode node = (TreeLinkedNode)queue.dequeue();
            list.insertLast(node);
            for(TreeLinkedNode sub = node.getFirstChild(); sub != null; sub = sub.getNextSibling()){
                queue.enqueue(sub);
            }
        }
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