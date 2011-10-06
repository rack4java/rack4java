package org.jrack;

public interface RackEnvironment {
    public static final String REQUEST_METHOD = "REQUEST_METHOD";
    public static final String SCRIPT_NAME = "SCRIPT_NAME";
    public static final String PATH_INFO = "PATH_INFO";
    public static final String QUERY_STRING = "QUERY_STRING";
    public static final String SERVER_NAME = "SERVER_NAME";
    public static final String SERVER_PORT = "SERVER_PORT";
    
    public static final String HTTP_ = "HTTP_";
    public static final String HTTP_USER_AGENT = HTTP_ + "User-Agent";
    public static final String HTTP_HOST = HTTP_ + "Host";
    public static final String HTTP_CONNECTION = HTTP_ + "Connection";
    public static final String HTTP_ACCEPT = HTTP_ + "Accept";
    public static final String HTTP_ACCEPT_CHARSET = HTTP_ + "ACCEPT_CHARSET";
    public static final String HTTP_ACCEPT_ENCODING = HTTP_ + "ACCEPT_ENCODING";
	public static final Object HTTP_CONTENT_LENGTH = HTTP_ + "Content-Length";
	public static final Object HTTP_CONTENT_TYPE = HTTP_ + "Content-Type";

    public static final String RACK_VERSION = "rack.version";
    public static final String RACK_URL_SCHEME = "url_scheme";
    public static final String RACK_INPUT = "rack.input";
    public static final String RACK_ERRORS = "rack.errors";
    public static final String RACK_MULTITHREAD = "rack.multithread";
    public static final String RACK_MULTIPROCESS = "rack.multiprocess";
    public static final String RACK_RUN_ONCE = "rack.run_once";
}
