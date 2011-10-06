package org.jrack.jservlet;

import org.jrack.JRack;
import org.jrack.examples.EchoRack;
import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.nio.SelectChannelConnector;
import org.mortbay.jetty.servlet.ServletHandler;
import org.mortbay.jetty.servlet.ServletHolder;
import org.mortbay.thread.QueuedThreadPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple support for running a Jetty servlet instance
 *
 * @author <a href="mailto:florin.patrascu@gmail.com">Florin T.PATRASCU</a>
 * @since $Revision$ (created: 11-07-30 4:10 PM)
 */
public class JettyServer {
    protected static final Logger log = LoggerFactory.getLogger(JettyServer.class);
    public static final String DEFAULT_SERVER_ADDRESS = "127.0.0.1";

    public static void start(String host, int port, JRack rack) {
        startJettyServer(host, port, new RackServlet(rack));
        log.info(String.format("Jetty rack listening on: %s:%d", host, port));
    }

    /**
     * start a simple Jetty server
     *
     * @param host host address
     * @param port the port number
     * @param servlet a rack Servlet
     */
    private static void startJettyServer(String host, int port, RackServlet servlet) {
        try {

            SelectChannelConnector connector = new SelectChannelConnector();
            connector.setHost(host);
            connector.setPort(port);
            connector.setThreadPool(new QueuedThreadPool(20));

            Server server = new Server(port);
            server.setConnectors(new Connector[]{connector});

            ServletHandler handler = new ServletHandler();
            handler.addServletWithMapping(new ServletHolder(servlet), "/*");
            server.setHandler(handler);
            server.start();
            server.join();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * run a simple Jetty servlet and test with:
     * curl http://localhost:8080/echo -d 'hello'
     *
     * @param args cmd args if any
     */
    public static void main(String[] args) {
        start(DEFAULT_SERVER_ADDRESS, 8080, new EchoRack());
    }
}