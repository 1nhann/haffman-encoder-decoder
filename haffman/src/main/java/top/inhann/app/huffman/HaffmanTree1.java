package top.inhann.app.huffman;

import top.inhann.dsa.binTree.linkedImpl.BinaryTreeImpl;
import top.inhann.dsa.comparator.Comparator;
import top.inhann.dsa.iterator.Iterator;
import top.inhann.dsa.priotyQueue.PQueue;
import top.inhann.dsa.priotyQueue.heapImpl.PQueueImpl;
import top.inhann.dsa.sequence.Sequence;
import top.inhann.dsa.sequence.SequenceImpl;
import top.inhann.dsa.vector.Vector;

public class HaffmanTree1 extends BinaryTreeImpl{
    public class CharEntryComparator implements Comparator{
        @Override
        public int compare(Object a, Object b) {
            Double aa = (Double)a;
            Double bb = (Double)b;
            double flag = aa-bb;
            if(flag < 0) return -1;
            else if(flag > 0) return 1;
            else return 0; 
        }
    }

    private Sequence charSequence;
    private Sequence haffmanNodeSequence;
    public HaffmanTree1(Sequence charSequence){
        this.charSequence = charSequence;
        buildTree();
    }
    private Sequence charSequence2HaffmanNodeSequence(Sequence charSequence){
        Sequence s = new SequenceImpl();
        while(!charSequence.isEmpty()){
            Char a = (Char)charSequence.removeFirst();
            s.insertLast((new HaffmanNode(a)));
        }
        haffmanNodeSequence = s;
        return s;
    }
    private PQueue createNodesPQ(){
        Sequence HaffmanNodeSequence = charSequence2HaffmanNodeSequence(charSequence);
        return new PQueueImpl(new CharEntryComparator(), HaffmanNodeSequence);
    }
    private void buildTree(){
        PQueue nodes = createNodesPQ();
        while(nodes.getSize() > 1){
            HaffmanNode n1 = (HaffmanNode)nodes.delMin();
            HaffmanNode node1 = n1.deepClone();
            HaffmanNode n2 = (HaffmanNode)nodes.delMin();
            HaffmanNode node2 = n2.deepClone();
            //newNode is BinaryTreeNode and an Entry
            HaffmanNode newNode = new HaffmanNode(node1, node2);
            nodes.insertNode(newNode);
        }
        this.root = (HaffmanNode)nodes.delMin();
    }
    public void display(){
        Iterator iter = inOrder();
        while(iter.hasNext()){
            HaffmanNode n = (HaffmanNode)iter.getNext();
            System.out.println(n.c);
            System.out.println(n.weight);
            System.out.println();
        }
    }
}
