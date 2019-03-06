package si.test.backend.coding.challenge.rest;

import com.kumuluz.ee.rest.beans.QueryParameters;

import si.test.backend.coding.challenge.interceptors.RequestCounterInterceptor;
import si.test.backend.coding.challenge.entities.Movies;
import si.test.backend.coding.challenge.service.IMovieService;
import si.test.backend.coding.challenge.service.impl.ActorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * REST operations to support basic UI requirements: • list all movies • list movies with pagination support • search of movie • CRUD operations
 *
 * */

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("movies")
@Interceptors(RequestCounterInterceptor.class)
public class RestMovies {

    private static Logger logger = Logger.getLogger(RestMovies.class.getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    private IMovieService movieService;

    @Inject
    private ActorService actorService;

    @GET
    public Response getAll() {
        logger.info("getAll()");
        List<Movies> movies = movieService.getMovies(createQuery());
        return Response.ok(movies).build();
    }

    @GET
    @Path("count")
    public Response getCount() {
        logger.info("getCount()");
        Long count = movieService.getMoviesCount(createQuery());
        return Response.ok(count).build();
    }

    @GET
    @Path("{imdbId}")
    public Response get(@PathParam("imdbId") Long imdbId) {
        logger.info("get(imdbId=" + imdbId +")");
        Movies movie = movieService.getMovie(imdbId);
        return movie != null
                ? Response.ok(movie).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewMovie(Movies movie) {
        logger.info("addNewMovie(movie=" + movie +")");
        movieService.save(movie);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{imdbId}")
    public Response delete(@PathParam("imdbId") Long imdbId) {
        logger.info("delete(imdbId=" + imdbId +")");
        movieService.delete(imdbId);
        return Response.noContent().build();
    }

    @POST
    @Path("{imdbId}/addActor/{actorId}")
    public Response addActorToMovie(@PathParam("imdbId") Long imdbId, @PathParam("actorId") Integer actorId) {
        logger.log(Level.INFO,"addActorToMovie(actorId={0}, imdbId={1})", new Object[]{actorId, imdbId});
        actorService.addMovieToActor(actorId, imdbId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{imdbId}/removeActor/{actorId}")
    public Response removeActorFromMovie(@PathParam("imdbId") Long imdbId, @PathParam("actorId") Integer actorId) {
        logger.log(Level.INFO,"removeActorFromMovie(actorId={0}, imdbId={1})", new Object[]{actorId, imdbId});
        actorService.removeMovieFromActor(actorId, imdbId);
        return Response.noContent().build();
    }

    /**
     * Helper method for parsing query parameters from uri.
     *
     * @return query parameters
     */
    private QueryParameters createQuery() {
        return QueryParameters.query(uriInfo.getRequestUri().getQuery()).defaultOffset(0).defaultLimit(10).build();
    }
}
