# MovieAPI

## Overview
MovieAPI is a backend application developed using **Spring Boot**, **Java**, and **MySQL**. It provides endpoints to manage movies, including features like adding, updating, deleting, and fetching movie details. The API also supports user authentication and JWT-based authorization.

## Features
- Upload and store movie images.
- Add, update, and delete movies.
- Fetch details of a specific movie or all movies.
- User authentication with JWT tokens.
- Password reset functionality.

## Technologies Used
- **Java**: Backend language.
- **Spring Boot**: Application framework for building microservices.
- **MySQL**: Database for storing movie and user data.
- **JWT**: For securing APIs.
- **Maven**: Dependency management.

## API Documentation

### Authentication APIs

1. **Register a user**
   - **Endpoint**: `http://localhost:8080/api/v1/auth/register`
   - **Method**: `POST`
   - **Description**: Registers a new user.
   - **Request Body**:
     ```json
     {
       "name": "Sandesh Bhujbal",
       "username": "sandesh",
       "email": "bhujbalsandesh52@gmail.com",
       "password": "Sandesh@30"
     }
     ```
  - **Response Body**:
     ```json
     {
        "User register successfully!"
     }
     ```
     
 

2. **Login a user**
   - **Endpoint**: `http://localhost:8080/api/v1/auth/login`
   - **Method**: `POST`
   -  **Description**: Authenticates the user and returns a JWT token.
   - **Request Body**:
     ```json
     {
       "email": "bhujbalsandesh52@gmail.com",
       "password": "12345"
     }
     ```
     - **Response Body**:
     ```json
     {
      {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjY0MDg4MzAsImV4cCI6MTcyNjQwODg1NX0.LIdT4456XO8P40GGCRmylhG0K6NUOjie12joxW6I-3I",
        "refreshToken": "d4a563b7-98b2-4e16-abcb-3bcb0f05d5bf"
     }
     ```
   
3. **Refresh Token**
   - **Endpoint**: `http://localhost:8080/api/v1/auth/refresh`
   - **Method**: `POST`
   - **Description**: Generates a new access token using a refresh token.
   - **Request Body**:
     ```json
     {
       "refreshToken": "d4a563b7-98b2-4e16-abcb-3bcb0f05d5bf"
     }
     ```
    - **Response Body**:
     ```json
     {
        "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjY0MDg5OTMsImV4cCI6MTcyNjQwOTAxOH0.bhZWLkNnq0Kza8zgs3WH-dnKSYJteA_wfQuBkppGWXk",
        "refreshToken": "98b4efd1-9a60-48cb-bb85-bb456ed72e0a"
     }
     ```
     

### Movie APIs

1. **Upload a Movie Image**
   - **Endpoint**: `http://localhost:8080/file/upload`
   - **Method**: `POST`
   - **Description**: Uploads a movie image.
   - **Request Body**:
     - `file`: Multipart form file upload (image of the movie).

     - **Response Body**:
        ```json
          {
           "File uploaded : avengers_endgame1.png"
        }
        ```

2. **Add a Movie**
   - **Endpoint**: `http://localhost:8080/api/v1/movie/add-movie`
   - **Method**: `POST`
   - **Description**: Adds a new movie.
   - **Request Body** (FormData):
     - `file`: Multipart form file upload (movie poster).
     - `movieDto`: JSON string containing the movie details:
     ```json
     {
      "movieId": 4,
      "title": "jawan",
      "director": "Rohit shetty",
      "studio": "Bollywood",
     "movieCast": ["Shahrukh khan", "Priyanka Chopra"],
     "releaseYear": 2023,
     "poster": "default.png",
     "posterUrl": "url"
     }
     ```
    - **Response Body**:

     ```json
     {
    "movieId": 4,
    "title": "jawan",
    "director": "Rohit shetty",
    "studio": "Bollywood",
    "movieCast": [
        "Shahrukh khan",
        "Priyanka Chopra"
    ],
    "releaseYear": 2023,
    "poster": "jawan.png",
    "posterUrl": "http://localhost:8080/file/jawan.png"
   }
     ```
  

