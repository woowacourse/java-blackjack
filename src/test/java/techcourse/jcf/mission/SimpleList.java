package techcourse.jcf.mission;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] objects) {
        SimpleArrayList<T> simpleArrayList = new SimpleArrayList<>();
        for (T object : objects) {
            simpleArrayList.add(object);
        }
        return simpleArrayList;
    }

    static <T extends Number> double sum(SimpleList<T> simpleList) {
        double sum = 0;
        for (int i = 0; i < simpleList.size(); i++) {
            sum += simpleList.get(i).doubleValue();
        }
        return sum;
    }

    static <T> SimpleList<T> filterNegative(SimpleList<? extends Number> simpleList) {
        SimpleList<T> resultList = new SimpleArrayList<>();
        for (int i = 0; i < simpleList.size(); i++) {
            if (simpleList.get(i).doubleValue() >= 0) {
                resultList.add((T) simpleList.get(i));
            }
        }
        return resultList;
    }

    static <T> SimpleList<T> copy(SimpleList<? extends T> listToCopy, SimpleList<T> target) {
        for (int i = 0; i < listToCopy.size(); i++) {
            target.add(listToCopy.get(i));
        }
        return target;
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