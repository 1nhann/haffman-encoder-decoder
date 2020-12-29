package kboss.dsaj.tree;
import kboss.dsaj.iterator.Iterator;

public interface Tree {
    public TreeNode getRoot();
    public boolean isEmpty();
    public int getSize();
    public int getHeight();
    public Iterator preOrder();
    public Iterator postOrder();
    public Iterator levelOrder();
}
