package org.rack4java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.rack4java.context.LiteralContext;
import org.rack4java.context.MapContext;
import org.rack4java.utils.StreamHelper;

public class RackResponse {
    private final int status;
    private Context<String> headers;

    // preserved but auto-converted if required
    private Charset charset;
    private byte[] bytes;
    private String string;
    private File file;
    
    public RackResponse(int status) {
    	this.status = status;
    	this.headers = new MapContext<String>();
    }
    
    public RackResponse withHeader(String key, String value) {
    	headers.put(key, value);
    	return this;
    }
    
    public RackResponse withHeaders(Context<String> headers) {
    	for (Map.Entry<String, String> entry : headers) {
        	headers.put(entry.getKey(), entry.getValue());
    	}
    	return this;
    }
    
    public RackResponse withHeaders(String... headers) {
    	return withHeaders(new LiteralContext<String>((Object[])headers));
    }
    
    public RackResponse withBody(String string) {
    	this.string = string;
    	return this;
    }
    
    public RackResponse withBody(String string, Charset charset) {
    	this.string = string;
    	this.charset = charset;
    	return this;
    }
    
    public RackResponse withBody(File file) {
    	this.file = file;
    	return this;
    }
    
    public RackResponse withBody(File file, Charset charset) {
    	this.file = file;
    	this.charset = charset;
    	return this;
    }
    
    public RackResponse withBody(byte[] bytes) {
    	this.bytes = bytes;
    	return this;
    }
    
    public RackResponse withBody(byte[] bytes, Charset charset) {
    	this.bytes = bytes;
    	this.charset = charset;
    	return this;
    }

    public byte[] getBytes() throws IOException {
    	if (null != bytes) return bytes;
    	
    	if (null != file) {
    		bytes = StreamHelper.readAsBytes(new FileInputStream(file));
    	} else if (null != charset) {
    		bytes = string.getBytes(charset);
    	} else if (null != string) {
    		bytes = string.getBytes();
    	}
        return bytes != null ? bytes : new byte[0];
    }

    public String getString() throws IOException {
    	if (null != string) return string;
    	
    	if (null != file) {
    		if (null != charset) {
        		string = StreamHelper.readAsString(new FileInputStream(file), charset);
    		} else {
        		string = StreamHelper.readAsString(new FileInputStream(file));
    		}
    	} else if (null != charset) {
    		string = new String(bytes, charset);
    	} else if (null != bytes) {
    		string = new String(bytes);
    	}
    	
    	return string != null ? string : "";
    }
    
    public File getFile() {
    	return file;
    }

    public Context<String> getHeaders() {
        return headers;
    }

    public int getStatus() {
        return status;
    }
    
    public void addHeader(String key, String value) {
    	headers.put(key, value);
    }
}
