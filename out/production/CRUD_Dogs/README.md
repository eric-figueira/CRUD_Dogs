## CRUD Dogs

### What is it
CRUD Dogs is a project developed in the third semester of a IT course on Colégio Técnico de Campinas - Unicamp. It consists of a Graphical User Interface (GUI) made in Java containing operations such as Insert, Read, Update and Delete dogs, the entity we choose to manipulate, from a DataBase, SQL Server in this case and acess to API

### What we have learned
Not only this project allowed us to review crucial aspects for a CRUD like acessing a Database, which means we had to develop the DAOs and DBOs classes for it, and building a GUI in java, but it also required us to learn how to deal with requests for an API and its responses.
The API used is public on the internet and allows us to get data from a CEP.


### How was it made
The whole project was developed in Java. The database used was the SQL Server

### How to run it

In order to run the program, you can use IntelliJ to run it/visualize the code/make modifications or follow the following steps:
Inside the API folder, run theses commands:
```
javac -classpath .;jackson-all-1.9.0.jar *.java
java  -classpath .;jackson-all-1.9.0.jar Programa
```
Note: Before executing the code, go to BDSQLServer.java and change sqlserver, databasename, "logon", "password" to your corresponding credentails

#### Images
