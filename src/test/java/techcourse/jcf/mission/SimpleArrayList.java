package techcourse.jcf.mission;

import java.util.Arrays;

public class SimpleArrayList<E> implements SimpleList {

    private static final Object[] DEFAULT_CAPACITY = {};

    public Object[] elementData;

    private int size;

    public SimpleArrayList() {
        this.elementData = DEFAULT_CAPACITY;
        this.size = 0;
    }

    public SimpleArrayList(Printer printer){
        this.elementData = DEFAULT_CAPACITY;
        add(printer);
        this.size = elementData.length;
    }

    public SimpleArrayList(E[] arraysAsList) {
        this.elementData = arraysAsList;
        this.size = arraysAsList.length;
    }

    public SimpleArrayList(Number n1, Number n2) {
        this.elementData = new Object[2];
        elementData[0] = n1;
        elementData[1] = n2;
        this.size = 2;
    }

    public SimpleArrayList(Number n1, Number n2, Number n3) {
        this.elementData = new Number[3];
        elementData[0] = n1;
        elementData[1] = n2;
        elementData[2] = n3;
        this.size = 3;
    }

    public SimpleArrayList(Number[] numbers){
        this.elementData = new Number[0];
        for (int i = 0; i < numbers.length; i++) {
            if (numbers[i].floatValue()>=0){
                add(numbers[i]);
            }
        }
    }

    @Override
    public SimpleList<E> fromArrayToList(Object[] arrays) {
        return new SimpleArrayList(arrays);
    }

    @Override
    public boolean add(Object value) {
        elementData = Arrays.copyOf(elementData, ++size);
        elementData[size - 1] = value;
        return true;
    }

    @Override
    public void add(int index, Object value) {
        if (index != size) {
            checkWrongIndex(index);
        }
        Object[] targetArray = new Object[++size];
        System.arraycopy(elementData, 0, targetArray, 0, index);
        targetArray[index] = value;
        System.arraycopy(elementData, index, targetArray, index + 1, size - index - 1);
        elementData = targetArray;
    }

    @Override
    public Object set(int index, Object value) {
        checkWrongIndex(index);
        Object oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public Object get(int index) {
        checkWrongIndex(index);
        return elementData[index];
    }

    @Override
    public boolean contains(Object value) {
        for (Object data : elementData) {
            if (data.equals(value)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(Object value) {
        Integer i = findFirstValueIndex(value);
        if (i != null) {
            return i;
        }
        return -1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(Object value) {
        int index = indexOf(value);
        if (index == -1) {
            return false;
        }
        removeElement(index);
        return true;
    }

    @Override
    public Object remove(int index) {
        checkWrongIndex(index);
        Object oldValue = elementData[index];
        removeElement(index);
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        this.size = 0;
    }

    private void checkWrongIndex(int index) {
        if (index < 0 || index >= this.size) {
            throw new ArrayIndexOutOfBoundsException("잘못된 인덱스 참조");
        }
    }

    private Integer findFirstValueIndex(Object value) {
        for (int i = 0; i <= size; i++) {
            if (elementData[i].equals(value)) {
                return i;
            }
        }
        return null;
    }

    private void removeElement(int index) {
        Object[] targetArray = new Object[--size];
        System.arraycopy(elementData, 0, targetArray, 0, index);
        System.arraycopy(elementData, index + 1, targetArray, index, size - index);
        elementData = targetArray;
    }

    public Object[] getElementData() {
        return elementData;
    }

    public int getSize() {
        return size;
    }
}
