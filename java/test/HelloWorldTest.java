import java.util.HashMap;

import junit.framework.TestCase;

import org.jrack.RackResponse;
import org.jrack.examples.HelloWorldRack;

public class HelloWorldTest extends TestCase {
    public void testHelloWorld() throws Exception {
    	RackResponse ret = new HelloWorldRack().call(new HashMap<String, Object>());
    	assertEquals(200, ret.getStatus());
    	assertEquals("Hello World", ret.getResponse().toString());
    }
}
