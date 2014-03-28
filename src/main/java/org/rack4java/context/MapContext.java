package org.rack4java.context;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.rack4java.Context;

public class MapContext<T> implements Context<T> {
	protected Map<String, T> map;
	
	public MapContext(Map<String, T> map) {
		this.map = map;
	}
	
	public MapContext() {
		this(new LinkedHashMap<String, T>());
	}

	@Override public Object getObject(String key) {
		return map.get(key);
	}

	@Override public T get(String key) {
		return (T)map.get(key);
	}

	@SuppressWarnings("unchecked")
	@Override public Context<T> with(String key, Object value) {
		map.put(key, (T)value);
		return this;
	}
	
	public MapContext<T> with(Context<T> context) {
		for (Map.Entry<String, T> entry : context) {
			with(entry.getKey(), entry.getValue());
		}
		return this;
	}

	public Map<String, T> getMap() {
		return map;
	}
	
	@Override public String toString() {
		return map.toString();
	}

	@Override public Object remove(String key) {
		return map.remove(key);
	}

	@Override public Iterator<Map.Entry<String, T>> iterator() {
		return map.entrySet().iterator();
	}
}
