package kboss.dsaj.map.hashTableImpl;

import kboss.dsaj.entry.Entry;
import kboss.dsaj.equalTest.EqualityTester;
import kboss.dsaj.equalTest.EqualityTesterDefault;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.iterator.listImpl.IteratorElement;
import kboss.dsaj.list.List;
import kboss.dsaj.list.dlinkedImpl.ListImpl;
import kboss.dsaj.map.Map;

public class MapImpl implements Map{
    private Map[] buckets;
    private int bucketNum;
    private final double maxLambda = 0.75;
    private int size;
    private EqualityTester tester; 

    public MapImpl(){
        this(101, new EqualityTesterDefault());
    }
    public MapImpl(int num, EqualityTester tester){
        this.tester = tester;
        this.bucketNum = num;
        buckets = new Map[bucketNum];
        for(int i = 0; i <bucketNum; i++) buckets[i] = new kboss.dsaj.map.listImpl.MapImpl(tester);
        size = 0;
    }
    private int hash(Object key){
        return key.hashCode() % bucketNum;
    }
    private boolean isPrime(int n){
        for(int i = 3; i < 1 + Math.sqrt(n); i++){
            if(n/i*i == n) return false;
        }
        return true;
    }
    private int getPrime(int downBorder){
        if(downBorder < 3) return 3;
        int n = downBorder | 1;
        while(! isPrime(n)) n += 2;
        return n;
    }
    public int getSize() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    @Override
    public Object get(Object key) {
        return buckets[hash(key)].get(key);
    }
    @Override
    public Object put(Object key, Object value) {
        Object oldValue = buckets[hash(key)].put(key, value);
        if(oldValue == null){
            size ++;
            if(size > bucketNum * maxLambda) rehash();
        }
        return oldValue;
    }
    @Override
    public Object remove(Object key) {
        Object oldValue = buckets[hash(key)].remove(key);
        if(oldValue != null) size--;
        return oldValue;
    }
    @Override
    public Iterator entries() {
        List list = new ListImpl();
        for(int i = 0; i < bucketNum; i++){
            Iterator iter = buckets[i].entries();
            while(iter.hasNext()) list.insertLast(iter.getNext());
        }
        return new IteratorElement(list);
    }
    private void rehash(){
        Iterator iter = entries();
        bucketNum = getPrime(bucketNum<<1);
        buckets = new Map[bucketNum];
        for(int i = 0; i <bucketNum; i++){
            buckets[i] = new kboss.dsaj.map.listImpl.MapImpl(tester);
        }
        while(iter.hasNext()){
            Entry entry = (Entry)iter.getNext();
            Object key = entry.getKey();
            Object value = entry.getValue();
            buckets[hash(key)].put(key, value);
        }
    }
    @Override
    public boolean containsKey(Object key){
        if(get(key) == null) return false;
        return true;
    }
}
