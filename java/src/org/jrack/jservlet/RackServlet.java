package org.jrack.jservlet;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jrack.JRack;
import org.jrack.RackEnvironment;
import org.jrack.RackResponse;
import org.jrack.utils.ClassUtilities;
import org.jrack.utils.LiteralMap;

@SuppressWarnings("serial") 
public class RackServlet extends HttpServlet {
	private static final Map<String, Object> commonEnvironment = new LiteralMap<String, Object>(
		    RackEnvironment.RACK_VERSION, Arrays.asList(1, 1),
		    RackEnvironment.RACK_ERRORS, new OutputStreamWriter(System.err),
		    RackEnvironment.RACK_MULTITHREAD, true,
		    RackEnvironment.RACK_MULTIPROCESS, true,
		    RackEnvironment.RACK_RUN_ONCE, false
		);
	
    private JRack rack;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        String rackClass = config.getInitParameter("rackClass");

        try {
            rack = ((JRack) ClassUtilities.loadClass(rackClass).newInstance());
        } catch (Exception e) {
            throw new ServletException("Cannot load: " + rackClass);
        }
    }

    /**
     * used by servlet container
     */
    public RackServlet() {
    }

    public RackServlet(JRack rack) {
        this.rack = rack;
    }

    private void processCall(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            RackResponse response = rack.call(getEnvironment(req));
            writeResponse(resp, response);
        } catch (Exception e) {
            RackServlet.throwAsError(e);
        }
    }

    private void writeResponse(HttpServletResponse resp, RackResponse response) throws IOException {
        resp.setStatus(response.getStatus());
        for (String key : response.getHeaders().keySet()) {
            resp.setHeader(key, response.getHeaders().get(key));
        }
        resp.getWriter().print(response.getResponse());
    }

    private Map<String, Object> getEnvironment(HttpServletRequest req) {
        Map<String, Object> environment = new HashMap<String, Object>();
        environment.putAll(commonEnvironment);
        
        environment.put(RackEnvironment.REQUEST_METHOD, req.getMethod());
        environment.put(RackEnvironment.PATH_INFO, req.getPathInfo());
        environment.put(RackEnvironment.QUERY_STRING, req.getQueryString());
        environment.put(RackEnvironment.SERVER_NAME, req.getServerName());
        environment.put(RackEnvironment.SERVER_PORT, req.getServerPort());
        environment.put(RackEnvironment.SCRIPT_NAME, req.getServletPath());

        environment.put(RackEnvironment.HTTP_ACCEPT_ENCODING, req.getHeader("Accept-Encoding"));
        environment.put(RackEnvironment.HTTP_USER_AGENT, req.getHeader("User-Agent"));
        environment.put(RackEnvironment.HTTP_HOST, req.getHeader("Host"));
        environment.put(RackEnvironment.HTTP_CONNECTION, req.getHeader("Connection"));
        environment.put(RackEnvironment.HTTP_ACCEPT, req.getHeader("Accept"));
        environment.put(RackEnvironment.HTTP_ACCEPT_CHARSET, req.getHeader("Accept-Charset"));
        
        environment.put(RackEnvironment.RACK_URL_SCHEME, req.getScheme());
        
        return environment;
    }


    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        processCall((HttpServletRequest) req, (HttpServletResponse) res);
    }

    public static Error throwAsError(Throwable t) throws Error {
        if (t instanceof RuntimeException) {
            throw (RuntimeException) t;
        } else if (t instanceof Error) {
            throw (Error) t;
        } else {
            throw new Error(t);
        }
    }
}
