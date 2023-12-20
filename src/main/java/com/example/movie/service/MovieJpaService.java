package com.example.movie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import com.example.movie.model.Movie;
import com.example.movie.repository.MovieJpaRepository;
import com.example.movie.repository.MovieRepository;
import java.util.*;

@Service
public class MovieJpaService implements MovieRepository {

	@Autowired
	private MovieJpaRepository movieRepository;

	@Override
	public ArrayList<Movie> getMovies() {
		List<Movie> movieList = movieRepository.findAll();
		ArrayList<Movie> movies = new ArrayList<>(movieList);
		return movies;
	}

	@Override
	public Movie getMovieById(int movieId) {
		try {
			Movie movie = movieRepository.findById(movieId).get();
			return movie;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
	}

	@Override
	public Movie addMovie(Movie movie) {
		movieRepository.save(movie);
		return movie;
	}

	@Override
	public Movie updateMovie(int movieId, Movie movie) {
		try {
			Movie newMovie = movieRepository.findById(movieId).get();
			if (movie.getMovieName() != null) {
				newMovie.setMovieName(movie.getMovieName());
			}
			if (movie.getLeadActor() != null) {
				newMovie.setLeadActor(movie.getLeadActor());
			}
			movieRepository.save(newMovie);
			return newMovie;
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

	@Override
	public void deletemovie(int movieId) {
		try {
			movieRepository.deleteById(movieId);
		} catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}

	}

}
