# Rack 4 Java

In the world of dynamic language web development it is common for the interface between server, 
web framework, and application components to be simple and standard. Most have settled on a particular style:

* Ruby [Rack][1]
* Python [WSGI][8]
* perl [PSGI][9]

and more, all have a lot in common.

An request is represented as an associative array ("Map", "Hash", "Dictionary" etc.) populated with 
a standard set of named items, but able to contain others. A response is represented as a triplet of 
[response code, headers, body]

This is extremely simple compared with the Java [Servlet API][3] usually considered as 
the standard way to interface web applications with web servers. This simplicty has many advantages:

* it's much easier to implement, so lots of people can support it in their servers
* it's much easier to work with, so both frameworks and raw applications can be simpler
* it's much easier to mock/stub, so framework and application code is easier to test
* it's much easier to use the "decorator" pattern, so applications can be much more modular

The last point is particularly important. Ruby Rack has a vibrant ecosystem of "middleware" for all sorts of 
common tasks and processes, making application developmentmuch easier by simply combining and re-arranging 
pre-built blocks with no change to application code at all.

_I want these benefits for my Java development!_

# Rack 4 Java is An ultra-lightweight Rack port for Java

The key goals of this project are:

1. To produce a tiny Rack layer for Java, with no external dependencies
2. To ensure that it works acceptably for all types of HTTP request and response
3. To be as compatible as possible with the spirit of the original [Rack specification][2]
4. To be as performant as possible within the constraints of the essential API

All of these goals contribute to the overall aim of a very thin, agnostic layer to connect wildly different types 
of web servers, applications and third-party APIs. In particular this means no dependency on the [Servlet API][3], 
to encourage and unify non-servlet servers. A simpler web API is also vital to bring the joy of Test-Driven development 
to all levels of java web software. 

That's not to say that a servlet server can't make use of Rack4Java, it just needs a Servlet which passes 
through requsts and responses from the complex and largely untestable Servlet API to the much simpler Rack API. 
I have added [Some code to do this][6] to GitHub.

# How It Works

A Rack4Java application can be very simple. For example:

    import org.rack4java.Context;
    import org.rack4java.Rack;
    import org.rack4java.RackResponse;

    public class HelloWorld implements Rack {
        public Context<String> call(Context<String> input) {
            return new RackResponse(200)
        	    .withHeader("Content-Type", "text/plain")
        	    .withBody("Hello World");
        }
    }
  
When plugged in to a Rack4Java friendly server, this will respond with the usual greeting to any request.

Notes:
* Java is strongly typed and does not (yet?) have a sensible or concise literal syntax for Arrays and Maps, so the code looks a bit different to the Ruby version
* [**Context**](/rack4java/rack4java/blob/master/src/main/java/org/rack4java/Context.java) is just a very simple interface, roughly equivalent to Map&lt;String,?&gt;
* [**RackResponse**](/rack4java/rack4java/blob/master/src/main/java/org/rack4java/RackResponse.java) is just a [**MapContext**](/rack4java/rack4java/blob/master/src/main/java/org/rack4java/context/MapContext.java) with a bunch of helper methods. You don't have to use it and can return any **Context** which has the appropriate entries.

# Associated Projects

* Many thanks to Anthony Eden for the original [JRack][4].
* Many thanks to Florin Patrascu who forked JRack, set up [a git repo][5] and kept the idea alive.
* Some [examples of simple Rack4Java applications][10] 
* Some [tools for routing requests to Rack4Java applications][7] 
* Some [examples of Rack4Java "middleware"][11] 
* A work-in-progress example of a [Rack4Java wrapper for the Servlet API with an example using the Jetty web server][6].

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
[8]: http://www.python.org/dev/peps/pep-3333/ 
[9]: http://search.cpan.org/~miyagawa/PSGI-1.10/PSGI.pod
[10]: https://github.com/rack4java/rack4java-examples
[11]: https://github.com/rack4java/rack4java-middleware
