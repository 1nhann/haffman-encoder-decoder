package kboss.app;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kboss.dsaj.binTree.BinTree;
import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.binTree.linkedImpl.BinaryTreeImpl;
import kboss.dsaj.binTree.linkedImpl.BinaryTreeNode;
import kboss.dsaj.entry.Entry;
import kboss.dsaj.entry.EntryDefault;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.priotyQueue.PQueue;
import kboss.dsaj.priotyQueue.heapImpl.PQueueImpl;
import kboss.dsaj.sequence.Sequence;
import kboss.dsaj.sequence.SequenceImpl;

public class HuffmanTreeBuilder {
    private Sequence leaves;
    public HuffmanTreeBuilder(){
        leaves = new SequenceImpl();
    }
    private PQueue buildTreePQueue(Sequence chars){
        Sequence trees = new SequenceImpl();
        while(!chars.isEmpty()){
            Char c = (Char)chars.removeFirst();
            //entry: (key,value) = (c.weight,binaryTreeNode)
            BinTreePosition leaf = new BinaryTreeNode(c, null, true, null, null);
            leaves.insertLast(leaf);
            trees.insertLast(new EntryDefault(c.getWeight(), 
                                            leaf));
        }
        return new PQueueImpl(new WeightComparator(),trees);
    }
    public BinTree buildHuffmanTree(Sequence chars){

        PQueue treeEntryPQueue = buildTreePQueue(chars);

        while(treeEntryPQueue.getSize() > 1){
            Entry treeEntry1 = treeEntryPQueue.delMin();
            Entry treeEntry2 = treeEntryPQueue.delMin();
            Double key = (Double)treeEntry1.getKey() + (Double)treeEntry2.getKey();
            Char c = new CharImpl(null, key);
            BinaryTreeNode newNode = new BinaryTreeNode(null, null, true, 
                                            (BinaryTreeNode)treeEntry1.getValue(),
                                            (BinaryTreeNode)treeEntry2.getValue());
            newNode.setElement(c);
            treeEntryPQueue.insert(key, newNode);
        }
        return new BinaryTreeImpl((BinaryTreeNode)treeEntryPQueue.delMin().getValue());
    }
    public Sequence getLeaves() {
        return leaves;
    }
    public Sequence str2Sequence(String charWeightPairs){
        Sequence charSequence = new SequenceImpl();
        String regEx = "(.,[0-9.]+)";
        Pattern pattern = Pattern.compile(regEx);
        Matcher m = pattern.matcher(charWeightPairs);
        

        Sequence pairs1 = new SequenceImpl();
        while(m.find() == true){
            pairs1.insertLast(m.group());
        }
        Iterator iter = pairs1.elements();
        while(iter.hasNext()){
            String pair = (String)iter.getNext();
            String[] s = pair.split(",");
            Character c1 = s[0].toCharArray()[0];
            Double weight = Double.parseDouble(s[1]);
            Char c = new CharImpl(c1, weight);
            charSequence.insertLast(c);
        }

        // List<String> pairs = new ArrayList<>();
        // // while(m.find() == true){
        // //     pairs.add(m.group());
        // // }
        // // for(String pair:pairs){
        // //     String[] s = pair.split(",");
        // //     //Character c1 = s[0].substring(1).toCharArray()[0];
        // //     Character c1 = s[0].toCharArray()[0];
        // //     //Double weight = Double.parseDouble(s[1].substring(0, s[1].length()));
        // //     Double weight = Double.parseDouble(s[1]);
        // //     Char c = new CharImpl(c1, weight);
        // //     charSequence.insertLast(c);
        // // }
        return charSequence;
    }
}
