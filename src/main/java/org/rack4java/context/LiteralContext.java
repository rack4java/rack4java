package org.rack4java.context;

public class LiteralContext<V> extends MapContext<V> {
	@SuppressWarnings("unchecked")
	public LiteralContext(Object... objects) {
		if (objects.length % 2 != 0) throw new IllegalArgumentException("LiteralContext requires an even number of args");
		for (int i = 0; i < objects.length; i += 2) {
			put((String)objects[i], (V)objects[i+1]);
		}
	}

}
