# Rack 4 Java

An ultra-lightweight [**Rack**][1] port for Java

The goals of this project are:

1. To produce a tiny **Rack** layer for Java, with no external dependencies
2. To ensure that it works acceptably for all types of HTTP request and response
3. To be as compatible with the spirit of the original [**Rack** specification][2] as possible
4. To be as performant as possible within the constraints of the essential API

All of these goals contribute to the overall aim of a very thin, agnostic layer to connect wildly different types 
of web servers, applications and third-party APIs. In particular this means no dependency on the [Servlet API][3], 
to encourage and unify non-servlet servers. A simpler web API is also vital to bring the joy of Test-Driven development 
to java web development. 

That's not to say that a servlet server can't make use of Rack4Java, it just needs a Servlet which passes 
through requsts and responses from the complex and largely untestable Servlet API to the much simpler Rack API. 
I have added [An example of this][5] to GitHub.

# How It Works

A Rack4Java application can be very simple. For example:

    package org.rack4java.examples;

    import java.util.Map;
    import org.rack4java.Rack;
    import org.rack4java.RackResponse;

    public class HelloWorld implements Rack {
        public RackResponse call(Map<String, Object> input) {
            return new RackResponse(200, 
        		"Hello World", 
        		"Content-Type", "text/plain");
        }
    }
  
When plugged in to a Rack4Java friendly server, this will respond with the usual greeting to any request

# Associated Projects

* Many thanks to Anthony Eden for the original [JRack][4].
* Many thanks to Florin Patrascu who forked JRack, set up [a git repo][5] and kept the idea alive.
* Some [tools for routing requests among Rack4Java applications][7] 
* An in-progress example of a [Rack4Java wrapper for the Servlet API with an example using the Jetty web server][6].

# Building

To build Rack4Java you will need a java compiler and Ant.

Checkout the Rack4Java code and cd to that directory.

    git clone git@github.com:rack4java/rack4java.git
    cd rack4java
    ant

The generated jar should be located here: dist/rack4java-*.jar

TODO
----

* add tests for different cases (empty data, multiple headers, etc..)
* add javadoc and generate doc pages
* publish to a maven repo somewhere

DONE
----

* Strip out servlet dependencies from JRack and examples
* improve test coverage of existing examples
* support easier header syntax 
* Remove all dependencies
* add tests for binary data


[1]: http://rack.rubyforge.org
[2]: http://rack.rubyforge.org/doc/files/SPEC.html
[3]: http://www.oracle.com/technetwork/java/javaee/servlet/index.html
[4]: https://sourceforge.net/projects/approvaltests/files/
[5]: https://github.com/florinpatrascu/jrack
[6]: https://github.com/rack4java/rack4java-servlet
[7]: https://github.com/rack4java/rack4java-router

