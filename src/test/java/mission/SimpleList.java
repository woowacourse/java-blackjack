package mission;

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

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        final SimpleArrayList<T> tSimpleArrayList = new SimpleArrayList<>();
        for (T array : arrays) {
            tSimpleArrayList.add(array);
        }
        return tSimpleArrayList;
    }
}
