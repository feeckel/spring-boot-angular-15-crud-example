# Spring Boot + Angular 15 CRUD example

## Todos:

1. Create Table component to display all rooms
   2. add number of seats and occupation to room model. 
   3. Make Title and Description mandatory
2. Create Detail component, that displays selected room from table  
   3. Only display if Room is selected
3. Create Add Component, that allows to add new Rooms with Validation
   4. Mandatory Fields are filled out
   5. max length for title 20
4. Allow to edit Room in detail component
5. Allow to delete selected room in detail component
6. Display List and Detail next to each other with a ration of 60/40
7. Use Material Design Objects.
## Run Spring Boot application
```
mvn spring-boot:run
```
The Spring Boot Server will export API at port `8081`.

## Run Angular Client
```
npm install
ng serve --port 8081
```
