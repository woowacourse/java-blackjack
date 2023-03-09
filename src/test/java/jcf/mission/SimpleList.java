package jcf.mission;

import java.util.logging.Logger;
import jcf.mission.GenericStudy.Printer;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/02/21
 */
public interface SimpleList<T> {

    static SimpleList<String> fromArrayToList(String[] values) {
        return new SimpleArrayList<String>(values);
    }

    static double sum(SimpleList<? extends Number> values) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum = sum + values.get(i).doubleValue();
        }
        return sum;
    }

    static <T> SimpleList<T> filterNegative(SimpleList<? extends Number> values) {
        SimpleArrayList<T> list = new SimpleArrayList<T>();
        for (int i = 0; i < values.size(); i++) {
            double value = values.get(i).doubleValue();
            if (value > 0) {
                list.add((T) values.get(i));
            }
        }
        return list;
    }

    static <E> void copy(SimpleList<? extends E> copy, SimpleList<? super E> origin) {
        origin.clear();
        for (int i = 0; i < copy.size(); i++) {
            origin.add(copy.get(i));
        }
    }

    <T> boolean add(T value);

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
