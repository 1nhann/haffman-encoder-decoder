package top.inhann.dsa.list;

import top.inhann.dsa.position.Position;
import top.inhann.dsa.vector.ExceptionBoundaryViolation;
import top.inhann.dsa.iterator.Iterator;

public interface List {
    public int getSize();
    public boolean isEmpty();
    public Position first() throws ExceptionPositionInvalid;
    public Position last() throws ExceptionPositionInvalid;
    public Position getNext(Position position) throws ExceptionPositionInvalid,ExceptionBoundaryViolation;
    public Position getPrevious(Position position) throws ExceptionBoundaryViolation,ExceptionPositionInvalid;
    public Position insertFirst(Object element);
    public Position insertLast(Object element);
    public Position insertAfter(Position position, Object element) throws ExceptionPositionInvalid;
    public Position insertBefore(Position position, Object element) throws ExceptionPositionInvalid;
    public Object remove(Position position) throws ExceptionPositionInvalid;
    public Object removeFirst();
    public Object removeLast();
    public Object replace(Position position, Object element) throws ExceptionPositionInvalid;
    public Iterator positions();
    public Iterator elements();
}