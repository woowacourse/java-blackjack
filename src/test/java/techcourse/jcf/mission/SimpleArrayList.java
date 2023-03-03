package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList implements SimpleList {

    private String[] values;
    private int size;


    public SimpleArrayList(int initSize) {
        this.values = new String[initSize];
        this.size = 0;
    }

    public SimpleArrayList() {
        this(10);
    }

    @Override
    public boolean add(String value) {
        try {
            add(this.size, value);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void add(int index, String value) {
        realloc();
        if (!(index <= this.size)) {
            throw new IllegalArgumentException("원소를 추가할 위치는 현재 리스트 크기 이하여야 합니다.");
        }
        for (int i = this.size; i > index; i--) {
            values[i] = values[i - 1];
        }
        values[index] = value;
        ++this.size;
    }

    private void realloc() {
        if (this.size < this.values.length) {
            return;
        }
        String[] newMemory = new String[this.values.length * 2];
        for (int i = 0; i < this.size; i++) {
            newMemory[i] = this.values[i];
        }
        this.values = newMemory;
    }

    @Override
    public String set(int index, String value) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("값을 수정할 수 있는 인덱스의 범위를 벗어났습니다.");
        }
        String beforeValue = values[index];
        values[index] = value;
        return beforeValue;
    }

    @Override
    public String get(int index) {
        if (!(0 <= index && index < this.size)) {
            throw new IllegalArgumentException("값을 가져ㅇ 수 있는 인덱스의 범위를 벗어났습니다.");
        }
        return values[index];
    }

    @Override
    public boolean contains(String value) {
        return Arrays.asList(this.values).contains(value);
    }

    @Override
    public int indexOf(String value) {
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
        if (this.size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(String value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        remove(index);
        return true;
    }

    @Override
    public String remove(int index) {
        validateOutOfBound(index);
        String oldValue = this.values[index];
        if (index == this.size - 1) {
            return removePostProcess(oldValue);
        }
        for (int i = index; i < this.size; i++) {
            this.values[i] = this.values[i + 1];
        }
        return removePostProcess(oldValue);
    }

    private String removePostProcess(String oldValue) {
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
