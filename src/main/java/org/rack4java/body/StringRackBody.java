package org.rack4java.body;

import java.io.File;
import java.util.Collections;

import org.rack4java.RackBody;

public class StringRackBody implements RackBody {
	
	private String body;

	public StringRackBody(String body) {
		this.body = body;
	}

	@Override public Type getType() {
		return Type.literal;
	}

	@Override public File getBodyAsFile() {
		throw new IllegalStateException("String body cannot be converted to file");
	}

	@Override public Iterable<String> getBodyAsStrings() {
		return Collections.singleton(body);
	}

	@Override public Iterable<byte[]> getBodyAsBytes() {
		return Collections.singleton(body.getBytes()); // TODO charset?
	}

}