3. **Get a Movie by ID**
   - **Endpoint**: `http://localhost:8080/api/v1/movie/{id}`
   - **Method**: `GET`
   - **Description**: Retrieves the details of a specific movie by its ID.
   - **Response Body**:
     ```json
     {
     "movieId": 2,
     "title": "Animal",
     "director": "Sandeep Vanga",
     "studio": "Bollywood",
     "movieCast": [
        "Ranbir Kapoor",
        "Rashmika Mandanna",
        "Tripti Dimri"
     ],
     "releaseYear": 2023,
     "poster": "animal.png",
     "posterUrl": "http://localhost:8080/file/animal.png"
     }


4. **Get All Movies**
   - **Endpoint**: `http://localhost:8080/api/v1/movie/all`
   - **Method**: `GET`
   - **Headers**:
     - `Authorization`: Bearer token.
   - **Description**: Retrieves a list of all movies.
   - **Response Body**:
   ```json
    [
    {
        "movieId": 2,
        "title": "Animal",
        "director": "Sandeep Vanga",
        "studio": "Bollywood",
        "movieCast": [
            "Ranbir Kapoor",
            "Rashmika Mandanna",
            "Tripti Dimri"
        ],
        "releaseYear": 2023,
        "poster": "animal.png",
        "posterUrl": "http://localhost:8080/file/animal.png"
    },
    {
        "movieId": 3,
        "title": "flash-2",
        "director": "Sandeep Vanga",
        "studio": "Hollywood",
        "movieCast": [
            "Salaman Khan",
            "Rashmika Mandanna",
            "Tripti Dimri"
        ],
        "releaseYear": 2024,
        "poster": "flash.png",
        "posterUrl": "http://localhost:8080/file/flash.png"
    },
    {
        "movieId": 4,
        "title": "jawan",
        "director": "Rohit shetty",
        "studio": "Bollywood",
        "movieCast": [
            "Shahrukh khan",
            "Priyanka Chopra"
        ],
        "releaseYear": 2023,
        "poster": "jawan.png",
        "posterUrl": "http://localhost:8080/file/jawan.png"
    }
    ]

5. **Update a Movie**
   - **Endpoint**: `http://localhost:8080/api/v1/movie/update/{id}`
   - **Method**: `PUT`
   - **Description**: Updates the details of an existing movie.
   - **Request Body** (FormData):
     - `file`: Multipart form file upload (new movie poster).
     - `movieDtoObj`: JSON string with updated movie details:
     ```json
     {
       "movieId": 2,
       "title": "Animal",
       "director": "Sandeep Vanga, Rohit Shetty",
       "studio": "Bollywood",
       "movieCast": ["Ranbir Kapoor", "Rashmika Mandanna", "Tripti Dimri", "Bobby Deol"],
       "releaseYear": 2023,
       "poster": "default.png",
       "posterUrl": "url"
     }
     ```

   - **Description**: Updates the details of an existing movie.
     - **Response Body**:
    ```json
    {
    "movieId": 2,
    "title": "Animal",
    "director": "Sandeep Vanga, Rohit shetty",
    "studio": "Bollywood",
    "movieCast": [
        "Ranbir Kapoor",
        "Rashmika Mandanna",
        "Tripti Dimri",
        "Bobby Deol"
    ],
    "releaseYear": 2023,
    "poster": "animal.png",
    "posterUrl": "http://localhost:8080/file/animal.png"
   }

6. **Delete a Movie**
   - **Endpoint**: `http://localhost:8080/api/v1/movie/delete/{id}`
   - **Method**: `DELETE`
   - **Description**: Deletes a movie by its ID.
   - **Response Body**:
     ```json
     {
     Movie deleted with id = 3
     }

### Password Management APIs

1. **Forgot Password**
   - **Endpoint**: `http://localhost:8080/forgotPassword/verifyMail/{email}`
   - **Method**: `POST`
   - **Description**: Sends an OTP to the user's email to verify password reset.
   - **Response Body**:

2. **Verify OTP**
   - **Endpoint**: `http://localhost:8080/forgotPassword/verifyOtp/{otp}/{email}`
   - **Method**: `POST`
   - **Description**: Verifies the OTP for password reset.
   - **Response Body**:

3. **Change Password**
   - **Endpoint**: `http://localhost:8080/forgotPassword/changePassword/{email}`
   - **Method**: `POST`
   - **Request Body**:
     ```json
     {
       "password": "new-password",
       "repeatPassword": "new-password"
     }
     ```
   - **Description**: Changes the user's password after OTP verification.
   - **Response Body**:


## Setup and Installation

1. Clone the repository:
    ```bash
    git clone https://github.com/yourusername/MovieAPI.git
    ```

2. Navigate to the project directory:
    ```bash
    cd MovieAPI
    ```

3. Configure MySQL:
    - Create a database named `movies_db`.
    - Update the `application.properties` with your MySQL configurations.

4. Build the project:
    ```bash
    mvn clean install
    ```

5. Run the application:
    ```bash
    mvn spring-boot:run
    ```

6. The server will start at `http://localhost:8080`.

