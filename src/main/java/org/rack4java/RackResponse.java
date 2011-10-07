package org.rack4java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.rack4java.utils.LiteralMap;
import org.rack4java.utils.StreamHelper;

public class RackResponse {
    private final int status;
    private final File file;
    private final Charset charset;
    private final Map<String, String> headers;

    // preserved but autoconverted if required
    private byte[] bytes;
    private String string;

    public RackResponse(int status, byte[] response, String string, File file, Charset charset, Map<String, String> headers) {
        this.status = status;
        this.bytes = response;
        this.string = string;
        this.file = file;
        this.charset = charset;
        this.headers = headers;
    }

    public RackResponse(int status, byte[] bytes, Map<String, String> headers) {
    	this(status, bytes, null, null, null, headers);
    }

    public RackResponse(int status, byte[] bytes, Charset charset, Map<String, String> headers) {
    	this(status, bytes, null, null, charset, headers);
    }

    public RackResponse(int status, File file, Map<String, String> headers) {
    	this(status, null, null, file, null, headers);
    }

    public RackResponse(int status, File file, Charset charset, Map<String, String> headers) {
    	this(status, null, null, file, null, headers);
    }

    public RackResponse(int status, String string, Map<String, String> headers) {
    	this(status, null, string, null, null, headers);
    }

    public RackResponse(int status, byte[] bytes, String... headers) {
    	this(status, bytes, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, byte[] bytes, Charset charset, String... headers) {
    	this(status, bytes, charset, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, File file, String... headers) {
    	this(status, file, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, File file, Charset charset, String... headers) {
    	this(status, file, charset, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, String string, String... headers) {
        this(status, string, new LiteralMap<String,String>((Object[])headers));
    }

    public byte[] getBytes() throws IOException {
    	if (null != bytes) return bytes;
    	
    	if (null != file) {
    		bytes = StreamHelper.readAsBytes(new FileInputStream(file));
    	} else if (null != charset) {
    		bytes = string.getBytes(charset);
    	} else {
    		bytes = string.getBytes();
    	}
        return bytes;
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
    	} else {
    		string = new String(bytes);
    	}
    	
    	return string;
    }
    
    public File getFile() {
    	return file;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatus() {
        return status;
    }
    
    public void addHeader(String key, String value) {
    	headers.put(key, value);
    }
}
