package techcourse.jcf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] objects) {
        SimpleArrayList<T> simpleArrayList = new SimpleArrayList<>();
        for (T object : objects) {
            simpleArrayList.add(object);
        }
        return simpleArrayList;
    }

    static double sum(SimpleList<? extends Number> list) {
        double sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> list) {
        SimpleArrayList<T> newList = new SimpleArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() >= 0) {
                newList.add(list.get(i));
            }
        }
        return newList;
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
