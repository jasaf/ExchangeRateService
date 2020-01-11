# ExchangeRateService
Just a very simple example of a REST service in Spring Boot with unit tests and DDD. It provides an endpoint which returns US Dollar/PLN exchange rate data based on a provided date range.

### Instructions
Install the project in local target directory.

```
 mvn install 
```
Run the jar file in order to start the service.

```
java -jar target/ExchangeRateService-0.0.*-SNAPSHOT.jar
```

Access the service in your browser or Postman.
```
http://localhost:8080/api/rate/usd/{fromDate}/{toDate}
```

Please make sure to enter the dates in the following format:

```
 yyyy-mm-dd
```
