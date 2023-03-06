package techcourse.jcf.mission;

public interface SimpleList<T> {
    static <T> SimpleList<T> fromArrayToList(T[] values) {
        SimpleList<T> simpleList = new SimpleArrayList<>();
        for (T value : values) {
            simpleList.add(value);
        }
        return simpleList;
    }

    static double sum(SimpleList<? extends Number> values) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum += values.get(i).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<? extends Number> values) {
        SimpleArrayList<T> simpleArrayList = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            T value = (T) values.get(i);
            if (value.doubleValue() >= 0) {
                simpleArrayList.add(value);
            }
        }
        return simpleArrayList;
    }

    static <T> void copy(SimpleList<? extends T> source, SimpleList<? super T> target) {
        int maxSize = Math.max(source.size(), target.size());
        for (int i = 0; i < maxSize; i++) {
            target.add(source.get(i));
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
