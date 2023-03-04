package techcourse.icf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        SimpleList<T> result = new SimpleArrayList<T>();
        for (T value : arrays) {
            result.add(value);
        }
        return result;
    }

    static <T extends Number> Double sum(SimpleList<T> values) {
        Double sum = Double.valueOf(0);
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i).doubleValue();
        }
        return sum;
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
