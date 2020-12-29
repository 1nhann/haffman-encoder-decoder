package kboss.app;


import kboss.dsaj.binTree.BinTree;
import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.binTree.linkedImpl.BinaryTreeImpl;
import kboss.dsaj.binTree.linkedImpl.BinaryTreeNode;
import kboss.dsaj.complBinTree.ComplBinTree;
import kboss.dsaj.complBinTree.vectorImpl.ComplBinTreeImpl;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.map.Map;
import kboss.dsaj.map.hashTableImpl.MapImpl;
import kboss.dsaj.queue.Queue;
import kboss.dsaj.queue.linkedListImpl.QueueImpl;
import kboss.dsaj.sequence.Sequence;
import kboss.dsaj.sequence.SequenceImpl;

public class HaffmanTreeImpl extends BinaryTreeImpl implements HaffmanTree{
    private BinTree haffman;
    private Sequence charSequence;
    private Sequence leaves;
    public HaffmanTreeImpl(String charWeightPairs) throws ExceptionBuildTree{
        HuffmanTreeBuilder builder = new HuffmanTreeBuilder();
        charSequence = new SequenceImpl();
        try{
            charSequence = builder.str2Sequence(charWeightPairs);
        }
        catch(Exception e){
            throw new ExceptionBuildTree("please check your char-weight pairs again !!!");
        }
        haffman = builder.buildHuffmanTree(charSequence);
        leaves = builder.getLeaves();
    }
    private BinTreePosition find(Character c){
        Iterator iter = leaves.elements();
        while(iter.hasNext()){
            BinTreePosition p = (BinTreePosition)iter.getNext();
            Char pc = (CharImpl)p.getElement();
            if((Character)pc.getC() == c) return p;
        }
        return null;
    }
    //m to 101010
    @Override
    public String encode(String m) throws ExceptionDecode{
        //HashMap<Character,String> cache = new HashMap<>();
        Map cache = new MapImpl();
        StringBuilder r = new StringBuilder();
        char chars[] = m.toCharArray();
        for(char c:chars){
            if(cache.containsKey(c)){
            }
            else{
                BinTreePosition p = find(c);
                if(p==null){
                    throw new ExceptionDecode("Character not found!!");
                }
                else{
                    StringBuilder s = new StringBuilder();
                    BinTreePosition root = haffman.getRoot();
                    while(p != root){
                        if(p.isLChild()) s.append("0");
                        else if(p.isRChild()) s.append("1");
                        p = p.getParent();
                    }
                    s = s.reverse();
                    cache.put(c, s.toString());
                }
            }
            r.append((String)cache.get(c));
        }
        return r.toString();
    }
    //101010 to m
    @Override
    public String decode(String c) throws ExceptionEncode{
        char digits[] = c.toCharArray();
        StringBuilder r = new StringBuilder();
        BinTreePosition p = haffman.getRoot();
        for(char digit: digits){
            if(digit == '0'){
                if(!p.hasLChild()) throw new ExceptionEncode("Incorect codes , please check again !!!");
                p = p.getLChild();
            }
            else if(digit == '1'){
                if(!p.hasRChild()) throw new ExceptionEncode("Incorect codes , please check again !!!");
                p = p.getRChild();
            }
            else{
                throw new ExceptionEncode("Invalid digit found!!! please input 0 and 1 to encode");
            }
            if(p.isLeaf()){
                r.append(((Char)p.getElement()).getC());
                p = haffman.getRoot();
            }
        }
        if(p != haffman.getRoot())throw new ExceptionEncode("invalid digits!! unable to encode!!");
        return r.toString();
    }
    private ComplBinTree binTree2ComplBinTree(BinTree tree){
        //Iterator iter = tree.levelOrder();
        BinTreePosition root = tree.getRoot();
        ComplBinTree compl = new ComplBinTreeImpl();
        int level = root.getHeight()+1;

        Queue queue = new QueueImpl();
        queue.enqueue(root);
        while(!queue.isEmpty()){
            BinTreePosition u = (BinTreePosition)queue.dequeue();
            if(u.getDepth() == level) break;
            compl.addLast(u.getElement());
            if(u.hasLChild()) queue.enqueue(u.getLChild());
            else{queue.enqueue(new BinaryTreeNode(null, u, true, null, null));}
            if(u.hasRChild()) queue.enqueue(u.getRChild());
            else{queue.enqueue(new BinaryTreeNode(null, u, false, null, null));}
            
        }
        return compl;
    }

    @Override
    public String showTree() {
        ComplBinTree compl = binTree2ComplBinTree(haffman);
        Iterator iter = compl.levelOrder();
        StringBuilder s = new StringBuilder();
        s.append("[");
        while(iter.hasNext()){
            //iter.getNext() is Char
            BinTreePosition p = (BinaryTreeNode)iter.getNext();
            Char c = (CharImpl)(p.getElement());
            if(c == null) s.append("null,");
            else{
                Double weight = (Double)c.getWeight();
                Character cin = (Character)c.getC();
                String item = "'";
                if(cin != null) item = item+cin+"',";
                else item = item + String.format("%.2f", weight) + "',";
                s.append(item);
            }
        }
        s.append("]");
        return s.toString();
    }
}
