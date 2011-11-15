package org.rack4java.context;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.rack4java.Context;

public class MapContext<T> implements Context<T> {
	private Map<String, T> map;
	
	public MapContext(Map<String, T> map) {
		this.map = map;
	}
	
	public MapContext() {
		this(new LinkedHashMap<String, T>());
	}

	@Override public T get(String key) {
		return map.get(key);
	}

	@Override public void put(String key, T value) {
		map.put(key, value);
	}

	public Map<String, T> getMap() {
		return map;
	}
	
	@Override public String toString() {
		return map.toString();
	}

	@Override public T remove(String key) {
		return map.remove(key);
	}

	@Override public Iterator<Map.Entry<String, T>> iterator() {
		return map.entrySet().iterator();
	}
}
