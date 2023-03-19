package techcourse.jcf.mission.generic;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Optional;

public class SimpleArrayList<T> implements SimpleList<T> {
    private static final int DEFAULT_SIZE = 200;

    private transient int currentIndex;
    private int size = DEFAULT_SIZE;
    private T[] lists;

    public SimpleArrayList() {
        this.lists = (T[]) new Object[DEFAULT_SIZE];
    }

    @SafeVarargs
    public SimpleArrayList(T... ts) {
        this.lists = ts;
        currentIndex = ts.length;
    }

    @Override
    public boolean add(T t) {
        if (checkArrayFull()) {
            lists = allocateNewArraySize();
        }
        lists[currentIndex++] = t;
        return true;
    }

    @Override
    public void add(int index, T t) {
        if (checkArrayFull()) {
            lists = allocateNewArraySize();
        }
        pushOneSpaceBefore(index);
        lists[index] = t;
    }

    @Override
    public T set(int index, T t) {
        validateOutOfRange(index);
        lists[index] = t;
        return t;
    }

    @Override
    public T get(int index) {
        validateOutOfRange(index);
        return lists[index];
    }

    @Override
    public boolean contains(T t) {
        return Arrays.asList(lists).contains(t);
    }

    @Override
    public int indexOf(T t) {
        for (int index = 0; index < currentIndex; index++) {
            if (lists[index].equals(t)) {
                return index;
            }
        }
        throw new NullPointerException("존재하지 않는 값입니다.");
    }

    @Override
    public int size() {
        return currentIndex;
    }

    @Override
    public boolean isEmpty() {
        return currentIndex == 0;
    }

    @Override
    public boolean remove(T t) {
        for (int index = 0; index < currentIndex; index++) {
            if (lists[index].equals(t)) {
                pullOneSpaceAfter(index);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        validateOutOfRange(index);
        validateNotExists(lists[index]);
        final T removedValue = lists[index];
        pullOneSpaceAfter(index);

        return removedValue;
    }

    @Override
    public void clear() {
        currentIndex = 0;
        Arrays.fill(lists, null);
    }

    private T[] allocateNewArraySize() {
        size *= 1.5;
        return Arrays.copyOf(lists, size);
    }

    private boolean checkArrayFull() {
        return currentIndex + 1 == size;
    }

    private void pullOneSpaceAfter(int index) {
        for (int findIndex = index; findIndex < currentIndex; findIndex++) {
            lists[findIndex] = lists[findIndex + 1];
        }
    }

    private void pushOneSpaceBefore(int index) {
        for (int lastIndex = currentIndex; lastIndex > index; lastIndex--) {
            lists[lastIndex + 1] = lists[lastIndex];
        }
    }

    private void validateOutOfRange(int index) {
        if (0 > index || index >= currentIndex) {
            throw new IndexOutOfBoundsException(MessageFormat.format("{0} 는 범위를 벗어난 인덱스입니다.", index));
        }
    }

    private <T> void validateNotExists(T t) {
        Optional.ofNullable(t).orElseThrow(() -> new NullPointerException("존재하지 않는 값입니다."));
    }
}
