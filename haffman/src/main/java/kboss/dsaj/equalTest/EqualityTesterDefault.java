package kboss.dsaj.equalTest;

public class EqualityTesterDefault implements EqualityTester{
    public EqualityTesterDefault(){}
    @Override
    public boolean isEqualTo(Object a, Object b) {
        return a.equals(b);
    }
}
