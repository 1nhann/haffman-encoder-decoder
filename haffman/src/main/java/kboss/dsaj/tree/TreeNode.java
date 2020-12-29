package kboss.dsaj.tree;
import kboss.dsaj.position.Position;

public interface TreeNode extends Position{
    public TreeLinkedNode getParent();
    public TreeLinkedNode getFirstChild();
    public TreeLinkedNode getNextSibling();
    public int getSize();
    public int getHeight();
    public int getDepth();
}