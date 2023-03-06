package techcourse.jcf.mission;

public interface SimpleList<T> {
    static <T> SimpleList<T> fromArrayToList(T[] values) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T value : values) {
            simpleList.add(value);
        }
        return simpleList;
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
