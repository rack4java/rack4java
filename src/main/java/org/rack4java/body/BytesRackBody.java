package org.rack4java.body;

import java.io.File;
import java.util.Collections;

import org.rack4java.RackBody;

public class BytesRackBody implements RackBody {
	
	private byte[] body;

	public BytesRackBody(byte[] body) {
		this.body = body;
	}

	@Override public Type getType() {
		return Type.literal;
	}

	@Override public File getBodyAsFile() {
		throw new IllegalStateException("bytes body cannot be converted to file");
	}

	@Override public Iterable<String> getBodyAsStrings() {
		return Collections.singleton(new String(body)); // TODO charset?
	}

	@Override public Iterable<byte[]> getBodyAsBytes() {
		return Collections.singleton(body);
	}

}
