package techcourse.jcf.mission;

public interface SimpleList<E> {

    static <E> SimpleList<E> fromArrayToList(final E[] values) {
        return new SimpleArrayList<>(values);
    }

    static <E extends Number> double sum(final SimpleList<E> values) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i).doubleValue();
        }
        return sum;
    }

    static <E extends Number> SimpleList<E> filterNegative(final SimpleList<E> values) {
        final SimpleList<E> result = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            final E value = values.get(i);
            if (value.doubleValue() >= 0.0) {
                result.add(value);
            }
        }
        return result;
    }

    static <S extends D, D> void copy(final SimpleList<S> source, final SimpleList<D> dest) {
        if (source.size() < dest.size()) {
            for (int i = source.size(); i < dest.size(); i++) {
                dest.remove(i);
            }
        }
        for (int i = 0; i < source.size(); i++) {
            dest.add(source.get(i));
        }
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
