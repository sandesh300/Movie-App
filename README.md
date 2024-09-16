
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

## Movie Module Unit Tests

This section documents the unit tests for the Movie Module, covering core functionalities such as adding a movie, updating a movie, get all movies, get movie by id, deleting a movie.

### Prerequisites

Ensure the following setup is complete before running the tests:

- **JUnit 5**: Tests are written using JUnit 5.
- **Mockito**: Used for mocking dependencies in services and repositories.
- **Spring Boot Test**: Provides support for integration testing in Spring Boot applications.

### Running Tests

To run the tests, use:
```bash
mvn test
```

Or in IntelliJ, right-click on the test file and select **Run 'Tests'**.

### Test Cases for Movie Module

#### 1. **Test `addMovie` API**

This test case verifies that the `addMovie` API correctly creates a new movie and returns the expected movie details.

```java
@Test
public void shouldCreateMovieWhenValidRequest() throws IOException {
    // Given: A MovieDto object with valid data and a MultipartFile
    MovieDto movieDto = new MovieDto(null, "Inception", "Christopher Nolan", "Warner Bros.", Set.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 2010, "inception-poster.jpg", null);
    MultipartFile file = new MockMultipartFile("file", "inception-poster.jpg", "image/jpeg", "dummy content".getBytes());

    // Mock: Simulate the file upload and movie creation process
    String uploadedFileName = "inception-poster.jpg";
    when(fileService.uploadFile(anyString(), any(MultipartFile.class))).thenReturn(uploadedFileName);
    Movie savedMovie = new Movie(1, movieDto.getTitle(), movieDto.getDirector(), movieDto.getStudio(), movieDto.getMovieCast(), movieDto.getReleaseYear(), uploadedFileName);
    when(movieService.addMovie(any(MovieDto.class), any(MultipartFile.class))).thenReturn(movieDto);

    // When: The controller's addMovie method is called
    ResponseEntity<MovieDto> response = movieController.addMovie(file, new ObjectMapper().writeValueAsString(movieDto));

    // Then: Assert that the response is CREATED and the movie details match the expected data
    assertEquals(HttpStatus.CREATED, response.getStatusCode());
    assertEquals(movieDto.getTitle(), response.getBody().getTitle());
    assertEquals(movieDto.getDirector(), response.getBody().getDirector());
}
```

- **Explanation**:
  - **Given**: Prepare a valid `MovieDto` and a mock file.
  - **When**: Call the controller's method to add a movie.
  - **Then**: Verify the response status and the content of the returned movie.

#### 2. **Test `updateMovie` API**

This test case checks that the `updateMovie` API updates an existing movie's details correctly.

```java
@Test
public void shouldUpdateMovieWhenValidRequest() throws IOException {
    // Given: An existing movie ID and an updated MovieDto object
    Long movieId = 1L;
    MovieDto updatedMovieDto = new MovieDto(movieId, "Inception", "Christopher Nolan", "Warner Bros.", Set.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 2010, "inception-updated-poster.jpg", null);
    MultipartFile file = new MockMultipartFile("file", "inception-updated-poster.jpg", "image/jpeg", "dummy content".getBytes());

    // Mock: Simulate file upload and movie update process
    String updatedFileName = "inception-updated-poster.jpg";
    when(fileService.uploadFile(anyString(), any(MultipartFile.class))).thenReturn(updatedFileName);
    Movie updatedMovie = new Movie(movieId, updatedMovieDto.getTitle(), updatedMovieDto.getDirector(), updatedMovieDto.getStudio(), updatedMovieDto.getMovieCast(), updatedMovieDto.getReleaseYear(), updatedFileName);
    when(movieService.updateMovie(eq(movieId), any(MovieDto.class), any(MultipartFile.class))).thenReturn(updatedMovieDto);

    // When: The controller's updateMovie method is called
    ResponseEntity<MovieDto> response = movieController.updateMovie(movieId, file, new ObjectMapper().writeValueAsString(updatedMovieDto));

    // Then: Assert that the response is OK and the movie details match the updated data
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(updatedMovieDto.getTitle(), response.getBody().getTitle());
    assertEquals(updatedMovieDto.getPoster(), response.getBody().getPoster());
}
```

