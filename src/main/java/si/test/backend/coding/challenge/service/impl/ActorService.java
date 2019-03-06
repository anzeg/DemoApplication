package si.test.backend.coding.challenge.service.impl;

import com.kumuluz.ee.rest.beans.QueryParameters;
import com.kumuluz.ee.rest.utils.JPAUtils;
import si.test.backend.coding.challenge.entities.Actors;
import si.test.backend.coding.challenge.entities.Movies;
import si.test.backend.coding.challenge.service.IActorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@RequestScoped
public class ActorService implements IActorService {

    @PersistenceContext
    private EntityManager em;

    @Inject
    MovieService movieService;

    @Override
    public Actors getActor(Integer actorId) {

        return em.find(Actors.class, actorId);
    }

    @Override
    public List<Actors> getActors(QueryParameters query) {
        List<Actors> actors = JPAUtils.queryEntities(em, Actors.class, query);
        return actors;
    }

    @Override
    public Long getActorCount(QueryParameters query) {
        Long count = JPAUtils.queryEntitiesCount(em, Actors.class, query);
        return count;
    }

    @Override
    @Transactional
    public void save(Actors actor) {
        if (actor != null) {
            em.persist(actor);
        }
    }

    @Override
    @Transactional
    public void delete(Integer actorId) {
        Actors Actor = em.find(Actors.class, actorId);
        if (Actor != null) {
            em.remove(Actor);
        }
    }

    @Override
    @Transactional
    public void addMovieToActor(Integer actorId, Long imdbId) {
        if (actorId != null && imdbId != null) {
            Actors existActors = getActor(actorId);
            Movies existMovie = movieService.getMovie(imdbId);
            if (existActors == null){
                throw new RuntimeException("Actor with actorId=" + actorId + " doesn't exist");
            } else if(existMovie == null) {
                throw new RuntimeException("Movie with imdbId=" + imdbId + " doesn't exist");
            }
            existActors.getMovies().add(existMovie);
            existMovie.getActors().add(existActors);
        } else{
            throw new RuntimeException("Missing requeired input parameters imdbId=" + imdbId + " or actorId=" + actorId);
        }
    }

    @Override
    @Transactional
    public void removeMovieFromActor(Integer actorId, Long imdbId) {
        if (actorId != null && imdbId != null) {
            Actors existActors = getActor(actorId);
            Movies existMovie = movieService.getMovie(imdbId);
            if (existActors == null){
                throw new RuntimeException("Actor with actorId=" + actorId + " doesn't exist");
            } else if(existMovie == null) {
                throw new RuntimeException("Movie with imdbId=" + imdbId + " doesn't exist");
            }

            if(existActors.getMovies().contains(existMovie)){
                existActors.getMovies().remove(existMovie);
            }

            if(existMovie.getActors().contains(existActors)){
                existMovie.getActors().remove(existActors);
            }

        } else{
            throw new RuntimeException("Missing requeired input parameters imdbId=" + imdbId + " or actorId=" + actorId);
        }
    }

}
