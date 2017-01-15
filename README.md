# Object Oriented Programming Assignment
*A Multi-Threaded File Server and Logging Application. Third Year, Object Oriented Programming, Software Development.*

The Assignment Description has specified  the objective of the assignment was 
to "implement a multi-threaded file server and logging application that allows
a client application to download files using a set of options presented in a 
terminal user interface", and to apply the correct usage of OO concepts discussed 
in this module. The concepts include:

* Abstraction
* Encapsulation
* Composition
* Polymorphism

**Additional Requirements:**

* Client side should be run by the command: java -cp .:./oop.jar ie.gmit.sw.Client (the project submission required a .jar file)
* Server side should be run by the command: java -cp .:./oop.jar ie.gmit.sw.Server
* User should be greeted with the following options:
  * Connect to Server
  * Print File Listing
  * Download File
  * Quit
* The client should parse the following XML file to connect to the server:

```
<client-config username="gmit-sw2016">
	<server-host>127.0.0.1</server-host>
	<server-port>7777</server-port>
	<download-dir>./downloads/</download-dir>
</client-config>
```

* Every request from the client should be added to a blocking queue and logged in a text file ahering to the following format: [INFO | ERROR | WARNING] command requested by client ip address at date time
* Use java.oi.File to get directory listing and use java.io.FileWriter to log requests to the file.

**Client Side:**

As required, the Client is presented with the above options. The handling of user input is handled on the Client side to prevent errorneous input to the server.

**Server Side:**

The Server validates the Arguments entered upon execution. The Server will either printan error to the console and terminate or start listening for Clients, i.e. start the server. All messages, sent and received, are printed to the console. To terminate the server, the command "EXIT" must be entered. Then the Server will stop listening for Clients, wait for all the existing Client Threads to terminate, and "poisin" the BlockingQueue.


**References:**

[https://https://stackoverflow.com/questions/15482423/how-to-list-the-files-in-current-directory]

[https://www.youtube.com/watch?v=zFufOEsvHqU]

[http://stackoverflow.com/questions/12780446/check-if-a-path-represents-a-file-or-a-folder]

[https://docs.oracle.com/javase/tutorial/essential/environment/cmdLineArgs.html]

[http://crunchify.com/what-is-threadsafe-blockingqueue-in-java-and-when-you-should-use-it-implementation-attached/]

[https://examples.javacodegeeks.com/core-java/util/concurrent/java-blockingqueue-example/]

[http://www.journaldev.com/1034/java-blockingqueue-example]

-----

__*Tara O'Kelly - G00322214@gmit.ie*__ 