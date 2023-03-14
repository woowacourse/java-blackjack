package mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] array) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T t : array) {
            simpleList.add(t);
        }
        return simpleList;
    }

    static double sum(SimpleList<? extends Number> simpleList) {
        double sum = 0;
        for (int i = 0; i < simpleList.size(); i++) {
            sum += simpleList.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> simpleList) {
        SimpleList<T> results = new SimpleArrayList<>();
        for (int i = 0; i < simpleList.size(); i++) {
            T target = simpleList.get(i);
            if (target.doubleValue() > 0) {
                results.add(target);
            }
        }
        return results;
    }

    static <T, E extends T> void copy(SimpleList<E> originalList, SimpleList<T> targetList) {
        targetList.clear();
        for (int i = 0; i < originalList.size(); i++) {
            targetList.add(originalList.get(i));
        }
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