package top.inhann.dsa.complBinTree.vectorImpl;

import top.inhann.dsa.binTree.BinTreePosition;
import top.inhann.dsa.binTree.linkedImpl.BinaryTreeImpl;
import top.inhann.dsa.complBinTree.ComplBinTree;
import top.inhann.dsa.entry.Entry;
import top.inhann.dsa.sequence.Sequence;
import top.inhann.dsa.vector.Vector;
import top.inhann.dsa.vector.arrayImpl.VectorImpl_2;

public class ComplBinTreeImpl extends BinaryTreeImpl implements ComplBinTree{
    //Vector<BinaryTreePosition>
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
        this.root = (BinTreePosition)tree.getAtRank(0);
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
    
    public BinTreePosition addFirstNode(BinTreePosition node){
        //tree.insertAtRank(tree.getSize(), node);
        //root = (BinTreePosition)tree.getAtRank(0);
        //return node;

        //this node is BinaryTreeNode and entry
        //return addLast(node.getElement());
        tree.insertAtRank(0, node);
        root = (BinTreePosition)tree.getAtRank(0);
        return node;
    }
    public BinTreePosition addLastNode(BinTreePosition node){
        //tree.insertAtRank(tree.getSize(), node);
        //root = (BinTreePosition)tree.getAtRank(0);
        //return node;

        //this node is BinaryTreeNode and entry
        //return addLast(node.getElement());
        tree.insertAtRank(tree.getSize(), node);
        root = (BinTreePosition)tree.getAtRank(0);
        return node;
    }
    @Override
    public BinTreePosition addNodeSequence(Sequence nodeSequence){
        while(!nodeSequence.isEmpty()==true){
            addLastNode(((ComplBinTreeNode)nodeSequence.removeFirst()).setMotherTree(tree));
        }
        root = (BinTreePosition)tree.getAtRank(0);
        return root;
    }
    @Override
    public BinTreePosition addLast(Object element) {
        BinTreePosition node = new ComplBinTreeNode(tree, element);
        tree.insertAtRank(tree.getSize(), node);
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
    @Override
    public Vector getMother() {
        return tree;
    }
    @Override
    public Object getLast() {
        return tree.getAtRank(tree.getSize()-1);
    }
}
