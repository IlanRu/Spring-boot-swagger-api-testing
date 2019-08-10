# Spring-boot-swagger-api-testing

This is a spring-boot application with a REST controller that allows the user to perform some actions on data persisted on an h2 database using JPA.
Available actions(and example commands):
•	List of available items: curl -v localhost:8080/items
•	Read item details (by item no): curl -v localhost:8080/items/1
•	Withdrawal of a number of items of certai type: curl -X PUT localhost:8080/withdraw/1 -H 'Content-type:application/json' -d amount=2
•	Deposit quantity of a specific item to stock: curl -X PUT localhost:8080/deposit/{item-number} -H 'Content-type:application/json' -d amount=4
•	Add item to stock: curl -X POST localhost:8080/items -H 'Content-type:application/json' -d amount=6 -d name="Alastair Reynolds" -d code=1966 -d num=5
•	Delete an item from stock: curl -v -X DELETE localhost:8080/items/1

H2 dm username and password are both given in the properties file.
A docker image is available at: https://hub.docker.com/r/sabugoru/openlegacy-spring-test
And finally some pretty pictures of the app in action:
