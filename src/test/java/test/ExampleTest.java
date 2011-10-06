package test;

import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.jrack.RackResponse;
import org.jrack.examples.HelloWorldRack;

public class ExampleTest extends TestCase {
	
	Map<String, Object> env;
	
	public void setUp() {
		env = new HashMap<String, Object>();
	}
	
    public void testHelloWorld() throws Exception {
    	RackResponse ret = new HelloWorldRack().call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("text/html;charset=utf-8", ret.getHeaders().get("Content-Type"));
    	assertEquals("Hello World", ret.getResponse().toString());
    }
}
