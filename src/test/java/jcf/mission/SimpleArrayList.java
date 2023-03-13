package jcf.mission;

import java.util.Arrays;

/**
 * @author 우가
 * @version 1.0.0
 * @Created by 우가 on 2023/03/08
 */
public class SimpleArrayList<T> implements SimpleList<T> {

    // null을 허용하는가?
    // array list -> null <- 구현완료
    private Object[] list;

    private int currentInsertIndex = 0;

    public SimpleArrayList() {
        this.list = new Object[10];
    }

    public <T> SimpleArrayList(final T... values) {
        this.list = new Object[10];
        for (T value : values) {
            add(value);
        }
    }

    @Override
    public <T> boolean add(T value) {
        // 100 <- 전부 반복 <- add <- o(1) 구현완료
        if (currentInsertIndex == list.length) {
            Object[] newArray = new Object[list.length >> 1 + list.length];
            System.arraycopy(list, 0, newArray, 0, list.length);
            newArray[currentInsertIndex++] = value;
            list = newArray;
            return true;
        }
        list[currentInsertIndex++] = value;
        return true;
    }

    @Override
    public void add(final int index, final T value) {
        if (list.length <= index) {
            int i = list.length + (list.length >> 1);
            System.out.println("i = " + i);
            Object[] newArray = new Object[(list.length >> 1) + list.length];
            System.arraycopy(list, 0, newArray, 0, list.length);
            newArray[index] = value;
            currentInsertIndex = index + 1;
            list = newArray;
            return;
        }
        for (int i = 0; i < list.length; i++) {
            if (index == i) {
                Object temp = list[i];
                list[i] = value;
                for (int j = i + 1; j < list.length - 1; j++) {
                    if (j == index + 1) {
                        list[j] = temp;
                        continue;
                    }
                    list[j] = list[j + 1];
                }
                currentInsertIndex++;
                break;
            }
        }
    }

    @Override
    public T set(final int index, final T value) {
        if (list.length <= index) {
            list = new Object[index + 1];
            list[index] = value;
            return null;
        }
        Object temp = list[index];
        list[index] = value;
        return (T) temp;
    }

    @Override
    public T get(final int index) {
        if (currentInsertIndex <= index) {
            throw new IndexOutOfBoundsException("인덱스를 확인해주세요.");
        }
        return (T) list[index];
    }

    @Override
    public boolean contains(final T value) {
        for (Object str : list) {
            if (str == null) {
                return false;
            }
            if (str.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(T value) {
        for (int index = 0; index < list.length; index++) {
            if (list[index] == null) {
                continue;
            }
            if (list[index].equals(value)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return currentInsertIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentInsertIndex == 0;
    }

    @Override
    public boolean remove(final T value) {
        for (int i = 0; i < list.length; i++) {
            // 매번 삭제되면 복사를 해야할까?
            if (list[i] == null) {
                continue;
            }
            if (list[i].equals(value)) {
                if (i == 0) {
                    for (int j = 0; j < list.length - 1; j++) {
                        list[j] = list[j + 1];
                    }
                } else {
                    for (int j = 0; j < list.length; j++) {
                        if (j >= i - 1) {
                            list[i] = list[i + 2];
                            continue;
                        }
                        list[i] = list[i + 1];
                    }
                }
                currentInsertIndex--;
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(final int index) {
        if (currentInsertIndex <= index) {
            throw new IndexOutOfBoundsException("인덱스를 확인해주세요.");
        }
        Object temp = list[index];
        remove((T) temp);
        return (T) temp;
    }

    @Override
    public void clear() {
        list = new String[10];
        currentInsertIndex = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(list);
    }
}
