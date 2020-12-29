package top.inhann.dsa.priotyQueue;

import top.inhann.dsa.binTree.BinTreePosition;
import top.inhann.dsa.entry.Entry;

public interface PQueue {
    public int getSize();
    public boolean isEmpty();
    public Entry getMin() throws ExceptionPQueueEmpty;
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid;
    public Entry insert(Entry entry);
    public Entry delMin() throws ExceptionPQueueEmpty;

    public BinTreePosition insertNode(BinTreePosition p);
}
