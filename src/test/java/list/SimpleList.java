package list;

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
        return new SimpleArrayList<T>(array);
    }

    static <T extends Number> double sum(SimpleList<T> simpleList) {
        double sum = 0;
        for (int i = 0; i < simpleList.size(); i++) {
            sum += simpleList.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> simpleList) {
        SimpleList<T> newList = new SimpleArrayList<>();
        for (int i = 0; i < simpleList.size(); i++) {
            final T element = simpleList.get(i);
            if (element.doubleValue() >= 0) {
                newList.add(element);
            }
        }
        return newList;
    }
}