# ExchangeRateService

The main purpose of this service is to provide US Dollar/PLN exchange rate data based on a provided date range. The project is based on SpringBoot and it uses an external API to gather data for further processing. 

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
