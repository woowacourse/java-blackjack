package study;

public interface SimpleList<T> {
    static <T> void copy(SimpleList<? extends T> list1, SimpleList<? super T> list2) {
        for (int i = 0; i < list1.size(); i++) {
            list2.add(list1.get(i));
        }
    }

    static <T> SimpleList<T> fromArrayToArrayList(T[] array) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T t : array) {
            simpleList.add(t);
        }
        return simpleList;
    }

    static <T> SimpleList<T> fromArrayToLinkedList(T[] array) {
        SimpleList<T> simpleList = new SimpleLinkedList<>();
        for (T t : array) {
            simpleList.add(t);
        }
        return simpleList;
    }

    static double sum(SimpleList<? extends Number> values) {
        double sum = 0.0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleArrayList<T> list) {
        SimpleList<T> filteredList = new SimpleArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() >= 0.0) {
                filteredList.add(list.get(i));
            }
        }
        return filteredList;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleLinkedList<T> list) {
        SimpleList<T> filteredList = new SimpleLinkedList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() >= 0.0) {
                filteredList.add(list.get(i));
            }
        }
        return filteredList;
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
