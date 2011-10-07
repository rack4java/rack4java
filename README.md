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

# Associated Projects

* Many thanks to Anthony Eden for the original [JRack][4].
* Many thanks to Florin Patrascu who forked JRack, set up [a git repo][5] and kept the idea alive. 
* An in-progress example of a [Rack4Java wrapper for the Jetty web server, using the Servlet API][6].

# Building

To build Rack4Java you will need a java compiler and Ant.

Checkout the Rack4Java code and cd to that directory.

    git clone git@github.com:efficacy/rack4java.git
    cd rack4java
    ant

The generated jar should be located here: dist/rack4java-*.jar

TODO
----

* add tests for different cases (empty data, multiple headers, etc..)
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
[6]: https://github.com/efficacy/jrack-jetty

