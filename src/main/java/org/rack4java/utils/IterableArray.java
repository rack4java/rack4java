package org.rack4java.utils;

import java.util.NoSuchElementException;

public class IterableArray<T> extends AbstractIterator<T> {
    
    private T[] array;
    private int i;

    public IterableArray(T... array) {
        this.array = array;
        i = 0;
    }

    public boolean hasNext() {
        return array != null ? i < array.length : false;
    }

    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        return array[i++];
    }
}