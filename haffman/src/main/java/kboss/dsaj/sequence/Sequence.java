package kboss.dsaj.sequence;

import kboss.dsaj.position.Position;
import kboss.dsaj.list.ExceptionPositionInvalid;
import kboss.dsaj.list.List;
import kboss.dsaj.vector.ExceptionBoundaryViolation;
import kboss.dsaj.vector.Vector;

public interface Sequence extends Vector,List{
    public Position rank2Pos(int rank) throws ExceptionBoundaryViolation;
    public int pos2Rank(Position position) throws ExceptionPositionInvalid;
}