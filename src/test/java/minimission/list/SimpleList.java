package minimission.list;

public interface SimpleList<E> {
    
    static <E> SimpleList<E> fromArrayToList(E[] arrays) {
        SimpleArrayList<E> simpleArrayList = new SimpleArrayList<>();
        
        for (E o : arrays) {
            simpleArrayList.add(o);
        }
        
        return simpleArrayList;
    }
    
    static <E extends Number> double sum(SimpleList<E> values) {
        double sum = 0.0;
        
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i).doubleValue();
        }
        
        return sum;
        
    }
    
    static <E extends Number> SimpleList<E> filterNegative(SimpleList<E> doubleValues) {
        SimpleList<E> tmpArr = new SimpleArrayList<>();
    
        for (int i = 0; i < doubleValues.size(); i++) {
            E value = doubleValues.get(i);
            if (value.doubleValue() >= 0) {
                tmpArr.add(value);
            }
        }
        
        return tmpArr;
    }
    
    static <E> void copy(SimpleList<? extends E> laserPrinters, SimpleList<E> printers) {
        printers.clear();
        
        for (int i = 0; i < laserPrinters.size(); i++) {
            printers.add(laserPrinters.get(i));
        }
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
