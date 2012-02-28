package org.rack4java;

import java.io.File;
import java.io.FileNotFoundException;
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
    	with(Rack.RESPONSE_STATUS, status);
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
    	with(Rack.RESPONSE_BODY, body);
    	return this;
    }
    
    public RackResponse withBody(String body) {
    	with(Rack.RESPONSE_BODY, new StringRackBody(body));
    	return this;
    }
    
    public RackResponse withBody(byte[] bytes) {
    	with(Rack.RESPONSE_BODY, new BytesRackBody(bytes));
    	return this;
    }
    
    public RackResponse withBody(File file) throws FileNotFoundException {
    	with(Rack.RESPONSE_BODY, new FileRackBody(file));
    	return this;
    }

	public RackResponse withContentLength(long length) {
		return withHeader("Content-Length", Long.toString(length));
	}

	public RackResponse withContentType(String type) {
		return withHeader("Content-Type", type);
	}

    public int getStatus() {
        return (Integer)getObject(Rack.RESPONSE_STATUS);
    }
    
    public RackBody getBody() {
    	return (RackBody) getObject(Rack.RESPONSE_BODY);
    }
    
    public Context<String> getHeaders() {
    	Context<String> ret = new MapContext<String>();
    	for (Map.Entry<String, Object> entry : this) {
    		if (entry.getKey().startsWith(Rack.HTTP_)) {
    			ret.with(entry.getKey().substring(Rack.HTTP_.length()), entry.getValue());
    		}
    	}
    	
    	return ret;
    }
    
    public String toString() {
    	return "RackResponse[status=" + getStatus() + " body=" + getBody() + " headers=" + getHeaders() + "]";
    }
}
