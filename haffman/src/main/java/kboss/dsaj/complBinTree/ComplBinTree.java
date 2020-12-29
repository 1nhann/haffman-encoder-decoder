package kboss.dsaj.complBinTree;

import kboss.dsaj.binTree.BinTree;
import kboss.dsaj.binTree.BinTreePosition;

public interface ComplBinTree extends BinTree{
    public BinTreePosition addLast(Object element);
    public Object delLast();
    public BinTreePosition posOfNode(int i);
}
