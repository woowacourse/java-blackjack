package techcourse.jcf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] array) {
        final SimpleArrayList<T> SimpleArrayList = new SimpleArrayList<>();
        for (T element : array) {
            SimpleArrayList.add(element);
        }
        return SimpleArrayList;
    }

    static <T extends Number> double sum(SimpleList<T> numberArray) {
        double total = 0;
        while (numberArray.size() != 0) {
            Number remove = (Number) numberArray.remove(0);
            total += remove.doubleValue();
        }
        return total;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> doubleValues) {
        SimpleList<T> filter = new SimpleArrayList<>();
        while (doubleValues.size() != 0) {
            Number remove = doubleValues.remove(0);
            if (remove.doubleValue() >= 0) {
                filter.add((T) remove);
            }
        }
        return filter;
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

