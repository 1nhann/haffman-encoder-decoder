package kboss.dsaj.binTree;

import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.position.Position;

public interface BinTreePosition extends Position{
    public boolean hasParent();
    public BinTreePosition getParent();
    public void setParent(BinTreePosition parent);

    public boolean isLeaf();
    
    public boolean isLChild();
    public boolean hasLChild();
    public BinTreePosition getLChild();
    public void setLChild(BinTreePosition child);

    public boolean isRChild();
    public boolean hasRChild();
    public BinTreePosition getRChild();
    public void setRChild(BinTreePosition child);

    public int getSize();
    public void updateSize();

    public int getHeight();
    public void updateHeight();

    public int getDepth();
    public void updateDepth();

    public BinTreePosition getPrev();
    public BinTreePosition getSucc();

    public BinTreePosition secede();
    public BinTreePosition attachL(BinTreePosition c);
    public BinTreePosition attachR(BinTreePosition c);

    public Iterator preOrder();
    public Iterator postOrder();
    public Iterator inOrder();
    public Iterator levelOrder();
}