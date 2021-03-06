package kboss.dsaj.binTree.linkedImpl;

import kboss.dsaj.binTree.BinTree;
import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.iterator.Iterator;

public class BinaryTreeImpl implements BinTree{
    protected BinTreePosition root;
    public BinaryTreeImpl(){
        this(null);
    }
    public BinaryTreeImpl(BinTreePosition root){
        this.root = root;
    }
    public BinTreePosition getRoot() {
        return root;
    }
    @Override
    public boolean isEmpty() {
        return root == null;
    }
    @Override 
    public int getSize() {
        return isEmpty()?0:root.getSize();
    }
    @Override
    public int getHeight() {
        return isEmpty()?0:root.getHeight();
    }
    @Override
    public Iterator preOrder() {
        return root.preOrder();
    }
    @Override
    public Iterator postOrder() {
        return root.postOrder();
    }
    @Override
    public Iterator inOrder() {
        return root.inOrder();
    }
    @Override
    public Iterator levelOrder() {
        return root.levelOrder();
    }
}