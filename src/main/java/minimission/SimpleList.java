package minimission;

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
        SimpleList<T> simpleList = new SimpleArrayList<>();

        for (T t : array) {
            simpleList.add(t);
        }

        return simpleList;
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double result = 0;
        for (int i = 0; i < values.size(); i++) {
            result += values.get(i).doubleValue();
        }
        return result;
    }

    static SimpleList filterNegative(SimpleList<? extends Number> values) {
        SimpleArrayList list = new SimpleArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            Number number = values.get(i);

            if (number.doubleValue() >= 0) {
                list.add(number);
            }
        }

        return list;
    }

    static void copy(SimpleList<? extends Printer> from, SimpleList<? super Printer> to) {
        for (int i = 0; i < from.size(); i++) {
            Printer printer = from.get(i);
            to.add(printer);
        }
    }
}
