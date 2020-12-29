package kboss.dsaj.binTree.linkedImpl;

import kboss.dsaj.binTree.BinTreePosition;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.list.List;
import kboss.dsaj.list.dlinkedImpl.ListImpl;
import kboss.dsaj.queue.Queue;
import kboss.dsaj.queue.linkedListImpl.QueueImpl;
 
public class BinaryTreeNode implements BinTreePosition{
    protected Object element;
    protected BinTreePosition parent;
    protected BinTreePosition lChild;
    protected BinTreePosition rChild;
    protected int size;
    protected int height;
    protected int depth;

    public BinaryTreeNode(){
        this(null, null, true, null, null);
    }
    public BinaryTreeNode(Object element,
                        BinTreePosition parent,
                        boolean asLChild,
                        BinTreePosition lChild,
                        BinTreePosition rChild){
        this.element = element;
        size = 1;
        height = 0;
        depth = 0;
        if(parent != null){
            if(asLChild) parent.attachL(this);
            else parent.attachR(this);
        }
        if(lChild != null) attachL(lChild);
        if(rChild != null) attachR(rChild);
    }
    public Object getElement() {
        return element;
    }
    @Override
    public Object setElement(Object element) {
        this.element = element;
        return element;
    }
    @Override
    public boolean hasParent() {
        return parent != null;
    }
    public BinTreePosition getParent() {
        return parent;
    }
    public void setParent(BinTreePosition parent) {
        this.parent = parent;
    }
    @Override
    public boolean isLeaf() {
        return !hasLChild()&&!hasRChild();
    }
    @Override
    public boolean isLChild() { 
        return hasParent() && this == this.getParent().getLChild();
    }
    @Override
    public boolean isRChild() {
        return hasParent() && this == this.getParent().getRChild();
    }
    public BinTreePosition getLChild() {
        return lChild;
    }
    public BinTreePosition getRChild() {
        return rChild;
    }
    public int getSize() {
        return size;
    }
    @Override
    public void updateSize() {
        size = 1;
        if(hasLChild()) size += getLChild().getSize();
        if(hasRChild()) size += getRChild().getSize();
        if(hasParent()) getParent().updateSize();
    }
    @Override
    public int getHeight() {
        return height;
    }
    @Override
    public void updateHeight() {
        height = 0;
        if(hasLChild()) height = Math.max(height, 1 + getLChild().getHeight());
        if(hasRChild()) height = Math.max(height, 1 + getRChild().getHeight());
        if(hasParent()) getParent().updateHeight();
    }
    public int getDepth() {
        return depth;
    }
    @Override
    public void updateDepth() {
        depth = hasParent()
                ? 1 + getParent().getDepth()
                : 0;
        if(hasLChild()) getLChild().updateDepth();
        if(hasRChild()) getRChild().updateDepth();
    }
    //?????
    @Override
    public BinTreePosition getPrev() {
        if(hasLChild()) return findMaxDescendant(getLChild());
        else{
            BinTreePosition v = this;
            while(v.isLChild()) v = v.getParent();
            return v.getParent();
        }
    }
    //?????
    @Override
    public BinTreePosition getSucc() {
        if(hasRChild()) return findMinDescendant(getRChild());
        else{
            BinTreePosition v = this;
            while(v.isRChild()) v = v.getParent();
            return v.getParent();
        }
    }
    @Override
    public BinTreePosition secede() {
        if(parent != null){
            if(isLChild()) parent.setLChild(null);
            else parent.setRChild(null);
            parent.updateSize();
            parent.updateHeight();
            setParent(null);
            updateDepth();
        }
        return this;
    }
    @Override
    public BinTreePosition attachL(BinTreePosition c) {
        if(hasLChild()) getLChild().secede();
        if(c != null){
            c.secede();
            setLChild(c);
            c.setParent(this);
            updateSize();
            updateHeight();
            updateDepth();
        }
        return this;
    }
    @Override
    public BinTreePosition attachR(BinTreePosition c) {
        if(hasRChild()) getRChild().secede();
        if(c != null){
            c.secede();
            setRChild(c);
            c.setParent(this);
            updateSize();
            updateHeight();
            updateDepth();
        }
        return this;
    }
    @Override
    public Iterator preOrder() {
        List list = new ListImpl();
        preOrder(list,this);
        return list.elements();
    }
    protected void preOrder(List list, BinTreePosition node){
        if(node == null) return;
        list.insertLast(node);
        preOrder(list, node.getLChild());
        preOrder(list, node.getRChild());
    }
    @Override
    public Iterator inOrder() {
        List list = new ListImpl();
        inOrder(list,this);
        return list.elements();
    }
    private void inOrder(List list, BinTreePosition node){
        if(node == null) return;
        inOrder(list, node.getLChild());
        list.insertLast(list);
        inOrder(list,node.getRChild());
    }
    @Override
    public Iterator postOrder() {
        List list = new ListImpl();
        postOrder(list,this);
        return list.elements();
    }
    private void postOrder(List list, BinTreePosition node){
        if(node == null) return;
        preOrder(list, node.getLChild());
        preOrder(list, node.getRChild());
        list.insertLast(node);
    }
    @Override
    public Iterator levelOrder() {
        List list = new ListImpl();
        levelOrder(list,this);
        return list.elements(); 
    }
    private void levelOrder(List list, BinTreePosition node){
        Queue queue = new QueueImpl();
        queue.enqueue(node);
        while(!queue.isEmpty()){
            BinTreePosition u = (BinTreePosition)queue.dequeue();
            list.insertLast(u);
            if(u.hasLChild()) queue.enqueue(u.getLChild());
            if(u.hasRChild()) queue.enqueue(u.getRChild());
        }
    }

    protected static BinTreePosition findMinDescendant(BinTreePosition v){
        if(v != null){
            while(v.hasLChild()){
                v = v.getLChild();
            }
        }
        return v;
    }
    protected static BinTreePosition findMaxDescendant(BinTreePosition v){
        if(null != v){
            while(v.hasRChild()){
                v = v.getRChild();
            }
        }
        return v;
    }

    @Override
    public boolean hasLChild() {
        return lChild != null;
    }

    @Override
    public void setLChild(BinTreePosition child) {
        lChild = child;
    }

    @Override
    public boolean hasRChild() {
        return rChild != null;
    }

    @Override
    public void setRChild(BinTreePosition child) {
        rChild = child;
    }
}