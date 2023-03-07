package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList<E> {

    private Object[] values;
    private int size;


    public SimpleArrayList(int initSize) {
        this.values = new Object[initSize];
        this.size = 0;
    }

    public SimpleArrayList() {
        this(10);
    }

    public SimpleArrayList(E element) {
        this.values = new Object[1];
        this.size = 1;
        this.values[0] = element;
    }

    @Override
    public boolean add(E value) {
        try {
            add(this.size, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void add(int index, E value) {
        realloc();
        if (!(index <= this.size)) {
            throw new IllegalArgumentException("원소를 추가할 위치는 현재 리스트 크기 이하여야 합니다.");
        }
        for (int i = this.size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = (E) value;
        ++this.size;
    }

    private void realloc() {
        if (this.size < this.values.length) {
            return;
        }
        Object[] newMemory = new Object[this.values.length << 1];
        for (int i = 0; i < this.size; i++) {
            newMemory[i] = this.values[i];
        }
        this.values = newMemory;
    }

    @Override
    public E set(int index, E value) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("값을 수정할 수 있는 인덱스의 범위를 벗어났습니다.");
        }
        E beforeValue = (E) values[index];
        values[index] = value;
        return beforeValue;
    }

    @Override
    public E get(int index) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("값을 가져ㅇ 수 있는 인덱스의 범위를 벗어났습니다.");
        }
        return (E) values[index];
    }

    @Override
    public boolean contains(E value) {
        return Arrays.asList(this.values).contains(value);
    }

    @Override
    public int indexOf(E value) {
        int index = 0;
        for (; (index < this.size) && !this.values[index].equals(value); index++)
            ;
        if (index == this.size) {
            return -1;
        }
        return index;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public E remove(int index) {
        validateOutOfBound(index);
        E oldValue = (E) this.values[index];
        if (index == this.size - 1) {
            return removePostProcess(oldValue);
        }
        for (int i = index; i < this.size; i++) {
            this.values[i] = this.values[i + 1];
        }
        return removePostProcess(oldValue);
    }

    @Override
    public boolean remove(E value) {
        int index = indexOf((E) value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    private E removePostProcess(E oldValue) {
        this.values[--this.size] = null;
        return oldValue;
    }

    private void validateOutOfBound(int index) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("삭제할 인덱스는 범위를 벗어날 수 없습니다.");
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < this.size; i++) {
            this.values[i] = null;
        }
        this.size = 0;
    }
}
