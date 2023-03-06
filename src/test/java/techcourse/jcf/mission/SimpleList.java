package techcourse.jcf.mission;

import java.util.ArrayList;
import java.util.List;

public interface SimpleList<E> {

    static <T> SimpleList<T> fromArrayToList(T[] arrays) {
        final SimpleArrayList<T> result = new SimpleArrayList<>();
        for (final T element : arrays) {
            result.add(element);
        }
        return result;
    }

    static <T> SimpleList<T> create(List<T> list) {
        final SimpleArrayList<T> result = new SimpleArrayList<>();
        for (final T element : list) {
            result.add(element);
        }
        return result;
    }

    static <T extends Number> double sum(SimpleList<T> values) {
        double result = 0d;
        for (int i = 0; i < values.size(); i++) {
            result += values.get(i).doubleValue();
        }
        return result;
    }

    static <T extends Number> SimpleList<T> filterNegative(SimpleList<T> values) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < values.size(); i++) {
            if (values.get(i).doubleValue() < 0) {
                result.add(values.get(i));
            }
        }
        return create(result);
    }

    static <T> void copy(SimpleList<? super T> superClass, SimpleList<T> subClass) {
        for (int i = 0; i < subClass.size(); i++) {
            superClass.add(subClass.get(i));
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
