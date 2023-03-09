package techcourse.jcf.mission;

public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(final T[] array) {
        final SimpleList<T> simpleList = new SimpleArrayList<>();

        for (final T t : array) {
            simpleList.add(t);
        }

        return simpleList;
    }

    static <T extends Number> double sum(final SimpleList<T> simpleList) {
        double sumOfElement = 0;

        for (int i = 0; i < simpleList.size(); i++) {
            sumOfElement += simpleList.get(i).doubleValue();
        }

        return sumOfElement;
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
