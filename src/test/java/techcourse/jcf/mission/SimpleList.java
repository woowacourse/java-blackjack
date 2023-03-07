package techcourse.jcf.mission;

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

    static <T> SimpleArrayList<T> fromArrayToList(T... values) {
        return new SimpleArrayList<>(values);
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double totalSum = 0;

        for (int i = 0; i < values.size(); i++) {
            totalSum += values.get(i).doubleValue();
        }

        return totalSum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        SimpleArrayList<T> result = new SimpleArrayList<>();

        for (int i = 0; i < values.size(); i++) {
            if (0 <= values.get(i).doubleValue()) {
                result.add(values.get(i));
            }
        }

        return result;
    }

    static <T extends R, R> SimpleList<R> copy(SimpleList<T> from, SimpleList<R> to) {
        for (int i = 0; i < from.size(); i++) {
            to.add(from.get(i));
        }

        return to;
    }

}
