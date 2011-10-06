package org.jrack.examples;

import java.util.Map;

import org.jrack.JRack;
import org.jrack.RackResponse;

public class HelloWorld implements JRack {
    public RackResponse call(Map<String, Object> input) {
        return new RackResponse(200, 
        		"Hello World", 
        		"Content-Type", "text/plain");
    }
}
