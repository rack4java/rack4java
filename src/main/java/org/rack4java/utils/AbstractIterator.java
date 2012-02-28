package org.rack4java.utils;

import java.util.Iterator;

public abstract class AbstractIterator<T> implements Iterator<T>, Iterable<T> {
    
    public abstract boolean hasNext();
    public abstract T next();

    public void remove() {
        throw new UnsupportedOperationException("can't remove from " + this.getClass().getName());
    }
    
    public Iterator<T> iterator() {
        return this;
    }
}
