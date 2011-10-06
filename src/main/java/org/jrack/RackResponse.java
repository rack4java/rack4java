package org.jrack;

import java.util.Map;

import org.jrack.utils.LiteralMap;

public class RackResponse {
    private byte[] response;
    private final Map<String, String> headers;
    private int status;

    public RackResponse(int status, byte[] response, Map<String, String> headers) {
        this.status = status;
        this.response = response;
        this.headers = headers;
    }

    public RackResponse(int status, String response, String... headers) {
        this(status, response.getBytes(), new LiteralMap<String,String>((Object[])headers));
    }

    public byte[] getResponse() {
        return response;
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
