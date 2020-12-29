package top.inhann.dsa.binTree;

import top.inhann.dsa.iterator.Iterator;

public interface BinTree {
    public BinTreePosition getRoot();
    public boolean isEmpty();
    public int getSize();
    public int getHeight();
    public Iterator preOrder();
    public Iterator inOrder();
    public Iterator postOrder();
    public Iterator levelOrder();
}