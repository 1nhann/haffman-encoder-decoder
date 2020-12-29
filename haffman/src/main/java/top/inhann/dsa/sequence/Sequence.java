package top.inhann.dsa.sequence;

import top.inhann.dsa.position.Position;

import top.inhann.dsa.list.ExceptionPositionInvalid;
import top.inhann.dsa.list.List;
import top.inhann.dsa.vector.ExceptionBoundaryViolation;
import top.inhann.dsa.vector.Vector;

public interface Sequence extends Vector,List{
    public Position rank2Pos(int rank) throws ExceptionBoundaryViolation;
    public int pos2Rank(Position position) throws ExceptionPositionInvalid;
}