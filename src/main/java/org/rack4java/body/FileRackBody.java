package org.rack4java.body;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Collections;

import org.rack4java.RackBody;
import org.rack4java.utils.StreamHelper;

public class FileRackBody implements RackBody {
	private File body;
	
	public FileRackBody(File file) {
		this.body = file;
	}

	@Override public Type getType() {
		return Type.file;
	}

	@Override public File getBodyAsFile() {
		return body;
	}

	@Override public Iterable<String> getBodyAsStrings() {
		try {
			return Collections.singleton(StreamHelper.readAsString(new FileInputStream(body)));
		} catch (IOException e) {
			throw new IllegalStateException("Exception attempting to read file [" + body + "]", e);
		}
	}

	@Override public Iterable<byte[]> getBodyAsBytes() {
		try {
			return Collections.singleton(StreamHelper.readAsBytes(new FileInputStream(body)));
		} catch (IOException e) {
			throw new IllegalStateException("Exception attempting to read file [" + body + "]", e);
		}
	}

}
