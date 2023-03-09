package mini;

public interface SimpleList<T> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        SimpleList<T> result = new SimpleArrayList<>();
        for (T t : arrays) {
            result.add(t);
        }

        return result;
    }

//    static <T extends Number> double sum(SimpleList<T> numbers) {
//        double result = 0;
//        for (int i = 0; i < numbers.size(); i++) {
//            result += numbers.get(i).doubleValue();
//        }
//
//        return result;
//    }

    static double sum(SimpleList<? extends Number> numbers) {
        double result = 0;
        for (int i = 0; i < numbers.size(); i++) {
            result += numbers.get(i).doubleValue();
        }

        return result;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<? extends T> numbers) {
        SimpleArrayList<T> result = new SimpleArrayList<>();

        for (int i = 0; i < numbers.size(); i++) {
            if (numbers.get(i).doubleValue() >= 0) {
                result.add(numbers.get(i));
            }
        }

        return result;
    }

    static <T> void copy(SimpleList<? extends T> from, SimpleList<? super T> to) {
        to.clear();
        for (int i = 0; i < from.size(); i++) {
            to.add(from.get(i));
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