package kboss.dsaj.vector;

public interface Vector {
    public int getSize();
    public boolean isEmpty();
    public Object getAtRank(int rank) throws ExceptionBoundaryViolation;
    public Object replaceAtRank(int rank, Object element) throws ExceptionBoundaryViolation;
    public Object insertAtRank(int rank, Object element) throws ExceptionBoundaryViolation;
    public Object removeAtRank(int rank) throws ExceptionBoundaryViolation;
}