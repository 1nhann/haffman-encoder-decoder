package kboss.dsaj.map.listImpl;

import kboss.dsaj.entry.Entry;
import kboss.dsaj.entry.EntryDefault;
import kboss.dsaj.equalTest.EqualityTester;
import kboss.dsaj.equalTest.EqualityTesterDefault;
import kboss.dsaj.iterator.Iterator;
import kboss.dsaj.iterator.listImpl.IteratorElement;
import kboss.dsaj.list.List;
import kboss.dsaj.list.dlinkedImpl.ListImpl;
import kboss.dsaj.map.Map;
import kboss.dsaj.position.Position;
 
public class MapImpl implements Map{
    private List list;
    private EqualityTester tester;
    public MapImpl(){
        this(new EqualityTesterDefault());
    }
    public MapImpl(EqualityTester tester){
        list = new ListImpl();
        this.tester = tester;
    }
    @Override
    public int getSize() {
        return list.getSize();
    }
    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }
    @Override
    public Object get(Object key) {
        Iterator positions = list.positions();
        while(positions.hasNext()){
            Position pos = (Position)positions.getNext();
            Entry entry = (EntryDefault)pos.getElement();
            if(tester.isEqualTo(entry.getKey(), key)) return entry.getValue();
        }
        return null;
    }
    @Override
    public Object put(Object key, Object value) {
        Iterator positions = list.positions();
        while(positions.hasNext()){
            Position pos = (Position)positions.getNext();
            Entry entry = (EntryDefault)pos.getElement();
            if(tester.isEqualTo(entry.getKey(), key)){
                Object oldValue = entry.getValue();
                list.replace(pos, new EntryDefault(key, value));
                return oldValue;
            }
        }
        list.insertFirst(new EntryDefault(key, value));
        return null;
    }
    @Override
    public Object remove(Object key) {
        Iterator positions = list.positions();
        while(positions.hasNext()){
            Position pos = (Position)positions.getNext();
            Entry entry = (EntryDefault)pos.getElement();
            if(tester.isEqualTo(entry.getKey(), key)){
                Object oldValue = entry.getValue();
                list.remove(pos);
                return oldValue;
            }
        }
        return null;
    }
    @Override
    public Iterator entries() {
        return new IteratorElement(list);
    }
    @Override
    public boolean containsKey(Object key){
        if(get(key) == null) return false;
        return true;
    }
}
