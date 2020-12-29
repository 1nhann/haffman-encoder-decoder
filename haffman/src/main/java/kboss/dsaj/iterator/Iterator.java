package kboss.dsaj.iterator;

public interface Iterator {
    boolean hasNext();
    Object getNext() throws ExceptionNoSuchElement;
}