# ironworker-java-archetype
Maven archetype for Java-based IronWorker workers.

[IronWorker](http://www.iron.io/worker) is a cloud-based asynchronous task processing service. The IronWorker website provides [step-by-step instructions](http://dev.iron.io/worker/languages/java/) for creating and deploying a Java-based "Hello World" worker. 

This project provides much the same in terms of sample code for developing a Java-based worker, with the following improvements:
* This sample worker is packaged as a [Maven archetype](https://maven.apache.org/guides/introduction/introduction-to-archetypes.html). Simply install/deploy the archetype and then create a new project using the `mvn archetype:generate` command.
* When creating your project with the archetype, provide your IronWorker token and project id and your `iron.json` file will be created automagically.
* Once you have generated your worker project, run a Maven build (`mvn package` or whatnot) and an **executable jar** will be placed in the `/target/worker` directory **along with your iron.json and .worker files**. Just go to this directory and you can upload your worker with the `iron_worker` command line utility (`iron_worker upload <worker-name>`). Done!
* Your Maven dependencies will be bundled with your worker inside the executable jar file, so there is no need to upload separate jar files.
