package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList implements SimpleList {

    private static final String[] DEFAULT_EMPTY_ELEMENT_DATA = {};
    private static final int DEFAULT_CAPACITY = 10; // 사이즈 늘릴 때 늘리기 전이 초기 값이면 해당 값으로 사이즈 설정
    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

    private String[] elementData;
    private int size;

    public SimpleArrayList() {
        this.elementData = DEFAULT_EMPTY_ELEMENT_DATA; // 아무런 파라미터 없이 생성 시 빈 배열로 할당
    }

    public SimpleArrayList(int initialCapacity) { // 초기 사이즈를 가지고 생성 시 분기처리하여 생성
        if (initialCapacity > 0) {
            this.elementData = new String[initialCapacity];
        } else if (initialCapacity == 0) {
            this.elementData = new String[]{};
        } else {
            throw new IllegalArgumentException("[ERROR] SimpleArrayList initialCapacity is Illegal");
        }
    }

    @Override
    public boolean add(String value) {
        add(value, elementData, size);
        return false;
    }

    private void add(String value, String[] elementData, int s) {
        if (size == elementData.length) {
            elementData = grow();
        }
        elementData[s] = value;
        size = s + 1;
    }

    private String[] grow() {
        return grow(size + 1);
    }

    private String[] grow(int expandCapacity) {
        elementData = Arrays.copyOf(elementData, newCapacity(expandCapacity));
        return elementData;
    }

    // 비트 연산자 >> : 비트를 이동시킨다. 12(1100) -> 6(110)로 비트 이동
    // 원래 사이즈에 하나씩 오른쪽으로 비트 이동 시킨 값(2분의 1값)을 더한다.
    // 12 -> 12 + 6 / 8 -> 8 + 4가 새로운 Capacity
    private int newCapacity(int expandCapacity) {
        int oldCapacity = elementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - expandCapacity <= 0) { // 확장한 새로운 Capacity가 확장할 Capacity보다 작거나 같으면
            if (elementData == DEFAULT_EMPTY_ELEMENT_DATA) {
                return Math.max(DEFAULT_CAPACITY, expandCapacity); // 현재 배열이 초기 값이라면 DEFAULT_CAPACITY, 확장할 사이즈 중에 큰 사이즈로 할당
            }
            if (expandCapacity < 0) { // 확장할 Capacity가 0보다 작으면 OutOfMemoryError 반환
                throw new OutOfMemoryError();
            }
            return expandCapacity;
        }
        if (newCapacity - MAX_ARRAY_SIZE <= 0) {
            return newCapacity;
        } else {
            return hugeCapacity(expandCapacity);
        }
    }

    private int hugeCapacity(int expandCapacity) {
        if (expandCapacity < 0) {
            throw new OutOfMemoryError();
        }
        if (expandCapacity > MAX_ARRAY_SIZE) {
            return Integer.MAX_VALUE;
        } else {
            return MAX_ARRAY_SIZE;
        }
    }

    @Override
    public void add(int index, String value) {
        rangeCheckForAdd(index);
        final int s;
        String[] elementData;
        if ((s = size) == (elementData = this.elementData).length)
            elementData = grow();
        System.arraycopy(elementData, index, elementData, index + 1, s - index);
        elementData[index] = value;
        size = s + 1;
    }

    private void rangeCheckForAdd(int index) {
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
    }

    @Override
    public String set(int index, String value) {
        Objects.checkIndex(index, size);
        String oldValue = elementData[index];
        elementData[index] = value;
        return oldValue;
    }

    @Override
    public String get(int index) {
        Objects.checkIndex(index, size);
        return elementData[index];
    }

    @Override
    public int indexOf(String value) {
        return indexOfRange(value, 0, size);
    }

    private int indexOfRange(String value, int start, int end) {
        String[] es = elementData;
        if (value == null) {
            for (int i = start; i < end; i++) {
                if (es[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = start; i < end; i++) {
                if (value.equals(es[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(String value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(String value) {
        final String[] es = elementData;
        final int size = this.size;
        int foundIndex = findIndex(es, size, value);
        fastRemove(elementData, foundIndex);
        return true;
    }

    private int findIndex(String[] elementData, int size, String valueToFind) {
        int foundIndex = 0;
        if (valueToFind == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    foundIndex = i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (valueToFind.equals(elementData[i])) {
                    foundIndex = i;
                }
            }
        }
        return foundIndex;
    }

    @Override
    public String remove(int index) {
        Objects.checkIndex(index, size);
        final String[] es = elementData;

        String oldValue = es[index];
        fastRemove(es, index);

        return oldValue;
    }

    private void fastRemove(String[] elementData, int index) {
        final int newSize;
        newSize = size -1;
        if (newSize > index) {
            // 지우려는 인덱스 뒤 요소들을 하나씩 앞으로 땡긴다.
            System.arraycopy(elementData, index + 1, elementData, index, newSize - index);
        }
        // 땡긴 후 마지막 인덱스를 null 처리로 지운다.
        size = newSize;
        elementData[size] = null;
    }

    @Override
    public void clear() {
        final Object[] es = elementData;
        for (int i = 0; i < size; i++) {
            es[i] = null;
        }
    }

    public String[] getElementData() {
        return elementData;
    }
}
