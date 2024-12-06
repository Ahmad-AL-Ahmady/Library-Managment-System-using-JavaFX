
# BookGuru - Library Management System

## Overview

BookGuru is a comprehensive desktop application designed for library management. It allows administrators to handle books, staff, customers, and overall library operations effectively.

## Prerequisites

- Java Development Kit (JDK) 21 or later
- MySQL 8.0.33 or later
- IDE (e.g., IntelliJ)
- Maven

## Project Setup

1. Open IntelliJ IDE and select "Open."
2. Navigate to the project directory and select the project folder.
3. IntelliJ will detect the project and set it up. Wait for the process to complete.

## Database Setup
1. Ensure the MySQL server is running.
2. Run Maven to compile and package the application:
   ```bash
   mvn install
   mvn compile
   ```
   Or use the Maven icon in IntelliJ after opening the `pom.xml` file.

3. Install MySQL Server:
    - Download the MySQL installer from the official website: [MySQL Installer](https://dev.mysql.com/downloads/installer/)
    - Run the installer and follow the setup wizard.
    - Choose a setup type (e.g., "Full").
    - You can set a `root` password for your MySQL server.

4. Create a new database named 'library':
    - Open MySQL Workbench or MySQL command-line interface.
    - Run the following SQL command:
      ```sql
      CREATE DATABASE library;
      ```

5. Update database credentials in `DBConnection.java`:
    - Open `BookGuru\src\main\java\com\example\librarymanagmentsystem\DBConnection.java`.
    - Modify the connection URL, username, and password to match your MySQL setup:
      ```java
      Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/library", "root", "root");
      ```
      
6. Run `CreateTables.java` from IDE to create the required database tables:
    - Navigate to `BookGuru\src\main\java\com\example\librarymanagmentsystem\` in IntelliJ.
    - Open `CreateTables.java`.
    - Run the file to execute the SQL statements and set up the database schema.
    - You can also run it using:
      ```bash
      java -cp "target/hotel-management-1.0-SNAPSHOT-jar-with-dependencies.jar;lib/" com.example.librarymanagmentsystem.CreateTables
      ```

## Running the Application

1. In IntelliJ, navigate to `BookGuru\src\main\java\com\example\librarymanagmentsystem\`.
2. Run `HelloApplication.java` or the respective main class to start the application.
    - You can also run it using:
      ```bash
      mvn javafx:run

      ```

3. Log in using the default admin credentials:
    - Username: admin
    - Password: admin
