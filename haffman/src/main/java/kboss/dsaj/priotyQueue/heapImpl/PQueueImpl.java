package kboss.dsaj.priotyQueue.heapImpl;
//interface
import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.comparator.Comparator;
//inmplement
import kboss.dsaj.comparator.ComparatorDefault;
import kboss.dsaj.complBinTree.ComplBinTree;
import kboss.dsaj.complBinTree.vectorImpl.ComplBinTreeImpl;
import kboss.dsaj.complBinTree.vectorImpl.ComplBinTreeNode;
import kboss.dsaj.entry.Entry;
import kboss.dsaj.entry.EntryDefault;
import kboss.dsaj.priotyQueue.ExceptionKeyInvalid;
import kboss.dsaj.priotyQueue.ExceptionPQueueEmpty;
import kboss.dsaj.priotyQueue.PQueue;
import kboss.dsaj.sequence.Sequence;

public class PQueueImpl implements PQueue{
    private ComplBinTree heap;
    private Comparator comparator;
    public PQueueImpl(){
        this(new ComparatorDefault(),null);
    }
    public PQueueImpl(Comparator comparator){
        this(comparator,null);
    }
    public PQueueImpl(Sequence entrySequence){
        this(new ComparatorDefault(),entrySequence);
    }
    public PQueueImpl(Comparator comparator, Sequence entrySequence){
        this.comparator = comparator;
        heap = new ComplBinTreeImpl(entrySequence);
        //build the heap , O(n) spent
        //Robe rt Floyd algorithm
        if(!heap.isEmpty()){
            for(int i  = heap.getSize()/2-1; i >= 0; i--){
                percolateDown(heap.posOfNode(i));
            }
        }
    } 
    @Override
    public int getSize() {
        return heap.getSize();
    }
    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }
    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        if(isEmpty()) throw new ExceptionPQueueEmpty("pqueue empty");
        return (Entry)heap.getRoot().getElement();
    }
    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        checkKey(key);
        Entry entry = new EntryDefault(key, value);
        percolateUp(heap.addLast(entry));
        return entry;
    }
    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        if(isEmpty()) throw new ExceptionPQueueEmpty("pqueue empty");
        Entry min = (Entry)heap.getRoot().getElement();
        if(getSize() == 1){
            heap.delLast();
        }
        else{
            heap.getRoot().setElement(((ComplBinTreeNode)heap.delLast()).getElement());
            percolateDown(heap.getRoot());
        }
        return min;
    }

    protected void checkKey(Object key) throws ExceptionKeyInvalid{
        try{
            comparator.compare(key, key);
        }catch(Exception e){
            throw new ExceptionKeyInvalid("keys cannot compare");
        }
    }
    protected Object key(BinTreePosition node){
        return ((Entry)node.getElement()).getKey();
    }

    protected void swapElement(BinTreePosition u, BinTreePosition v){
        Object temp = u.getElement();
        u.setElement(v.getElement());
        v.setElement(temp);
    }

    protected void percolateUp(BinTreePosition v){
        BinTreePosition root = heap.getRoot();
        while(v != root){
            BinTreePosition parent = v.getParent();
            if(comparator.compare(key(parent),key(v)) <= 0) break;
            else swapElement(parent, v);
            v = parent;
        }
    }
    protected void percolateDown(BinTreePosition v){
        while(v.hasLChild()){
            BinTreePosition smallerChild = v.getLChild();
            if(v.hasRChild() && comparator.compare(key(v.getLChild()), key(v.getRChild())) > 0){
                smallerChild = v.getRChild();
            }
            if(comparator.compare(key(smallerChild), key(v)) >= 0) break;
            else swapElement(smallerChild, v);
            v = smallerChild;
        }
    }

    public Entry set(BinTreePosition node, Object key){
        ((Entry)node.getElement()).setKey(key);
        Entry entry = (Entry)node.getElement();
        percolateUp(node);
        percolateDown(heap.getRoot());
        return entry;
    }
}
