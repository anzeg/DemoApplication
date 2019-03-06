package si.test.backend.coding.challenge.service.impl;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.test.backend.coding.challenge.entities.Movies;
import si.test.backend.coding.challenge.service.IMovieService;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class MovieService implements IMovieService {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Movies getMovie(Long imdbId) {

        return em.find(Movies.class, imdbId);
    }

    @Override
    public List<Movies> getMovies(QueryParameters query) {
        List<Movies> movies = JPAUtils.queryEntities(em, Movies.class, query);
        return movies;
    }

    @Override
    public Long getMoviesCount(QueryParameters query) {
        Long count = JPAUtils.queryEntitiesCount(em, Movies.class, query);
        return count;
    }

    @Override
    @Transactional
    public void save(Movies movie) {
        if (movie != null) {
            em.persist(movie);
        }
    }

    @Override
    @Transactional
    public void delete(Long movieId) {
        Movies movie = em.find(Movies.class, movieId);
        if (movie != null) {
            em.remove(movie);
        }
    }
}
