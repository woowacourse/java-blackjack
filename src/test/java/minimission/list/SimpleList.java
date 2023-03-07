package minimission.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface SimpleList<E> {
    
    static <E> SimpleList<E> fromArrayToList(E[] arrays) {
        SimpleArrayList<E> simpleArrayList = new SimpleArrayList<>();
        
        for (E o : arrays) {
            System.out.println(o.getClass().getName());
            simpleArrayList.add(o);
        }
        
        return simpleArrayList;
    }
    
    boolean add(E value);

    void add(int index, E value);
    
    E set(int index, E value);
    
    E get(int index);

    boolean contains(E value);

    int indexOf(E value);

    int size();

    boolean isEmpty();

    boolean remove(E value);
    
    E remove(int index);

    void clear();
}
