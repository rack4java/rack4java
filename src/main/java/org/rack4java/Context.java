package org.rack4java;

import java.util.Map;

/**
 * 
 * A lightweight interface much easier to implement than Map
 *
 * @see java.util.Map
 * @see org.rack4java.context.MapContext
 */
public interface Context<T> extends Iterable<Map.Entry<String,Object>> {
	Object getObject(String key);
	T get(String key);
	Context<T> with(String key, Object value);
	Object remove(String key);
}
