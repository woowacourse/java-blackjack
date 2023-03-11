package techcourse.jcf.mission;

public interface SimpleList<E> {

    static <E> SimpleList<E> fromArrayToList(E[] values) {
        return new SimpleArrayList<>(values);
    }

    static <E> double sum(SimpleList<E> values) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += Double.parseDouble(values.get(i).toString());
        }
        return sum;
    }

    static <E extends java.lang.Number> SimpleList<E> filterNegative(SimpleList<E> values) {
        SimpleList<E> result = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            E value = values.get(i);
            if (value.doubleValue() >= 0.0) {
                result.add(value);
            }
        }
        return result;
    }

    boolean add(E value);

    void add(int index, E value);

    E set(int index, E value);

    E get(int index);

    boolean contains(E value);

    int indexOf(E value);

    int size();

    boolean isEmpty();

    boolean remove(E value);

    E remove(int index);

    void clear();
}
