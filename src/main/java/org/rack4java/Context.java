package org.rack4java;

import java.util.Map;

/**
 * 
 * A lightweight interface much easier to implement than Map
 *
 * @see java.util.Map
 * @see org.rack4java.context.MapContext
 */
public interface Context<T> extends Iterable<Map.Entry<String,T>> {
	T get(String key);
	Context<T> with(String key, T value);
	T remove(String key);
}
