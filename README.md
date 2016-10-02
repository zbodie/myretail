# myRetail RESTful service
This is the repository for the myRetail products API.  

## Prerequisites
* JDK 1.8
* Maven 3.3
* Docker

## To run directly from Docker Hub
Running the following commands will create a docker container for the myretail mongo db with some seed data,
as well as a second container with the REST api running.  After these commands jump
to the "Interacting with the application" section.
```
docker run -dit --name myretail-mongo zbodie/myretail-mongo
docker exec myretail-mongo mongoimport --host 127.0.0.1 --db myretail --collection price --file /price.json
docker run -dit --link myretail-mongo:mongo -p 8080:8080 zbodie/myretail-api java -jar /var/myretail/myretail-api-0.1.0.jar
```

## To build and run for Docker
First, navigate to /code.
```
cd code
```
Next, build and package with Maven (this step skips the unit tests, please execute unit tests on the main branch)
```
mvn -Dmaven.test.skip=true clean package
```
Build the Docker images
```
cd ../docker/myretail-mongo
docker build . -t myretail-mongo:latest
cd ../myretail-api
cp ../../code/target/myretail-api-0.1.0.jar .
docker build . -t myretail-api:latest
```
Run the newly created images
```
docker run -dit --name myretail-mongo myretail-mongo
docker exec myretail-mongo mongoimport --host 127.0.0.1 --db myretail --collection price --file /price.json
docker run -dit --link myretail-mongo:mongo -p 8080:8080 myretail-api java -jar /var/myretail/myretail-api-0.1.0.jar
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
