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

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        SimpleArrayList<T> simpleArrayList = new SimpleArrayList<>();
        for (T element : arrays) {
            simpleArrayList.add(element);
        }
        return simpleArrayList;
    }

    static double sum(SimpleList<? extends Number> values) {
        double total = 0;
        for (int i = 0; i < values.size(); i++) {
            total += values.get(i).doubleValue();
        }
        return total;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        SimpleArrayList<T> simpleArrayList = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).doubleValue() >= 0) {
                simpleArrayList.add(values.get(i));
            }
        }
        return simpleArrayList;
    }

    static <T> void copy(SimpleList<? extends T> origin, SimpleList<T> other) {
        for(int i = 0; i<origin.size(); i++) {
            other.add(origin.get(i));
        }
    }
}
