package techcourse.jcf.mission;

public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        final SimpleArrayList<T> result = new SimpleArrayList<>();
        for (final T element : arrays) {
            result.add(element);
        }
        return result;
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double result = 0d;
        for (int i = 0; i < values.size(); i++) {
            result += values.get(i).doubleValue();
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
