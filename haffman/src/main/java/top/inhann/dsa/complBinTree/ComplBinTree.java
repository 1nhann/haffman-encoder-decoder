package top.inhann.dsa.complBinTree;

import top.inhann.dsa.binTree.BinTree;
import top.inhann.dsa.binTree.BinTreePosition;
import top.inhann.dsa.sequence.Sequence;
import top.inhann.dsa.vector.Vector;

public interface ComplBinTree extends BinTree{
    public BinTreePosition addLast(Object element);
    public BinTreePosition addNodeSequence(Sequence nodeSequence);
    public Object delLast();
    public Object getLast();
    public BinTreePosition posOfNode(int i);
    public Vector getMother();

    public BinTreePosition addFirstNode(BinTreePosition p);
    public BinTreePosition addLastNode(BinTreePosition p);
}
