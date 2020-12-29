package kboss.dsaj.tree;

import kboss.dsaj.iterator.Iterator;

public class TreeImpl implements Tree{
    private TreeLinkedNode root;
    @Override
    public TreeNode getRoot() {
        return root;
    }
    @Override
    public int getSize() {
        return root.getSize();
    }
    @Override
    public boolean isEmpty() {
        return root.getSize() == 0;
    }
    @Override
    public int getHeight() {
        return root.getHeight();
    }
    @Override
    public Iterator preOrder() {
        return new IteratorTree(IteratorTree.PRE_ORDER, root);
    }
    @Override
    public Iterator postOrder() {
        return new IteratorTree(IteratorTree.POST_ORDER, root);
    }
    @Override
    public Iterator levelOrder() {
        return new IteratorTree(IteratorTree.LEVEL_ORDER, root);
    }
}
