# JRack

A lightweight **Rack** port for the Java Servlet environment, forked from a [fork][1] of the original [JRack][2].

For more information on **Rack**, visit http://rack.rubyforge.org.

The goals of this fork are:

1. To strip the project down to the absolute minimum, with no external dependencies
2. To ensure that it works acceptably for all types of HTTP request and response
3. To be as compatible with the original [**Rack** specification][3] as possible

All of these goals contribute to the overall aim of using JRack like the original **Rack** - 
as a very thin, agnostic layer to connect wildly different types of web servers and applications. 
In particular this means removing any dependency in JRack on the Servlet API, so that it can be 
used with non-servlet servers.

# Associated Projects

* An example [JRack wrapper for the Jetty web server, using the Servlet API][4].

# Building

To build JRack you will need a java compiler and Ant.

Checkout the JRack code and cd to that directory.

    git clone git@github.com:efficacy/jrack.git
    cd jrack
    ant dist

The generated jar should be located here: dist/jrack-*.jar

This is work in progress:

TODO
----

* add tests for different cases (binary data, empty data, multiple headers, etc..)
* publish to a maven repo somewhere

DONE
----

* Strip out servlet dependencies from JRack and examples
* improve test coverage of existing examples
* support easier header syntax 
* Remove all dependencies


[1]: https://github.com/florinpatrascu/jrack
[2]: https://sourceforge.net/projects/approvaltests/files/
[3]: http://rack.rubyforge.org/doc/files/SPEC.html
[4]: https://github.com/efficacy/jrack-jetty

