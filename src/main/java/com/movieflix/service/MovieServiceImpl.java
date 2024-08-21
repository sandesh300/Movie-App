package com.movieflix.service;

import com.movieflix.dto.MovieDto;
import com.movieflix.models.Movie;
import com.movieflix.repositories.MovieRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService{

    @Value("${project.poster}")
    private String path;

    @Value("${base.url}")
    private String baseUrl;

    private final MovieRepository movieRepository;

    private final FileService fileService;

    public MovieServiceImpl(MovieRepository movieRepository, FileService fileService) {
        this.movieRepository = movieRepository;
        this.fileService = fileService;
    }

    @Override
    public MovieDto addMovie(MovieDto movieDto, MultipartFile file) throws IOException {
        // 1. upload the file
          String uploadedFileName = fileService.uploadFile(path, file);

        // 2. to set the value of field 'poster' as filename
        movieDto.setPoster(uploadedFileName);

        // 3. map movie dto to movie object
        Movie movie = new Movie(
                movieDto.getMovieId(),
                movieDto.getTitle(),
                movieDto.getDirector(),
                movieDto.getStudio(),
                movieDto.getMovieCast(),
                movieDto.getReleaseYear(),
                movieDto.getPoster()
        );

        // 4. to save the movie object -> saved movie object
        Movie savedMovie = movieRepository.save(movie);

        // 5. generate the poster url
        String posterUrl = baseUrl + "/file/" + uploadedFileName;

        // 6. map movie object to dto object and return it
        MovieDto response = new MovieDto(
                savedMovie.getMovieId(),
                savedMovie.getTitle(),
                savedMovie.getDirector(),
                savedMovie.getStudio(),
                savedMovie.getMovieCast(),
                savedMovie.getReleaseYear(),
                savedMovie.getPoster(),
                posterUrl
        );
        return response;
    }

    @Override
    public MovieDto getMovie(Integer movieId) {
        // 1. check the data in DB and if exists, fetch the data of given ID
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Movie not found with id -" +movieId));

        // 2. generate poster url
        String posterUrl = baseUrl + "/file/" + movie.getPoster();

        // 3. map to movie dto object and write it
        MovieDto response = new MovieDto(
                movie.getMovieId(),
                movie.getTitle(),
                movie.getDirector(),
                movie.getStudio(),
                movie.getMovieCast(),
                movie.getReleaseYear(),
                movie.getPoster(),
                posterUrl
        );

        return response;
    }

    @Override
    public List<MovieDto> getAllMovies() {
        // 1. fetch all data from DB
         List<Movie> movies= movieRepository.findAll();

         List<MovieDto> movieDtos = new ArrayList<>();

        // 2. iterate through the list, generate posterUrl for each movie object, and map to movieDto object
        for(Movie movie : movies) {
            String posterUrl = baseUrl + "/file/" + movie.getPoster();
            MovieDto movieDto = new MovieDto(
                    movie.getMovieId(),
                    movie.getTitle(),
                    movie.getDirector(),
                    movie.getStudio(),
                    movie.getMovieCast(),
                    movie.getReleaseYear(),
                    movie.getPoster(),
                    posterUrl
            );
            movieDtos.add(movieDto);
        }

        return movieDtos;
    }
}
