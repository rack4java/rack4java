package org.rack4java;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.rack4java.body.BytesRackBody;
import org.rack4java.body.FileRackBody;
import org.rack4java.body.StringRackBody;
import org.rack4java.context.MapContext;

public class RackResponse extends MapContext<String> {
	
	public RackResponse(Context<String> env) {
		for (Map.Entry<String, Object> entry : env) {
			with(entry.getKey(), entry.getValue());
		}
	}
   
    public RackResponse(int status) {
    	with(Rack.MESSAGE_STATUS, status);
    }
    
    public RackResponse withHeader(String key, String value) {
    	with(Rack.HTTP_ + key, value);
    	return this;
    }
    
    public RackResponse withHeaders(Context<String> headers) {
    	for (Map.Entry<String, Object> entry : headers) {
        	withHeader(entry.getKey(), (String)entry.getValue());
    	}
    	return this;
    }
    
    public RackResponse withBody(RackBody body) {
    	with(Rack.MESSAGE_BODY, body);
    	return this;
    }
    
    public RackResponse withBody(String body) {
    	with(Rack.MESSAGE_BODY, new StringRackBody(body));
    	return this;
    }
    
    public RackResponse withBody(byte[] bytes) {
    	with(Rack.MESSAGE_BODY, new BytesRackBody(bytes));
    	return this;
    }
    
    public RackResponse withBody(File file) throws FileNotFoundException {
    	with(Rack.MESSAGE_BODY, new FileRackBody(file));
    	return this;
    }

	public RackResponse withContentLength(long length) {
		return withHeader("Content-Length", Long.toString(length));
	}

	public RackResponse withContentType(String type) {
		return withHeader("Content-Type", type);
	}

    public int getStatus() {
        return (Integer)getObject(Rack.MESSAGE_STATUS);
    }
    
    public RackBody getBody() {
    	return getBody(this);
    }
    
    public Context<String> getHeaders() {
    	return getHeaders(this);
    }
    
    public static Context<String> getHeaders(Context<String> env) {
    	Context<String> ret = new MapContext<String>();
    	for (Map.Entry<String, Object> entry : env) {
    		if (entry.getKey().startsWith(Rack.HTTP_)) {
    			ret.with(entry.getKey().substring(Rack.HTTP_.length()), entry.getValue());
    		}
    	}
    	
    	return ret;
    }


    public static RackBody getBody(Context<String> response) {
		return (RackBody) response.getObject(Rack.MESSAGE_BODY);
	}

    public static String getBodyAsString(Context<String> response, Charset charset) {
		StringBuilder ret = new StringBuilder();
		for (byte[] chunk : getBody(response).getBodyAsBytes()) {
			ret.append(new String(chunk, charset));
		}
		return ret.toString();
	}
	
    public static String getBodyAsString(Context<String> response) {
		StringBuilder ret = new StringBuilder();
		for (byte[] chunk : getBody(response).getBodyAsBytes()) {
			ret.append(new String(chunk));
		}
		return ret.toString();
	}
	
    public static byte[] getBodyAsBytes(Context<String> response) {
		RackBody body = getBody(response);
		if (null == body) return null;
		List<byte[]> chunks = new ArrayList<byte[]>();
		int length = 0;
		for (byte[] chunk : body.getBodyAsBytes()) {
			length += chunk.length;
			chunks.add(chunk);
		}
		byte[] ret = new byte[length];
		int offset = 0;
		for (byte[] chunk : chunks) {
			int step = 0;
			for (byte b : chunk) {
				ret[offset + step++] = b;
			}
			offset += step;
		}
		return ret;
	}
    
    public String toString() {
    	return "RackResponse[status=" + getStatus() + " body=" + getBody() + " headers=" + getHeaders() + "]";
    }
}
