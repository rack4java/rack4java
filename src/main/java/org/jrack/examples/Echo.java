package org.jrack.examples;

import java.io.InputStream;
import java.util.Map;

import org.jrack.JRack;
import org.jrack.RackEnvironment;
import org.jrack.RackResponse;
import org.jrack.utils.ReaderUtils;

public class Echo implements JRack {
    @Override public RackResponse call(Map<String, Object> environment) throws Exception {
    	RackResponse ret = new RackResponse(200, 
    			ReaderUtils.readAsString((InputStream) environment.get(RackEnvironment.RACK_INPUT)));
    	
        String type = (String) environment.get(RackEnvironment.HTTP_ + "Content-Type");
        if (null != type) {
        	ret.addHeader("Content-Type", type);
        }
        
        return ret;
    }
}
