package core.basesyntax;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {
    private Object[] elementData = new Object[10];
    private int capacity = elementData.length;
    private int size;

    @Override
    public void add(T value) {
        if (this.isFull()) {
            grow();
        }
        elementData[size] = value;
        size++;
    }

    @Override
    public void add(T value, int index) {
        if (index < 0 || index > size) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        } else if (isFull()) {
            grow();
        }
        shift(index);
        elementData[index] = value;
        size++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            this.add(list.get(i));
        }
    }

    @Override
    public T get(int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        return (T) elementData[index];
    }

    @Override
    public void set(T value, int index) throws ArrayListIndexOutOfBoundsException {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        elementData[index] = value;
    }

    @Override
    public T remove(int index) {
        if (index < 0 || index > size - 1) {
            throw new ArrayListIndexOutOfBoundsException("Invalid index");
        }
        final T element = (T) elementData[index];
        for (int i = index + 1; i < size; i++) {
            elementData[i - 1] = elementData[i];
        }
        elementData[size - 1] = null;
        size--;
        return element;
    }

    @Override
    public T remove(T element) {
        for (int i = 0; i < size; i++) {
            if (element == null) {
                if (elementData[i] == null) {
                    return remove(i);
                }
            }
            if (elementData[i] != null && elementData[i].equals(element)) {
                return remove(i);
            }
        }
        throw new NoSuchElementException("No such element found by provided index");

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return capacity == this.size;
    }

    private void grow() {
        capacity = capacity + (capacity / 2);
        Object [] tempArr = new Object[capacity];
        System.arraycopy(elementData, 0, tempArr, 0, elementData.length);
        elementData = tempArr;
    }

    private void shift(int index) {
        for (int i = size - 1; i > index - 1; i--) {
            elementData[i + 1] = elementData[i];
        }
    }
}
