package techcourse.jcf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] objects) {
        SimpleArrayList<T> simpleArrayList = new SimpleArrayList<>();
        for (T object : objects) {
            simpleArrayList.add(object);
        }
        return simpleArrayList;
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
