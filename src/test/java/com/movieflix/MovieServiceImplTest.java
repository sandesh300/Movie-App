package com.movieflix;

import com.movieflix.repositories.MovieRepository;
import com.movieflix.dto.MovieDto;
import com.movieflix.service.FileService;
import com.movieflix.service.MovieServiceImpl;
import com.movieflix.models.Movie;
import com.movieflix.exceptions.MovieNotFoundException;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Optional;
import java.util.Set;

public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieServiceImpl movieServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize mocks before each test
    }

    // Test case for getMovie method
    @Test
    void testGetMovie_Success() {
        // Arrange: Create a mock Movie object
        Movie movie = new Movie(1, "Inception", "Christopher Nolan", "Warner Bros",
                Set.of("Leonardo DiCaprio"), 2010, "poster.jpg");

        // Mock the repository to return the mock movie
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        // Act: Call the method to be tested
        MovieDto result = movieServiceImpl.getMovie(1);

        // Assert: Check that the results are correct
        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
        assertEquals("Christopher Nolan", result.getDirector());
        verify(movieRepository, times(1)).findById(1); // Verify interaction with repository
    }

    @Test
    void testGetMovie_NotFound() {
        // Arrange: Mock the repository to return an empty Optional (movie not found)
        when(movieRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert: Check that the exception is thrown
        assertThrows(MovieNotFoundException.class, () -> movieServiceImpl.getMovie(1));
    }

    // Test case for deleteMovie method
    @Test
    void testDeleteMovie_Success() throws IOException {
        // Arrange: Create a mock Movie object
        Movie movie = new Movie(1, "Inception", "Christopher Nolan", "Warner Bros",
                Set.of("Leonardo DiCaprio"), 2010, "poster.jpg");

        // Mock the repository to return the mock movie
        when(movieRepository.findById(1)).thenReturn(Optional.of(movie));

        // Act: Call the deleteMovie method
        String result = movieServiceImpl.deleteMovie(1);

        // Assert: Check that the movie was deleted and the correct message is returned
        assertEquals("Movie deleted with id = 1", result);
        verify(movieRepository, times(1)).delete(movie); // Verify the delete interaction with the repository
    }

    @Test
    void testDeleteMovie_NotFound() {
        // Arrange: Mock the repository to return an empty Optional (movie not found)
        when(movieRepository.findById(1)).thenReturn(Optional.empty());

        // Act & Assert: Check that the MovieNotFoundException is thrown
        assertThrows(MovieNotFoundException.class, () -> movieServiceImpl.deleteMovie(1));
    }
}
