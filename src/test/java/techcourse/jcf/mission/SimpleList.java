package techcourse.jcf.mission;

public interface SimpleList<E> {

    boolean add(E value);

    void add(int index, E value);

    String set(int index, String value);

    E get(int index);

    boolean contains(String value);

    int indexOf(String value);

    int size();

    boolean isEmpty();

    boolean remove(String value);

    String remove(int index);

    void clear();

    static <E> SimpleList<E> fromArrayToList(Object[] values) {
        SimpleList<E> objectSimpleList = new SimpleArrayList<>();
        for (Object value : values) {
            objectSimpleList.add((E) value);
        }
        return objectSimpleList;
    }
}
