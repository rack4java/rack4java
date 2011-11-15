package org.rack4java.context;

import java.util.Map;

class ContextEntry<T> implements Map.Entry<String, T> {
	private String key;
	private T value;

	public ContextEntry(String key, T value) {
		this.key = key;
		this.value = value;
	}

	@Override public String getKey() {
		return key;
	}

	@Override public T getValue() {
		return value;
	}

	@Override public T setValue(T value) {
		T ret = this.value;
		this.value = value;
		return ret;
	}
}
