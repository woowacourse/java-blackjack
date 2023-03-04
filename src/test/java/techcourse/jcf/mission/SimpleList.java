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

    static <T> SimpleList<T> fromArrayToList(T[] array) {
        SimpleList<T> list = new SimpleLinkedList<>();
        for (T value : array) {
            list.add(value);
        }
        return list;
    }
}
