package org.rack4java;

import java.io.File;

public interface RackBody {
	enum Type { file, literal };
	Type getType();
    
    File getBodyAsFile();
    Iterable<String> getBodyAsStrings();
    Iterable<byte[]> getBodyAsBytes();
}
