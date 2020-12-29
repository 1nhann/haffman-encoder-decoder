package top.inhann.app.huffman;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import top.inhann.dsa.binTree.linkedImpl.BinaryTreeNode;
import top.inhann.dsa.entry.Entry;
import top.inhann.dsa.priotyQueue.heapImpl.ComplBinNodeEntry;

public class HaffmanNode extends ComplBinNodeEntry implements Serializable{
    public Char c;
    public Double weight;
    public boolean asLChild;
    public HaffmanNode(Char c){
        this.c = c;
        this.weight = c.weight;
    }
    public HaffmanNode(HaffmanNode lChild, HaffmanNode rChild){
        super(lChild.getMotherTree(),null, null, null);
        this.lChild = lChild;
        this.rChild = rChild;
        lChild.asLChild = true;
        rChild.asLChild = false;
        lChild.parent = this;
        rChild.parent = this;
        weight = lChild.weight+rChild.weight;
        c = new Char(null,weight);
    }
    @Override
    public Object getKey() {
        return weight;
    }
    @Override
    public Object setKey(Object key) {
        weight = (Double)key;
        return weight;
    }
    @Override
    public Object setValue(Object value) {
        c = (Char)value;
        return c;
    }
    @Override
    public Object getValue() {
        return c;
    }
    @Override
    public Object getElement() {
        return new Char(c.c, weight);
        //return c;
    }
    @Override
    public Object setElement(Object element) {
        Char c1 = (Char)element;
        this.c = c1;
        if(c1!=null)
        this.weight = c1.weight;
        return c1;
    }

    public HaffmanNode deepClone1(){
        HaffmanNode h = null;
        try{
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(this);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            h = (HaffmanNode)ois.readObject();
        }
        catch(IOException e){
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return h;
    }

    public HaffmanNode deepClone(){
        HaffmanNode h = new HaffmanNode(c);
        h.setLChild(getLChild());
        h.setRChild(getRChild());
        h.setParent(getParent());
        h.asLChild = asLChild;
        h.setMotherTree(getMotherTree());
        return h;
    }
}
