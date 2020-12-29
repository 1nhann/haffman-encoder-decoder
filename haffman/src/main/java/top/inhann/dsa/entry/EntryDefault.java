package top.inhann.dsa.entry;

public class EntryDefault implements Entry{
    protected Object key;
    protected Object value;
    public EntryDefault(Object key, Object value){
        this.key = key;
        this.value = value;
    }
    public Object getKey() {
        return key;
    }
    public Object getValue() {
        return value;
    }
    public Object setValue(Object value) {
        this.value = value;
        return value;
    }
    public Object setKey(Object key) {
        this.key = key;
        return key;
    }
}
