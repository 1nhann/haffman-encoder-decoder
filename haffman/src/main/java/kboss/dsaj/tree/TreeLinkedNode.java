package kboss.dsaj.tree;

public class TreeLinkedNode implements TreeNode{
    private Object element;
    private TreeLinkedNode parent;
    private TreeLinkedNode firstChild;
    private TreeLinkedNode nextSibling;

    public TreeLinkedNode(Object element,TreeLinkedNode parent,TreeLinkedNode child,TreeLinkedNode sibling){
        this.element = element;
        this.parent = parent;
        this.nextSibling = sibling;
        this.firstChild = child;
    }
    @Override
    public Object getElement() {
        return element;
    }
    @Override
    public Object setElement(Object element) {
        this.element = element;
        return element;
    }
    public TreeLinkedNode getParent() {
        return parent;
    }
    public TreeLinkedNode getFirstChild() {
        return firstChild;
    }
    public TreeLinkedNode getNextSibling() {
        return nextSibling;
    }
    @Override
    public int getSize() {
        int size = 1;
        TreeLinkedNode subTree = firstChild;
        while(subTree != null){
            size += subTree.getSize();
            subTree = subTree.getNextSibling();
        }
        return size;
    }
    @Override
    public int getHeight() {
        int height = -1;
        TreeLinkedNode subTree = firstChild;
        while (subTree != null){
            height = Math.max(height, subTree.getHeight());
            subTree = subTree.getNextSibling();
        }
        return height + 1;
    }
    @Override
    public int getDepth() {
        int depth = 0;
        TreeLinkedNode p = parent;
        while(p != null){
            depth ++;
            p = p.getParent();
        }
        return depth;
    }
}