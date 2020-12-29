package top.inhann.dsa.sequence;

import top.inhann.dsa.position.Position;
import top.inhann.dsa.deque.DoubleLinkedNode;
import top.inhann.dsa.list.ExceptionPositionInvalid;
import top.inhann.dsa.list.dlinkedImpl.ListImpl;
import top.inhann.dsa.vector.ExceptionBoundaryViolation;

public class SequenceImpl extends ListImpl implements Sequence{
    protected void checkRank(int rank, int size) throws ExceptionBoundaryViolation{
        if(rank < 0 || rank > size-1) throw new ExceptionBoundaryViolation("invalid rank");
    }
    @Override
    public Position rank2Pos(int rank) throws ExceptionBoundaryViolation {
        DoubleLinkedNode node;
        checkRank(rank, getSize());
        if(rank < getSize()/2){
            node = header.getNext();
            for(int i = 0; i < rank; i++) node = node.getNext();
        }
        else{
            node = tailer.getPrevious();
            for(int i = 1; i < getSize()-rank; i++) node = node.getPrevious();
        }
        return node;
    }
    @Override
    public int pos2Rank(Position position) throws ExceptionPositionInvalid {
        DoubleLinkedNode node = header.getNext();
        int rank = 0;
        while(node != tailer){
            if(node == position) return rank;
            node = node.getNext();
            rank++;
        }
        throw new ExceptionBoundaryViolation("invalid position");
    }
    @Override
    public Object getAtRank(int rank) throws ExceptionBoundaryViolation {
        checkRank(rank, getSize());
        return rank2Pos(rank).getElement();
    }
    @Override
    public Object replaceAtRank(int rank, Object element) throws ExceptionBoundaryViolation {
        checkRank(rank, getSize());
        return replace(rank2Pos(rank), element);
    }
    @Override
    public Object insertAtRank(int rank, Object element) throws ExceptionBoundaryViolation {
        checkRank(rank, getSize() + 1);
        if(getSize() == rank) insertLast(element);
        else insertBefore(rank2Pos(rank), element);
        return element;
    }
    @Override
    public Object removeAtRank(int rank) throws ExceptionBoundaryViolation {
        checkRank(rank, getSize());
        return remove(rank2Pos(rank));
    }
}