package techcourse;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(final T[] array) {
        final SimpleArrayList<T> tSimpleArrayList = new SimpleArrayList<>();
        for (final T t : array) {
            tSimpleArrayList.add(t);
        }
        return tSimpleArrayList;
    }

    static <T extends Number> double sum(final SimpleList<T> values) {
        double total = 0;
        while (!values.isEmpty()) {
            final T remove = values.remove(0);
            total += remove.doubleValue();
        }
        return total;
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