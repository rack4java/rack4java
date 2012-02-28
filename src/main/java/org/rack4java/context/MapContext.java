package org.rack4java.context;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.rack4java.Context;

public class MapContext<T> implements Context<T> {
	protected Map<String, Object> map;
	
	public MapContext(Map<String, Object> map) {
		this.map = map;
	}
	
	public MapContext() {
		this(new LinkedHashMap<String, Object>());
	}

	@Override public Object getObject(String key) {
		return map.get(key);
	}

	@SuppressWarnings("unchecked") @Override public T get(String key) {
		return (T)map.get(key);
	}

	@Override public Context<T> with(String key, Object value) {
		map.put(key, value);
		return this;
	}

	public Map<String, Object> getMap() {
		return map;
	}
	
	@Override public String toString() {
		return map.toString();
	}

	@Override public Object remove(String key) {
		return map.remove(key);
	}

	@Override public Iterator<Map.Entry<String, Object>> iterator() {
		return map.entrySet().iterator();
	}
}
