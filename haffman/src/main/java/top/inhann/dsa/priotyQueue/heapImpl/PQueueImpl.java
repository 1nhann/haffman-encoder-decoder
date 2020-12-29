package top.inhann.dsa.priotyQueue.heapImpl;

import top.inhann.app.huffman.HaffmanNode;
import top.inhann.dsa.binTree.BinTreePosition;
import top.inhann.dsa.comparator.Comparator;
import top.inhann.dsa.comparator.ComparatorDefault;
import top.inhann.dsa.complBinTree.ComplBinTree;
import top.inhann.dsa.complBinTree.vectorImpl.ComplBinTreeImpl;
import top.inhann.dsa.complBinTree.vectorImpl.ComplBinTreeNode;
import top.inhann.dsa.entry.Entry;
import top.inhann.dsa.priotyQueue.ExceptionKeyInvalid;
import top.inhann.dsa.priotyQueue.ExceptionPQueueEmpty;
import top.inhann.dsa.priotyQueue.PQueue;
import top.inhann.dsa.sequence.Sequence;

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
        //entrySequence must contains BinaryTreePosition
        heap = new ComplBinTreeImpl();
        heap.addNodeSequence(entrySequence);
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
    //trouble
    @Override
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid {
        checkKey(key);
        Entry entry = new ComplBinNodeEntry(heap.getMother(), null, key, value);
        percolateUp(heap.addLast(entry));
        return entry;
    }

    public BinTreePosition insertNode(BinTreePosition p){
        percolateUp(heap.addLastNode(p));
        return p;
    }
    @Override
    public Entry insert(Entry entry) {
        //entry is BinaryTreeNode and Entry
        percolateUp(heap.addLast(entry));
        return entry;
    }
    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        if(isEmpty()) throw new ExceptionPQueueEmpty("pqueue empty");
        //Entry min = ((HaffmanNode)heap.getRoot()).deepClone();
        Entry min = (Entry)heap.getLast();
        if(getSize() == 1){
            heap.delLast();
        }
        else{
            //heap.getRoot().setElement(((ComplBinTreeNode)heap.delLast()).getElement());
            swapElement(heap.getRoot(), (ComplBinTreeNode)heap.delLast());
            percolateDown(heap.getRoot());
        }
        //Entry min = (Entry)heap.getLast();
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
        return ((ComplBinNodeEntry)node).getKey();
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
            if(parent==null || comparator.compare(key(parent),key(v)) <= 0) break;
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
