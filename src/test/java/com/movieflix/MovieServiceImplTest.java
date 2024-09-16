package com.movieflix;

import com.movieflix.repositories.MovieRepository;
import com.movieflix.dto.MovieDto;
import com.movieflix.service.FileService;
import com.movieflix.service.MovieServiceImpl;
import com.movieflix.models.Movie;
import com.movieflix.exceptions.MovieNotFoundException;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@ActiveProfiles("test")
public class MovieServiceImplTest {

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private FileService fileService;

    @InjectMocks
    private MovieServiceImpl movieService;

    @Value("${project.poster}")
    private String path = "src/test/resources/posters"; // Mock path for test

    @Value("${base.url}")
    private String baseUrl = "http://localhost:8080";

    private MovieDto movieDto;
    private Movie movie;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        movieDto = new MovieDto(
                1, "Inception", "Christopher Nolan", "Warner Bros",
                new HashSet<>(Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt")),
                2010, "inception.jpg", "http://localhost:8080/file/inception.jpg"
        );
        movie = new Movie(
                1, "Inception", "Christopher Nolan", "Warner Bros",
                new HashSet<>(Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt")),
                2010, "inception.jpg"
        );
    }

    @Test
    public void testAddMovie() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "inception.jpg", "image/jpeg", "dummy content".getBytes());
        when(fileService.uploadFile(anyString(), any())).thenReturn("inception.jpg");
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        MovieDto result = movieService.addMovie(movieDto, file);
        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
        assertEquals("http://localhost:8080/file/inception.jpg", result.getPoster());
    }

    @Test
    public void testGetMovie() {
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie));

        MovieDto result = movieService.getMovie(1);
        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
        assertEquals("http://localhost:8080/file/inception.jpg", result.getPoster());
    }

    @Test
    public void testGetAllMovies() {
        when(movieRepository.findAll()).thenReturn(Collections.singletonList(movie));

        List<MovieDto> result = movieService.getAllMovies();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals("Inception", result.get(0).getTitle());
    }

    @Test
    public void testUpdateMovie() throws IOException {
        MockMultipartFile file = new MockMultipartFile("file", "inception-updated.jpg", "image/jpeg", "updated content".getBytes());
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie));
        when(fileService.uploadFile(anyString(), any())).thenReturn("inception-updated.jpg");
        when(movieRepository.save(any(Movie.class))).thenReturn(movie);

        MovieDto updatedDto = new MovieDto(
                1, "Inception", "Christopher Nolan", "Warner Bros",
                new HashSet<>(Arrays.asList("Leonardo DiCaprio", "Joseph Gordon-Levitt")),
                2010, "inception-updated.jpg", "http://localhost:8080/file/inception-updated.png"
        );

        MovieDto result = movieService.updateMovie(1, updatedDto, file);
        assertNotNull(result);
        assertEquals("Inception", result.getTitle());
        assertEquals("http://localhost:8080/file/inception-updated.jpg", result.getPoster());
    }

    @Test
    public void testDeleteMovie() throws IOException {
        when(movieRepository.findById(anyInt())).thenReturn(Optional.of(movie));
        doNothing().when(movieRepository).delete(any(Movie.class));
        doNothing().when(Files.class).deleteIfExists(any());

        String result = movieService.deleteMovie(1);
        assertEquals("Movie deleted with id = 1", result);
    }
}
