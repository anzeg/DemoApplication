package si.test.backend.coding.challenge.service;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.test.backend.coding.challenge.entities.Movies;

import java.util.List;

public interface IMovieService {

    Movies getMovie(Long imdbId);

    List<Movies> getMovies(QueryParameters query);

    Long getMoviesCount(QueryParameters query);

    void save(Movies movie);

    void delete(Long movieId);
}
