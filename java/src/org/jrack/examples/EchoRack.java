package org.jrack.examples;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

import org.jrack.JRack;
import org.jrack.RackEnvironment;
import org.jrack.RackResponse;

/**
 * @author <a href="mailto:florin.patrascu@gmail.com">Florin T.PATRASCU</a>
 * @since $Revision$ (created: 11-07-24 6:46 PM)
 */
public class EchoRack implements JRack {
    @Override public RackResponse call(Map<String, Object> environment) throws Exception {
    	String body = "";
    	String contentLength = (String) environment.get(RackEnvironment.HTTP_ + "Content-Length");
    	int length = 0;
    	if (null != contentLength) {
    		length = Integer.parseInt(contentLength);
    		body = read((Reader) environment.get(RackEnvironment.RACK_INPUT), length);
    	}
    	
        return new RackResponse(200,
                "ECHO(length=" + length + ")[" + body + "]",
               	"Content-Type", "text/html"
        	);
    }

    private static String read(Reader reader, int length) {
    	try {
			StringBuilder ret = new StringBuilder();
			reader = (reader instanceof BufferedReader) ? (BufferedReader) reader : new BufferedReader(reader);
			
			int n = 0;
			for (int c = reader.read(); c != -1 && n < length; c = reader.read()) {
			    ret.append((char) c);
			    ++n;
			}
			
			return ret.toString();
		} catch (IOException e) {
			return "";
		}
	}
}
