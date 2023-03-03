package mission;

public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(T[] values) {
        final SimpleList<T> result = new SimpleArrayList<>();
        for (final T value : values) {
            result.add(value);
        }
        return result;
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double result = 0;
        for (int i = 0; i < values.size(); i++) {
            final T value = values.get(i);
            result += value.doubleValue();
        }
        return result;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        final SimpleList<T> result = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            final T value = values.get(i);
            if (value.doubleValue() >= 0) {
                result.add(value);
            }
        }
        return result;
    }

    static <T> void copy(SimpleList<? extends T> laserPrinters, SimpleList<T> printers) {
        for (int i = 0; i < laserPrinters.size(); i++) {
            printers.add(laserPrinters.get(i));
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
