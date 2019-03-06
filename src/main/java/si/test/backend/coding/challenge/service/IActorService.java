package si.test.backend.coding.challenge.service;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.test.backend.coding.challenge.entities.Actors;

import java.util.List;

public interface IActorService {

    Actors getActor(Integer actorId);

    List<Actors> getActors(QueryParameters query);

    Long getActorCount(QueryParameters query);
    void save(Actors actor);

    void delete(Integer actorId);

    void addMovieToActor(Integer actorId, Long imdbId);

    void removeMovieFromActor(Integer actorId, Long imdbId);
}
