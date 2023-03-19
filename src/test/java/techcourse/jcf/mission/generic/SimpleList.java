package techcourse.jcf.mission.generic;

public interface SimpleList<T> {

    boolean add(T value);

    void add(int index, T t);

    T set(int index, T t);

    T get(int index);

    boolean contains(T t);

    int indexOf(T t);

    int size();

    boolean isEmpty();

    boolean remove(T t);

    T remove(int index);

    void clear();

    @SafeVarargs
    static <T> SimpleList<T> fromArrayToList(T... ts) {
        SimpleList<T> values = new SimpleArrayList<>();
        for (T t : ts) {
            values.add(t);
        }
        return values;
    }

    static <T extends Number> double sum(SimpleList<T> ts) {
        double sum = 0D;
        final int size = ts.size();
        for (int index = 0; index < ts.size(); index++) {
            sum += ts.get(index).doubleValue();
        }
        return sum;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> ts) {
        final SimpleList<T> values = new SimpleArrayList<>();
        final int size = ts.size();
        for (int index = 0; index < size; index++) {
            if (ts.get(index).doubleValue() >= 0) {
                values.add(ts.get(0));
            }
        }
        return values;
    }

    static <T> SimpleList<T> filterNegativeV2(SimpleList<? extends Number> ts) {
        final SimpleList<T> values = new SimpleArrayList<>();
        final int size = ts.size();
        for (int index = 0; index < size; index++) {
            if (ts.get(index).doubleValue() >= 0) {
                values.add((T) ts.get(0));
            }
        }
        return values;
    }

    static <T> void copy(SimpleList<? extends T> copyValue, SimpleList<? super T> newValue) {
        newValue.clear();
        final int size = copyValue.size();
        for (int index = 0; index < size; index++) {
            newValue.add(copyValue.get(index));
        }
    }
}
