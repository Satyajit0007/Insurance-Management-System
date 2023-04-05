# Insurance-Management-System
Its an Insurance Management System. This system allows for the creation and management of client data, insurance policies, and claims. The system provides a set of APIs that enable users to perform CRUD operations on each of these entities, allowing clients to be created and updated, policies to be managed, and claims to be filed and processed. This System provides a comprehensive solution for managing insurance-related data and operations. By providing a set of APIs for managing client data, insurance policies, and claims, the system can streamline workflows, improve efficiency, and reduce errors.
<hr>
<br>

<h3>Prerequisites:<h3>
 </hr>
 <li>JDK 8 or higher installed
 <li>Maven installed
 <li>Clone the repository containing your Spring Boot application code.
 <li>Navigate to the root folder of the project in the terminal or command prompt.
 <li>Run the command "mvn clean install" to build the application.
 <li>Once the build is successful, run the command "mvn spring-boot:run" to start the application.
 <li>The application should now be up and running on your local machine.</li>
<br>
 
# Steps  to  Setup  the  Application : 
 <hr>
<p>1.	Open src >main>resources>application.properties<p>
 <hr style="height:2px;border-width:0;color:blue;background-color:blue">

![appliacatioProperties](https://user-images.githubusercontent.com/51885478/230089331-7e938c17-b2b4-415b-afc1-ea59afa53c96.PNG)

2.	Configure your mySql database
 <hr>
    <li>	change server.port
    <li>put your database.username
    <li>put your database.password


![configureDB](https://user-images.githubusercontent.com/51885478/230093271-9b960a8c-9f69-4eb1-aa65-c5b102b62459.PNG)

     
3.	Create MySql database  : 
 <hr>
     
         Create mySql database (database name : IMTDB {suggested})
         Use MySql command to create database
         	example : 
              create database IMTDB;
              use IMTDB;
          
    <br>
4.	Run The Application .
<hr>

<br>
     
 # Steps to Execute Api/ Application :  
<hr>
<br>    
 <p style="text-decoration:underline;color:green;">1.	Admin Registration (POST  : /api/admin/)</p> 
     <hr>
      <li>This endpoint allows the creation of a new administrator account.
      <li>The endpoint expects a JSON payload in the request body containing the administrator's details, such as name, email, and password.
      <li>The endpoint should validate the input and create a new administrator account in the database if the input is valid.</li>

