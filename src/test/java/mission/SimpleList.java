package mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T... arrays) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T element : arrays) {
            simpleList.add(element);
        }
        return simpleList;
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            T element = values.get(i);
            if (element.doubleValue() >= 0) {
                simpleList.add(element);
            }
        }
        return simpleList;
    }

    static <T> void copy(SimpleList<? extends T> values1, SimpleList<T> values2) {
        for (int i = 0; i < values1.size(); i++) {
            values2.add(values1.get(i));
        }
    }

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

    void clear();
}
