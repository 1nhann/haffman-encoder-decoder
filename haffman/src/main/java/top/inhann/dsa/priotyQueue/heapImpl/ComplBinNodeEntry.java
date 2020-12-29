package top.inhann.dsa.priotyQueue.heapImpl;

import java.io.Serializable;

import top.inhann.dsa.binTree.BinTreePosition;
import top.inhann.dsa.binTree.linkedImpl.BinaryTreeNode;
import top.inhann.dsa.complBinTree.vectorImpl.ComplBinTreeImpl;
import top.inhann.dsa.complBinTree.vectorImpl.ComplBinTreeNode;
import top.inhann.dsa.entry.Entry;
import top.inhann.dsa.vector.Vector;

public class ComplBinNodeEntry extends ComplBinTreeNode implements Entry,Serializable{
    private Object key;
    private Object value;
    public Object getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
    public Object setKey(Object key) {
        this.key = key;
        return key;
    }
    public Object setValue(Object value) {
        this.value = value;
        return value;
    }
    public ComplBinNodeEntry(Vector tree,Object element,Object key, Object value){
        //super(null, parent, false, lChild, rChild);
        super(tree, element,false);
        this.key = key;
        this.value = value;
    }
    public ComplBinNodeEntry(){}
}
