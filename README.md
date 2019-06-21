
A Simple Spring-Boot app, for adding and delivering computational tasks to the clients.

Using Apache thrift to communicate between clients and server.


### Server

To build the jar file, uncomment lines in Application.java as follow

``` java
    public static void main(String[] args) throws TTransportException {
        ApplicationContext context = SpringApplication.run(Application.class);
        server(context);
//        client();
    }
```

Then run

```
./gradlew clean build
java -jar build/libs/task-delivery-system-1.0-SNAPSHOT.jar
```

### Client

To build the jar file, comment lines in Application.java as follow

``` java
    public static void main(String[] args) throws TTransportException {
//        ApplicationContext context = SpringApplication.run(Application.class);
//        server(context);
        client();  
    }
```

Then run

```
./gradlew clean build
java -jar build/libs/task-delivery-system-1.0-SNAPSHOT.jar
```

## Usage

The web app runs at `localhost:8080`.

1. Upload the input file, 
2. Choose the client by click it.
3. Submit the task.




