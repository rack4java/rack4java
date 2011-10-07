package org.jrack.examples;

import java.util.Map;

import org.jrack.Rack;
import org.jrack.RackResponse;

public class HelloWorld implements Rack {
    public RackResponse call(Map<String, Object> input) {
        return new RackResponse(200, 
        		"Hello World", 
        		"Content-Type", "text/plain");
    }
}
