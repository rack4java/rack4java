package org.rack4java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;

import org.rack4java.context.LiteralContext;
import org.rack4java.utils.StreamHelper;

public class RackResponse {
    private final int status;
    private final Context<String> headers;

    // preserved but auto-converted if required
    private final Charset charset;
    private byte[] bytes;
    private String string;
    private final File file;

    public RackResponse(int status, byte[] response, String string, File file, Charset charset, Context<String> headers) {
        this.status = status;
        this.bytes = response;
        this.string = string;
        this.file = file;
        this.charset = charset;
        this.headers = headers;
    }

    public RackResponse(int status, byte[] bytes, Context<String> headers) {
    	this(status, bytes, null, null, null, headers);
    }

    public RackResponse(int status, byte[] bytes, Charset charset, Context<String> headers) {
    	this(status, bytes, null, null, charset, headers);
    }

    public RackResponse(int status, File file, Context<String> headers) {
    	this(status, null, null, file, null, headers);
    }

    public RackResponse(int status, File file, Charset charset, Context<String> headers) {
    	this(status, null, null, file, null, headers);
    }

    public RackResponse(int status, String string, Context<String> headers) {
    	this(status, null, string, null, null, headers);
    }

    public RackResponse(int status, byte[] bytes, String... headers) {
    	this(status, bytes, new LiteralContext<String>((Object[])headers));
    }

    public RackResponse(int status, byte[] bytes, Charset charset, String... headers) {
    	this(status, bytes, charset, new LiteralContext<String>((Object[])headers));
    }

    public RackResponse(int status, File file, String... headers) {
    	this(status, file, new LiteralContext<String>((Object[])headers));
    }

    public RackResponse(int status, File file, Charset charset, String... headers) {
    	this(status, file, charset, new LiteralContext<String>((Object[])headers));
    }

    public RackResponse(int status, String string, String... headers) {
        this(status, string, new LiteralContext<String>((Object[])headers));
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
