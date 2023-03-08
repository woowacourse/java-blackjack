package jcf.mission;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/02/21
 */
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
}
