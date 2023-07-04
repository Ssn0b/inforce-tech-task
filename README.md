# Spring Boot User Management Application

This is a simple Spring Boot application for user registration, login, and user profile management. The application is built with Java, Spring Boot, Thymeleaf, and MySQL.

## Getting Started

1. Clone the repository to your local machine.
2. Import the project into your preferred Java IDE.
3. Update the database properties in `src/main/resources/application.properties`:
4. Make sure you have a MySQL server running with the appropriate credentials specified in the properties file.
5. Here is a example user added by myself in file.sql, so you can use it if you need.

## Running the Application

After setting up the database and configuring the properties, you can run the Spring Boot application using your IDE or using the following Maven command in the terminal:

## Endpoints

The application provides the following endpoints:

1. `GET /`: This endpoint displays the user registration form.
2. `POST /register`: This endpoint processes the user registration form and saves the new user in the database.
3. `GET /login`: This endpoint displays the login form.
4. `POST /login`: This endpoint processes the login form and authenticates the user. If the login is successful, it redirects to the home page (`/home`); otherwise, it redirects back to the login page with an error message.
5. `GET /home`: This endpoint displays the user's profile home page after successful login.
6. `GET /logout`: This endpoint logs out the user and redirects to the login page.
7. `POST /updateProfile`: This endpoint allows the user to update their profile information. After updating, it redirects to the home page.

## Example User

An example user is already inserted into the database using the `file.sql` script. You can use the following credentials to log in as the example user:

- Email: `vlad@gmail.com`
- Password: `vlad`

## Important Notes

- For a production application, you should handle password encryption securely using BCrypt or another strong encryption algorithm. This application uses `BCryptPasswordEncoder` from Spring Security to hash passwords securely.
- The application uses Thymeleaf templates for rendering the views.

Please make sure to change the necessary properties and configure your database appropriately before running the application. Enjoy exploring and extending this basic user management application!`
