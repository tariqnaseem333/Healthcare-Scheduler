# Health Care Appointment Scheduler
## It is a Java Enterprise Application maven based project using various concepts of OOPs, Java API(Regex API, DateTime API, Collection Framework), Java SE8 Features and use Tools(Properties File, Logging log4j2, JUNIT). 
### The Health Care Appointment Scheduler is a Console-based application designed to streamline the process of scheduling appointments between doctors and patients. For the given time slot of doctors, patients can book the available slot. Patients cannot book appointments that overlap with existing ones. This ensures a smooth experience for booking and viewing appointments. It has 3 User Stories:-
### 1.) View Available Doctor
### 2.) Search a Doctor by Specialization
### 3.) Book Appointment

#### The different steps taking place in the application are explained below:- 
##### 1.) User inputs the data in Tester Class (Presentation Tier). In this project, the client tier is not used. the inputs will be taken directly in the Tester Class.
##### 2.) The Tester class sends the Model class object to the Service Class (Business Tier).
##### 3.) The Service class sends the object to the Validator class to get the inputs validated. 
##### 4.) If the inputs are in valid format, the data is send to the DAO class (Persistence Tier) to access data from the database. In this project, the database is not being used simply a hard coded values will be returned from the database.
##### 4.) The Service class also performs operations like view available doctor, search a doctor by specialization and book appointment after validation. In this project, the database is not being used a hard coded data will be returned.
##### 5.) Based on the responses from the Validator and the DAO classes, the Service class formulates  either a successful output or a failure output and return the same to the Tester class.
##### 6.) The Tester class then displays the output to the User.
