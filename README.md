
# MovieAPI

**MovieAPI** is a backend application built using **Spring Boot**, **Java**, and **MySQL**. It provides RESTful APIs for managing movies, including adding, updating, deleting, and retrieving movie details. It also features user authentication and JWT-based authorization.

---

## Features
- Upload and store movie images.
- Create, update, and delete movies.
- Fetch details of individual movies or a list of all movies.
- User authentication with JWT tokens.
- Password reset and recovery functionality.

---

## Technologies Used
- **Java**: Backend language.
- **Spring Boot**: Application framework for building microservices.
- **MySQL**: Database to store movie and user data.
- **JWT (JSON Web Tokens)**: Securing API endpoints.
- **Maven**: Dependency management.

---
#### API Documentation Website url -https://walnut-wrist-9da.notion.site/Movie-API-Documentation-8258fc33d572475384e9199e9deba52a

## API Endpoints Documentations

### 1. **Authentication APIs**

#### a. **Register a User**
- **URL**: `http://localhost:8080/api/v1/auth/register`
- **Method**: `POST`
- **Description**: Registers a new user.
- **Request Body**:
  ```json
  {
    "name": "Sandesh Bhujbal",
    "username": "sandesh",
    "email": "bhujbalsandesh52@gmail.com",
    "password": "12345"
  }
  ```
- **Response**:
  ```json
  {
    "message": "User registered successfully!"
  }
  ```

#### b. **Login a User**
- **URL**: `http://localhost:8080/api/v1/auth/login`
- **Method**: `POST`
- **Description**: Authenticates the user and returns a JWT token.
- **Request Body**:
  ```json
  {
    "email": "bhujbalsandesh52@gmail.com",
    "password": "12345"
  }
  ```
- **Response**:
  ```json
  {
     "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjY0MDg4MzAsImV4cCI6MTcyNjQwODg1NX0.LIdT4456XO8P40GGCRmylhG0K6NUOjie12joxW6I-3I",
     "refreshToken": "d4a563b7-98b2-4e16-abcb-3bcb0f05d5bf"
  }
  ```

#### c. **Refresh JWT Token**
- **URL**: `http://localhost:8080/api/v1/auth/refresh`
- **Method**: `POST`
- **Description**: Generates a new access token using the refresh token.
- **Request Body**:
  ```json
  {
    "refreshToken": "d4a563b7-98b2-4e16-abcb-3bcb0f05d5bf"
  }
  ```
- **Response**:
  ```json
  {
    "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBnbWFpbC5jb20iLCJpYXQiOjE3MjY0MDg5OTMsImV4cCI6MTcyNjQwOTAxOH0.bhZWLkNnq0Kza8zgs3WH-dnKSYJteA_wfQuBkppGWXk",
    "refreshToken": "98b4efd1-9a60-48cb-bb85-bb456ed72e0a"
  }
  ```

---

### 2. **Movie APIs**

#### a. **Upload a Movie Image**
- **URL**: `http://localhost:8080/file/upload`
- **Method**: `POST`
- **Description**: Uploads a movie image.
- **Request**:
  - `file`: Multipart file upload (image).
- **Response**:
  ```json
  {
    "message": "File uploaded: avengers_endgame1.png"
  }
  ```

#### b. **Add a Movie**
- **URL**: `http://localhost:8080/api/v1/movie/add-movie`
- **Method**: `POST`
- **Description**: Adds a new movie.
- **Request (Multipart FormData)**:
  - `file`: Multipart file upload (movie poster).
  - `movieDto`: JSON string with movie details:
  ```json
  {
    "movieId": 4,
    "title": "Jawan",
    "director": "Rohit Shetty",
    "studio": "Bollywood",
    "movieCast": ["Shahrukh Khan", "Priyanka Chopra"],
    "releaseYear": 2023,
    "poster": "default.png",
    "posterUrl": "url"
  }
  ```
- **Response**:
  ```json
  {
    "movieId": 4,
    "title": "Jawan",
    "director": "Rohit Shetty",
    "studio": "Bollywood",
    "movieCast": ["Shahrukh Khan", "Priyanka Chopra"],
    "releaseYear": 2023,
    "poster": "jawan.png",
    "posterUrl": "http://localhost:8080/file/jawan.png"
  }
  ```

#### c. **Get Movie by ID**
- **URL**: `http://localhost:8080/api/v1/movie/{id}`
- **Method**: `GET`
- **Description**: Retrieves details of a specific movie by its ID.
- **Response**:
  ```json
  {
    "movieId": 2,
    "title": "Animal",
    "director": "Sandeep Vanga",
    "studio": "Bollywood",
    "movieCast": ["Ranbir Kapoor", "Rashmika Mandanna", "Tripti Dimri"],
    "releaseYear": 2023,
    "poster": "animal.png",
    "posterUrl": "http://localhost:8080/file/animal.png"
  }
  ```

#### d. **Get All Movies**
- **URL**: `http://localhost:8080/api/v1/movie/all`
- **Method**: `GET`
- **Headers**:
  - `Authorization`: Bearer token.
- **Description**: Retrieves a list of all movies.
- **Response**:
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
  ```

#### e. **Update a Movie**
- **URL**: `http://localhost:8080/api/v1/movie/update/{id}`
- **Method**: `PUT`
- **Description**: Updates details of an existing movie.
- **Request (Multipart FormData)**:
  - `file`: Multipart file upload (new movie poster).
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
- **Response**:
  ```json
  {
    "movieId": 2,
    "title": "Animal",
    "director": "Sandeep Vanga, Rohit Shetty",
    "studio": "Bollywood",
    "movieCast": ["Ranbir Kapoor", "Rashmika Mandanna", "Tripti Dimri", "Bobby Deol"],
    "releaseYear": 2023,
    "poster": "animal.png",
    "posterUrl": "http://localhost:8080/file/animal.png"
  }
  ```

#### f. **Delete a Movie**
- **URL**: `http://localhost:8080/api/v1/movie/delete/{id}`
- **Method**: `DELETE`
- **Description**: Deletes a movie by its ID.
- **Response**:
  ```json
  {
    "message": "Movie deleted with id = 3"
  }
  ```

---

### 3. **Password Management APIs**

#### a. **Forgot Password**
- **URL**: `http://localhost:8080/forgotPassword/verifyMail/{email}`
- **Method**: `POST`
- **Description**: Sends an OTP to the user's email for password reset.
- **Response**:
  ```json
  {
    "message": "Email sent for verification!"
  }
  ```

#### b. **Verify OTP**
- **URL**: `http://localhost:8080/forgotPassword/verifyOtp/{otp}/{email}`
- **Method**: `POST`
- **Description**: Verifies the OTP for password reset.
- **Response**:
  ```json
  {
    "message": "OTP verified!"
  }
  ```

#### c. **Change Password**
- **URL**: `http://localhost:8080/forgotPassword/changePassword/{email}`
- **Method**: `POST`
- **Request Body**:
  ```json
  {
    "password": "123456",
    "repeatPassword": "123456"
  }
  ```
- **Description**: Changes the user's password after OTP verification.
- **Response**:
  ```json
  {
    "message": "Password has been changed!"
  }
  ```

---

## Setup and Installation

1. **Clone the Repository**:
   ```bash
   git clone https://https://github.com/sandesh300/Movie-API.git
   ```

2. **Navigate to the Project Directory**:
   ```bash
   cd MovieAPI
   ```

3. **Configure MySQL**:
   - Create a database named `movies_db`.
   - Update the `application.properties` file with your MySQL configurations.

4. **Build the Project**:
   ```bash
   mvn clean install
   ``
