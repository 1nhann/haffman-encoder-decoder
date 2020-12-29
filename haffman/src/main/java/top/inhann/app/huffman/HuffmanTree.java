package top.inhann.app.huffman;

import top.inhann.dsa.binTree.BinTree;
import top.inhann.dsa.binTree.linkedImpl.BinaryTreeImpl;
import top.inhann.dsa.binTree.linkedImpl.BinaryTreeNode;
import top.inhann.dsa.entry.Entry;
import top.inhann.dsa.entry.EntryDefault;
import top.inhann.dsa.iterator.Iterator;
import top.inhann.dsa.priotyQueue.PQueue;
import top.inhann.dsa.priotyQueue.heapImpl.PQueueImpl;
import top.inhann.dsa.sequence.Sequence;
import top.inhann.dsa.sequence.SequenceImpl;

public class HuffmanTree {
    private BinTree haffman;
    public HuffmanTree(Sequence chars){
        this.haffman = buildHuffmanTree(chars);
    }
    private PQueue buildTreePQueue(Sequence chars){
        Sequence trees = new SequenceImpl();
        while(!chars.isEmpty()){
            Char c = (Char)chars.removeFirst();
            //entry: (key,value) = (c.weight,binaryTreeNode)
            trees.insertLast(new EntryDefault(c.weight, 
                                            new BinaryTreeNode(c, null, true, null, null)));
        }
        return new PQueueImpl(new WeightComparator(),trees);
    }
    private BinTree buildHuffmanTree(Sequence chars){
        PQueue treeEntryPQueue = buildTreePQueue(chars);
        while(treeEntryPQueue.getSize() > 1){
            Entry treeEntry1 = treeEntryPQueue.delMin();
            Entry treeEntry2 = treeEntryPQueue.delMin();
            Double key = (Double)treeEntry1.getKey() + (Double)treeEntry2.getKey();
            BinaryTreeNode newNode = new BinaryTreeNode(null, null, true, 
                                            (BinaryTreeNode)treeEntry1.getValue(),
                                            (BinaryTreeNode)treeEntry2.getValue());
            treeEntryPQueue.insert(key, newNode);
        }
        return new BinaryTreeImpl((BinaryTreeNode)treeEntryPQueue.delMin().getValue());
    }
    public void display(){
        Iterator iter = haffman.levelOrder();
        while(iter.hasNext()){
            System.out.println(iter.getNext());
        }
    }
}
