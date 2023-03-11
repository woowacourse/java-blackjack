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

    static <T extends Number> Double sum(SimpleList<T> list) {
        double number = 0;
        for (int i = 0; i < list.size(); i++) {
            number += list.get(i).doubleValue();
        }
        return number;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<? extends T> list) {
        SimpleList<T> result = new SimpleArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).doubleValue() >= 0) {
                result.add(list.get(i));
            }
        }
        return result;
    }

    static <T> void copy(SimpleList<? super T> list1, SimpleList<? super T> list2) {
        for (int i = 0; i < list1.size(); i++) {
            if (i > list2.size() - 1) {
                list2.add((T) list1.get(i));
            } else {
                list2.set(i, (T) list1.get(i));
            }
        }
        if (list2.size() > list1.size()) {
            for (int i = list2.size() - 1; i >= list1.size(); i--) {
                list2.remove(i);
            }
        }
    }
}
