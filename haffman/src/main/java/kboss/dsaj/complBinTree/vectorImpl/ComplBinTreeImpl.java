package kboss.dsaj.complBinTree.vectorImpl;

import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.binTree.linkedImpl.BinaryTreeImpl;
import kboss.dsaj.complBinTree.ComplBinTree;
import kboss.dsaj.sequence.Sequence;
import kboss.dsaj.vector.Vector;
import kboss.dsaj.vector.arrayImpl.VectorImpl_2;

public class ComplBinTreeImpl extends BinaryTreeImpl implements ComplBinTree{
    private Vector tree;
    
    public ComplBinTreeImpl(){
        tree = new VectorImpl_2();
        this.root = null;
    }
    public ComplBinTreeImpl(Sequence sequence){
        this();
        if(sequence != null){
            while(!sequence.isEmpty()){
                addLast(sequence.removeFirst());
            }
        }
    }
    @Override
    public BinTreePosition getRoot() {
        return tree.isEmpty()?null:posOfNode(0);
    }
    @Override
    public boolean isEmpty() {
        return tree.isEmpty();
    }
    @Override
    public int getSize() {
        return tree.getSize();
    }
    @Override
    public int getHeight() {
        return isEmpty()?-1:getRoot().getHeight();
    }
    @Override
    public BinTreePosition addLast(Object element) {
        BinTreePosition node = new ComplBinTreeNode(tree, element);
        //tree.insertAtRank(tree.getSize(), node);
        root = (BinTreePosition) tree.getAtRank(0);
        return node;
    }
    @Override
    public Object delLast() {
        if(isEmpty()) return null;
        if(getSize() == 1) root = null;
        return tree.removeAtRank(tree.getSize() - 1);
    }
    @Override
    public BinTreePosition posOfNode(int i) {
        return (BinTreePosition)tree.getAtRank(i);
    }
}
