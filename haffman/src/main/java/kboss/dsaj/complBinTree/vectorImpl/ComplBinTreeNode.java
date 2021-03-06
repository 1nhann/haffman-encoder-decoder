package kboss.dsaj.complBinTree.vectorImpl;

import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.binTree.linkedImpl.BinaryTreeNode;
import kboss.dsaj.vector.Vector;

public class ComplBinTreeNode extends BinaryTreeNode{
    private Vector motherTree;
    private int rank;
    private Object element;

    public ComplBinTreeNode(Vector tree, Object element){
        this.element = element;
        motherTree = tree;
        rank = motherTree.getSize();
        tree.insertAtRank(rank, this);
    }
    public Object getElement() {
        return element;
    }
    public Object setElement(Object element) {
        this.element = element;
        return element;
    }
    @Override
    public boolean hasParent() {
        return rank != 0;
    }
    @Override
    public BinTreePosition getParent() {
        return hasParent()
                ?(BinTreePosition)motherTree.getAtRank((rank-1)/2)
                :null;
    }
    @Override
    public boolean hasLChild() {
        return rank*2+1 < motherTree.getSize();
    }
    @Override
    public boolean hasRChild() {
        return rank*2+2 < motherTree.getSize();
    }
    @Override
    public BinTreePosition getLChild() {
        return hasLChild()
                ?(BinTreePosition)motherTree.getAtRank(rank*2+1)
                :null;
    }
    @Override
    public BinTreePosition getRChild() {
        return hasRChild()
                ?(BinTreePosition)motherTree.getAtRank(rank*2+2)
                :null;
    }
    @Override
    public int getSize() {
        int size = 1;
        if(hasLChild()) size += getLChild().getSize();
        if(hasRChild()) size += getRChild().getSize();
        return size;
    }
    @Override
    public int getHeight() {
        int hL = hasLChild()?getLChild().getHeight():-1;
        int hR = hasRChild()?getRChild().getHeight():-1;
        return 1 + Math.max(hL,hR);
    }
    @Override
    public int getDepth() {
        return hasParent()?1+getParent().getDepth():0;
    }
}