- **Explanation**:
  - **Given**: An existing movie ID and updated movie details.
  - **When**: Call the controller's method to update the movie.
  - **Then**: Verify that the response status is OK and the updated movie details are correct.

#### 3. **Test `getAllMovies` API**

This test case verifies that the `getAllMovies` API correctly retrieves a list of all movies.

```java
@Test
public void shouldReturnAllMovies() {
    // Given: A list of movies
    List<MovieDto> movieDtos = Arrays.asList(
        new MovieDto(1L, "Inception", "Christopher Nolan", "Warner Bros.", Set.of("Leonardo DiCaprio", "Joseph Gordon-Levitt"), 2010, "inception-poster.jpg", "http://localhost/file/inception-poster.jpg"),
        new MovieDto(2L, "The Dark Knight", "Christopher Nolan", "Warner Bros.", Set.of("Christian Bale", "Heath Ledger"), 2008, "dark-knight-poster.jpg", "http://localhost/file/dark-knight-poster.jpg")
    );
    when(movieService.getAllMovies()).thenReturn(movieDtos);

    // When: The controller's getAllMovies method is called
    ResponseEntity<List<MovieDto>> response = movieController.getAllMoviesHandler();

    // Then: Assert that the response is OK and contains the list of movies
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(movieDtos.size(), response.getBody().size());
    assertEquals("Inception", response.getBody().get(0).getTitle());
    assertEquals("The Dark Knight", response.getBody().get(1).getTitle());
}
```

- **Explanation**:
  - **Given**: A predefined list of movies.
  - **When**: Call the controller's method to get all movies.
  - **Then**: Verify the response status and ensure the list contains the expected movies.



#### 4. **Test `getMovieById` API**

This test case verifies that the `getMovieById` API works as expected and returns the correct movie details for a valid movie ID.

```java
@Test
public void shouldReturnMovieWhenMovieIdExists() {
    // Given: A valid movie ID
    Long movieId = 1L;

    // Mock: Simulating the expected Movie object that should be returned by the service
    Movie mockMovie = new Movie(movieId, "Inception", "A mind-bending thriller", 2010);
    when(movieService.getMovieById(movieId)).thenReturn(Optional.of(mockMovie));

    // When: The controller's getMovieById method is called
    ResponseEntity<Movie> response = movieController.getMovieById(movieId);

    // Then: Assert that the response is OK and the movie returned matches the expected data
    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertEquals(mockMovie.getTitle(), response.getBody().getTitle());
    assertEquals(mockMovie.getYear(), response.getBody().getYear());
}
```

- **Explanation**: 
  - **Given**: We prepare a movie ID and mock the service to return the movie with that ID.
  - **When**: The controller is invoked to fetch movie details.
  - **Then**: We validate the response status and content.

#### 5. **Test `deleteMovie` API**

This test case verifies that the `deleteMovie` API correctly deletes a movie by its ID.

```java
@Test
public void shouldDeleteMovieWhenMovieIdExists() {
    // Given: A valid movie ID to be deleted
    Long movieId = 1L;

    // Mock: Simulating the behavior of the movieService to delete the movie
    doNothing().when(movieService).deleteMovie(movieId);

    // When: The controller's deleteMovie method is called
    ResponseEntity<Void> response = movieController.deleteMovie(movieId);

    // Then: Assert that the response status is NO_CONTENT (204) indicating successful deletion
    assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

    // Verify that the movieService's deleteMovie method was called exactly once
    verify(movieService, times(1)).deleteMovie(movieId);
}
```

- **Explanation**: 
  - **Given**: A movie ID is passed for deletion, and the service's delete behavior is mocked.
  - **When**: The controller is called to delete the movie.
  - **Then**: We ensure the correct status is returned and the service is called exactly once.



### Additional Notes

- **Mocking**: Use Mockito to mock services and file uploads to isolate the controller logic from actual service implementations.
- **Edge Cases**: Consider writing tests for edge cases such as invalid inputs, missing data, or failure scenarios to ensure robustness.

---
