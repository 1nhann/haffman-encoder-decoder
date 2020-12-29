package kboss.app;

import kboss.dsaj.binTree.BinTree;

public interface HaffmanTree extends BinTree{
    public String decode(String m);
    public String encode(String c);
    public String showTree();
}
