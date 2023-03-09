package techcourse.mission.jcf.generic;

public interface SimpleList<E> {

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

    <T> SimpleList<T> fromArrayToList(T[] values);

    static <T extends Number> double sum(final SimpleList<T> list) {
        double sum = 0D;
        final int size = list.size();
        for (int index = 0; index < size; index++) {
            sum += list.get(index).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> list) {
        SimpleList<T> values = new SimpleArrayList<>();
        final int size = list.size();
        for (int index = 0; index < size; index++) {
            if (list.get(index).doubleValue() >= 0) {
                values.add(list.get(index));
            }
        }
        return values;
    }

    static <T> void copy(SimpleList<? extends T> copyList, SimpleList<? super T> newList) {
        newList.clear();
        final int size = copyList.size();
        for (int index = 0; index < size; index++) {
            newList.add(copyList.get(index));
        }
    }
}
