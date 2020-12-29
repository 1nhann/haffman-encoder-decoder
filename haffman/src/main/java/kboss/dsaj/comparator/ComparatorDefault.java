package kboss.dsaj.comparator;

public class ComparatorDefault implements Comparator{
    public ComparatorDefault(){}
    @Override
    public int compare(Object a, Object b) throws ClassCastException{
        return ((Comparable)a).compareTo(b);
    }
}
