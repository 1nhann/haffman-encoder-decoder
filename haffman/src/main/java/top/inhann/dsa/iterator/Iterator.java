package top.inhann.dsa.iterator;

public interface Iterator {
    boolean hasNext();
    Object getNext() throws ExceptionNoSuchElement;
}