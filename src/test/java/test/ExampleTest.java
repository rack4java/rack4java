package test;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;

import junit.framework.TestCase;

import org.jrack.JRack;
import org.jrack.RackEnvironment;
import org.jrack.RackResponse;
import org.jrack.examples.FileServer;
import org.jrack.utils.ReaderUtils;

public class ExampleTest extends TestCase {
	
	Map<String, Object> env;
	
	public void setUp() {
		env = new HashMap<String, Object>();
	}
	
    public void testStringBody() throws Exception {
    	RackResponse ret = new JRack() {
			@Override public RackResponse call(Map<String, Object> environment) throws Exception {
				return new RackResponse(200, "Wibble", "Content-Type", "text/plain");
			}
		}.call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("text/plain", ret.getHeaders().get("Content-Type"));
    	assertNull(ret.getFile());
    	assertEquals("Wibble", ret.getString());
    }
	
    public void testStringToByteConversion() throws Exception {
    	RackResponse ret = new JRack() {
			@Override public RackResponse call(Map<String, Object> environment) throws Exception {
				return new RackResponse(200, "<whatever/>", "Content-Type", "text/xml");
			}
		}.call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("text/xml", ret.getHeaders().get("Content-Type"));
    	assertNull(ret.getFile());
    	assertEquals(11, ret.getBytes().length);
    }
	
    public void testByteToStringConversion() throws Exception {
    	RackResponse ret = new JRack() {
			@Override public RackResponse call(Map<String, Object> environment) throws Exception {
				return new RackResponse(200, "picture".getBytes(), "Content-Type", "image/png");
			}
		}.call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("image/png", ret.getHeaders().get("Content-Type"));
    	assertNull(ret.getFile());
    	assertEquals("picture", ret.getString());
    }
	
    public void testFileBody() throws Exception {
    	env.put(RackEnvironment.PATH_INFO, "static.html");
    	RackResponse ret = new FileServer("src/test/files").call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("text/html", ret.getHeaders().get("Content-Type"));
    	assertNotNull(ret.getFile());
    	assertEquals("<p>Hello!</p>", ReaderUtils.readAsString(new FileInputStream(ret.getFile())));
    }
	
    public void testFileToByteConversion() throws Exception {
    	env.put(RackEnvironment.PATH_INFO, "static.html");
    	RackResponse ret = new FileServer("src/test/files").call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("text/html", ret.getHeaders().get("Content-Type"));
    	assertNotNull(ret.getFile());
    	assertEquals(13, ret.getBytes().length);
    }
	
    public void testFileToStringConversion() throws Exception {
    	env.put(RackEnvironment.PATH_INFO, "static.html");
    	RackResponse ret = new FileServer("src/test/files").call(env);
    	
    	assertEquals(200, ret.getStatus());
    	assertEquals("text/html", ret.getHeaders().get("Content-Type"));
    	assertNotNull(ret.getFile());
    	assertEquals("<p>Hello!</p>", ret.getString());
    }
}
