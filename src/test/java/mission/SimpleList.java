package mission;

public interface SimpleList<T> {

    boolean add(T value);

    void add(int index, T value);

    T set(int index, T value);

    T get(int index);

    boolean contains(T value);

    int indexOf(T value);

    int size();

    boolean isEmpty();

    boolean remove(T value);

    T remove(int index);

    static <T> SimpleList<T> fromArrayToList(T ... arrays) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T element : arrays) {
            simpleList.add(element);
        }
        return simpleList;
    }

    static double sum(SimpleList<? extends Number> values) {
        Double sum = 0d;
        for (int index = 0; index < values.size(); index++) {
            sum += values.get(index).doubleValue();
        }
        return sum;
    }

    static <T> SimpleList<T> filterNegative(SimpleList<? extends Number> values) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (int index = 0; index < values.size(); index++) {
            Number element = values.get(index);
            if (element.doubleValue() >= 0) {
                simpleList.add((T) element);
            }
        }
        return simpleList;
    }

    static <T> void copy(SimpleList<? extends T> valuesT1, SimpleList<T> valuesT2) {
        for (int index = 0; index < valuesT1.size(); index++) {
            valuesT2.add(valuesT1.get(index));
        }
    }

    void clear();
}
