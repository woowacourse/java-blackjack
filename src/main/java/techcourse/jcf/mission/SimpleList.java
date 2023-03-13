package techcourse.jcf.mission;

public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        return new SimpleArrayList<T>(arrays);
    }

    static <T extends Number> double sum(SimpleList<T> simpleList) {
        double totalSum = 0;
        for (int index = 0; index < simpleList.size(); index++) {
            totalSum += simpleList.get(index).doubleValue();
        }
        return totalSum;
    }

    static <T> SimpleList<T> filterNegative(SimpleList<? extends Number> simpleList) {
        SimpleList<T> filtered = new SimpleArrayList<>();
        for (int index = 0; index < simpleList.size(); index++) {
            if (simpleList.get(index).doubleValue() >= 0) {
                filtered.add((T) simpleList.get(index));
            }
        }
        return filtered;
    }

    static void copy(SimpleList<? extends Printer> from, SimpleList<? super Printer> to) {
        for (int index = 0; index < from.size(); index++) {
            to.add(from.get(index));
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
