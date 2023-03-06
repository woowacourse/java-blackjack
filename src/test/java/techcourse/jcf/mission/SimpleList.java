package techcourse.jcf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(final T[] values) {
        final SimpleArrayList<T> result = new SimpleArrayList<>();
        for (final T value : values) {
            result.add(value);
        }
        return result;
    }

    static <T extends Number> double sum(final SimpleList<T> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(final SimpleList<T> list) {
        final SimpleList<T> result = new SimpleArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() >= 0) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    static <T> void copy(final SimpleList<? extends T> origin, final SimpleList<? super T> copy) {
        for (int i = 0; i < origin.size(); i++) {
            copy.add(origin.get(i));
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
