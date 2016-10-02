# myRetail RESTful service
This is the repository for the myRetail products API.  

## Prerequisites
* JDK 1.8
* Maven 3.3
* Mongo installed to localhost:27017 with a db named "myretail" & collection named "price"

## To build, test and run
First, navigate to /code.
```
cd code
```
Next, build, run tests and package with Maven
```
mvn clean package
```
Finally, run the output jar with java
```
java -jar target\gs-consuming-rest-0.1.0.jar
```

## Interacting with the application
Performing a GET request on the url http://localhost:8080/product/{id} will return a JSON object containing the
name and pricing info for the requested product.  If no pricing info is available, the JSON object's current_price
will be null.

Example:
```
GET http://localhost:8080/product/15117729

response:
{
    "id": 15117729,
    "name": "Apple&reg; iPad Air 2 16GB Wi-Fi - Gold",
    "current_price": {
        "value": 299.98,
        "currency_code": "USD"
    }
}
```

To set the price of an item, perform a PUT request to the same URL with the request body being a JSON object
containing the id & updated pricing information.

Example:
```
PUT http://localhost:8080/product/15117729
content-type: application/json;charset=UTF-8
body:
{
    "id": 15117729,
    "current_price": {
        "value": 5.00,
        "currency_code": "USD"
    }
}

response:
{
    "id": 15117729,
    "name": "Apple&reg; iPad Air 2 16GB Wi-Fi - Gold",
    "current_price": {
        "value": 5.00,
        "currency_code": "USD"
    }
}
```
