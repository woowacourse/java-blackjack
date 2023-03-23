package generic;

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

    void clear();

    static Double sum(final SimpleList<? extends Number> list) {
        double sum = list.get(0).doubleValue();
        for (int index = 1; index < list.size(); index++) {
            sum += list.get(index).doubleValue();
        }
        return sum;
    }
}
