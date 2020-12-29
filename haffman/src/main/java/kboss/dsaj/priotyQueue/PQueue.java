package kboss.dsaj.priotyQueue;

import kboss.dsaj.entry.Entry;

public interface PQueue {
    public int getSize();
    public boolean isEmpty();
    public Entry getMin() throws ExceptionPQueueEmpty;
    public Entry insert(Object key, Object value) throws ExceptionKeyInvalid;
    public Entry delMin() throws ExceptionPQueueEmpty;
}