package org.rack4java;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

import org.rack4java.context.MapContext;
import org.rack4java.utils.StreamHelper;

public class RackResponse {
    private final int status;
    private final Context<String> headers;

    // preserved if supplied, but auto-converted where required
    private InputStream stream;
    private long length = Long.MAX_VALUE;
    private File file;
    
    public RackResponse(int status) {
    	this.status = status;
    	this.headers = new MapContext<String>();
    }
    
    public RackResponse withHeader(String key, String value) {
    	headers.with(key, value);
    	return this;
    }
    
    public RackResponse withHeaders(Context<String> headers) {
    	for (Map.Entry<String, String> entry : headers) {
        	headers.with(entry.getKey(), entry.getValue());
    	}
    	return this;
    }
    
    
    public RackResponse withBody(InputStream stream, long length) {
    	this.stream = stream;
    	withContentLength(length);
    	return this;
    }

	public RackResponse withContentLength(long length) {
		this.length = length;
		return withHeader("Content-Length", Long.toString(length));
	}

	public RackResponse withContentType(String type) {
		return withHeader("Content-Type", type);
	}
    
    public RackResponse withBody(byte[] bytes) {
    	return withBody(new ByteArrayInputStream(bytes), bytes.length);
    }
    
    public RackResponse withBody(String string, Charset charset) {
    	return withBody(string.getBytes(charset));
    }
    
    public RackResponse withBody(String string) {
    	return withBody(string.getBytes());
    }
    
    public RackResponse withBody(File file) throws FileNotFoundException {
    	this.file = file;
    	withBody(new FileInputStream(file), file.length());
    	return this;
    }

    public int getStatus() {
        return status;
    }

    public Context<String> getHeaders() {
        return headers;
    }
    
    public long getBodyLength() {
    	return length;
    }
    
    public File getFileBody() {
    	return file;
    }

    public InputStream getStreamBody() {
    	return stream;
    }
    
    public byte[] getBodyAsBytes() throws IOException {
    	return StreamHelper.readAsBytes(stream, (int)length);
    }
}
