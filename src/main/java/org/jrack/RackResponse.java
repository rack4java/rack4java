package org.jrack;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Map;

import org.jrack.utils.LiteralMap;
import org.jrack.utils.ReaderUtils;

public class RackResponse {
    private final int status;
    private final File file;
    private final Charset charset;
    private final Map<String, String> headers;

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

    public RackResponse(int status, File file, Map<String, String> headers) {
    	this(status, null, null, file, null, headers);
    }

    public RackResponse(int status, File file, Charset charset, Map<String, String> headers) {
    	this(status, null, null, file, null, headers);
    }

    public RackResponse(int status, String string, Map<String, String> headers) {
    	this(status, null, string, null, null, headers);
    }

    public RackResponse(int status, String string, Charset charset, Map<String, String> headers) {
    	this(status, null, string, null, charset, headers);
    }

    public RackResponse(int status, byte[] bytes, String... headers) {
    	this(status, bytes, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, File file, String... headers) {
    	this(status, file, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, String string, String... headers) {
        this(status, string, new LiteralMap<String,String>((Object[])headers));
    }

    public RackResponse(int status, String string, Charset charset, String... headers) {
        this(status, string, charset, new LiteralMap<String,String>((Object[])headers));
    }

    public byte[] getBytes() throws IOException {
    	if (null != bytes) return bytes;
    	
    	if (null != file) {
    		bytes = ReaderUtils.readAsBytes(new FileInputStream(file));
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
        		string = ReaderUtils.readAsString(new FileInputStream(file), charset);
    		} else {
        		string = ReaderUtils.readAsString(new FileInputStream(file));
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
