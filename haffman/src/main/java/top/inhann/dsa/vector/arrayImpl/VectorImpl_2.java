package top.inhann.dsa.vector.arrayImpl;


import java.io.Serializable;

import top.inhann.dsa.vector.ExceptionBoundaryViolation;
import top.inhann.dsa.vector.Vector;


/**
 * Expandable
 */
public class VectorImpl_2 implements Vector,Serializable{
    private int CAPACITY = 1024;
    private int size = 0;
    private Object[] elements;

    public VectorImpl_2(){
        elements = new Object[CAPACITY];
        size = 0;
    }
    public int getSize() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size==0;
    }
    @Override
    public Object getAtRank(int rank) throws ExceptionBoundaryViolation {
        if(rank > size-1 || rank < 0) throw new ExceptionBoundaryViolation("boundary violation");
        return elements[rank];
    }
    @Override
    public Object replaceAtRank(int rank, Object element) throws ExceptionBoundaryViolation {
        if(rank > size-1 || rank < 0) throw new ExceptionBoundaryViolation("boundary violation");
        Object ele = elements[rank];
        elements[rank] = element;
        return ele;
    }
    @Override
    public Object insertAtRank(int rank, Object element) throws ExceptionBoundaryViolation {
        if(rank > size || rank < 0) throw new ExceptionBoundaryViolation("boundary violation");
        if(size == CAPACITY){
            CAPACITY*=2;
            Object[] elements2 = new Object[CAPACITY];
            for(int i = 0; i < size; i++) elements2[i] = elements[i];
            elements = elements2;
        }
        for(int i = size; i > rank; i--){elements[i] = elements[i-1];}
        elements[rank] = element;
        size++;
        return element;
    }
    @Override
    public Object removeAtRank(int rank) throws ExceptionBoundaryViolation {
        if(rank > size-1 || rank < 0) throw new ExceptionBoundaryViolation("boundary violation");
        Object ele = elements[rank];
        for(int i = rank; i < size-2; i++){elements[i] = elements[i+1];}
        size--;
        return ele;
    }
}