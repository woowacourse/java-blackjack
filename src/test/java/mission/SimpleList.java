package mission;

public interface SimpleList<E> {

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

    static <E> SimpleArrayList fromArrayToList(E[] array){
        SimpleArrayList simple = new SimpleArrayList();
        for (E e : array) {
            simple.add(e);
        }
        return simple;
    }

    static <E extends Number> Double sum(SimpleList<E> value){
        double results = 0;
        for(int i=0; i<value.size(); i++){
            results += value.get(i).doubleValue();
        }
        return results;
    }

    static <E extends Number> SimpleList<E> filterNegative(SimpleList<E> value){
        SimpleList<E> result = new SimpleArrayList<>();
        for(int i=0; i<value.size(); i++){
            if(value.get(i).doubleValue() >= 0){
                result.add(value.get(i));
            }
        }
        return result;
    }

    static <E> void copy(SimpleList<? extends E> laserPrinters, SimpleList<E> printers){
        for (int i = 0; i < laserPrinters.size(); i++) {
            printers.add(laserPrinters.get(i));
        }
    }
}
