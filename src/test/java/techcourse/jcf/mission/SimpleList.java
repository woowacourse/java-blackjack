package techcourse.jcf.mission;

import java.util.ArrayList;
import techcourse.jcf.mission.study.LaserPrinter;
import techcourse.jcf.mission.study.Printer;

public interface SimpleList<E> extends Iterable<E>{

    static <E> SimpleList<E> fromArrayToList(E[] arrays) {
        SimpleArrayList simpleArrayList = new SimpleArrayList(arrays.length);
        for (E value : arrays) {
            simpleArrayList.add(value);
        }
        return simpleArrayList;
    }

    static <E extends Number> double sum(SimpleList<E> values) {
        double sum = 0;
        for (int i = 0; i < values.size(); i++) {
            sum +=  values.get(i).doubleValue();
        }
        return sum;
    }

    static <E extends Number> SimpleList<E> filterNegative(SimpleList<E> list) {
        SimpleArrayList<E> result = new SimpleArrayList();
        for (int i = 0; i < list.size(); i++) {
            E number = list.get(i);
            if (number.doubleValue() > 0) {
                result.add(number);
            }
        }
        return result;
    }

    static <E> void copy(SimpleList<E> srcList, SimpleList<? super E> targetList) {
        for (E e : srcList) {
            targetList.add(e);
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
