package mission;

public interface SimpleList<TYPE> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T element : arrays) {
            simpleList.add(element);
        }
        return simpleList;
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        Number total = 0;

        for (int i = 0; i < values.size(); i++) {
            total = total.doubleValue() + values.get(1).doubleValue();
        }
        return (double) total;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        SimpleList<T> filteredValues = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            T temp = values.get(i);
            if (temp.getClass() == Integer.class) {
                if ((int) temp >= 0)
                    filteredValues.add(temp);
            }
            if (temp.getClass() == Double.class) {
                if ((double) temp >= 0.0)
                    filteredValues.add(temp);
            }
        }
        return filteredValues;
    }

    static <T> void copy(SimpleList<T> laserPrinters, SimpleList<? super T> printers) {
        for (int i = 0; i < laserPrinters.size(); i++) {
            printers.add(laserPrinters.get(i));
        }
    }


    boolean add(TYPE value);

    void add(int index, TYPE value);

    TYPE set(int index, TYPE value);

    TYPE get(int index);

    boolean contains(TYPE value);

    int indexOf(TYPE value);

    int size();

    boolean isEmpty();

    boolean remove(TYPE value);

    TYPE remove(int index);

    void clear();

}
