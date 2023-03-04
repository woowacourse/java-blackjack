package techcourse.jcf.mission;

import java.util.Arrays;
import java.util.Objects;

public class SimpleArrayList implements SimpleList {
	private final static int DEFAULT_CAPACITY = 10;
	private String[] elementData;
	private int elements;
	private int size;

	public SimpleArrayList() {
		elementData = new String[DEFAULT_CAPACITY];
		size = DEFAULT_CAPACITY;
	}

	@Override
	public boolean add(String value) {
		if (!rangeCheckForAdd(elements)) {
			grow();
		}
		elementData[elements++] = value;
		return true;
	}

	@Override
	public void add(int index, String value) {
		Objects.checkIndex(index, size);
		Objects.checkIndex(index, size);
		if (!rangeCheckForAdd(elements)) {
			grow();
		}
		System.arraycopy(elementData, index, elementData, index + 1, elements - index);
	}

	private boolean rangeCheckForAdd(int index) {
		return index < size && index >= 0;
	}

	private String[] grow() {
		return elementData = Arrays.copyOf(elementData, size += DEFAULT_CAPACITY);
	}

	@Override
	public String set(int index, String value) {
		Objects.checkIndex(index, size);
		elementData[index] = value;
		return value;
	}

	@Override
	public String get(int index) {
		Objects.checkIndex(index, size);
		return elementData[index];
	}

	@Override
	public boolean contains(String value) {
		return indexOf(value) != -1;
	}

	@Override
	public int indexOf(String value) {
		if (value == null) {
			for (int i = 0; i < size; i++) {
				if (elementData[i] == null) {
					return i;
				}
			}
			return -1;
		}
		for (int i = 0; i < size; i++) {
			if (value.equals(elementData[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int size() {
		return elements;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean remove(String value) {
		int index = indexOf(value);
		// 0 1 2 3 4 5
		// remove(2)
		// index = 2
		// 0 1 3 4 5 _
		// 1개면?
		System.arraycopy(elementData, index + 1, elementData, index, elements - index - 1);
		elements--;
		return false;
	}

	@Override
	public String remove(int index) {
		return null;
	}

	@Override
	public void clear() {

	}
}
