package org.jrack;

import java.nio.CharBuffer;
import java.util.Map;

import org.jrack.utils.LiteralMap;

public class RackResponse {
    private CharSequence response;
    private final Map<String, String> headers;
    private int status;

    public RackResponse(int status, CharSequence response, Map<String, String> headers) {
        this.status = status;
        this.response = response;
        this.headers = headers;
    }

	public RackResponse(int status, char[] byteResponse, Map<String, String> headers) {
        this(status, CharBuffer.wrap(byteResponse), headers);
    }

    public RackResponse(int status, CharSequence response, String... headers) {
        this(status, response, new LiteralMap<String,String>((Object[])headers));
    }

	public RackResponse(int status, char[] byteResponse, String... headers) {
        this(status, CharBuffer.wrap(byteResponse), headers);
    }

    public CharSequence getResponse() {
        return response;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public int getStatus() {
        return status;
    }
}
