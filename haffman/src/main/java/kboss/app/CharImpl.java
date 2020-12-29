package kboss.app;

public class CharImpl implements Char{
    Double weight;
    Character c;
    @Override
    public Object getWeight() {
        return weight;
    }
    public Character getC() {
        return c;
    }
    public CharImpl(Character c, Double weight){
        this.c = c;
        this.weight = weight;
    }
}
