package techcourse.jcf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        return new SimpleArrayList<>(arrays);
    }

    static <T> double sum(SimpleList<? extends Number> values) {
        double result = 0;
        for (int i = 0; i < values.size(); i++) {
            result += values.get(i).doubleValue();
        }
        return result;
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
