package mission;

import java.util.List;

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

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        final SimpleArrayList<T> tSimpleArrayList = new SimpleArrayList<>();
        for (T array : arrays) {
            tSimpleArrayList.add(array);
        }
        return tSimpleArrayList;
    }

    static <T extends Number> T sum(SimpleList<T> values) {
        if (values.isEmpty()) {
            return null;
        }

        T value = values.get(0);
        if (value instanceof Integer) {
            Integer sum = 0;
            for (int i = 0; i < values.size(); i++) {
                sum += values.get(i).intValue();
            }
            return (T) sum;
        }

        if (value instanceof Double) {
            Double sum = 0d;
            for (int i = 0; i < values.size(); i++) {
                sum += values.get(i).doubleValue();
            }
            return (T) sum;
        }

        throw new UnsupportedOperationException();
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        SimpleList<T> tmpList = new SimpleArrayList<>();

        for(int i =0; i<values.size(); i++){
            if(values.get(i).doubleValue() >= 0){
                tmpList.add(values.get(i));
            }
        }
        return tmpList;
    }
}
