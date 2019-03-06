package si.test.backend.coding.challenge.rest;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.test.backend.coding.challenge.interceptors.RequestCounterInterceptor;
import si.test.backend.coding.challenge.entities.Actors;
import si.test.backend.coding.challenge.service.IActorService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@RequestScoped
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("actors")
@Interceptors(RequestCounterInterceptor.class)
public class RestActors {

    private static Logger logger = Logger.getLogger(RestActors.class.getName());

    @Context
    protected UriInfo uriInfo;

    @Inject
    private IActorService actorService;

    @GET
    public Response getAll() {
        List<Actors> Actor = actorService.getActors(createQuery());
        return Response.ok(Actor).build();
    }

    @GET
    @Path("count")
    public Response getCount() {
        Long count = actorService.getActorCount(createQuery());
        return Response.ok(count).build();
    }

    @GET
    @Path("{actorId}")
    public Response get(@PathParam("actorId") Integer actorId) {
        Actors Actor = actorService.getActor(actorId);
        return Actor != null
                ? Response.ok(Actor).build()
                : Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    public Response addNewActor(Actors actor) {
        actorService.save(actor);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{actorId}")
    public Response delete(@PathParam("actorId") Integer actorId) {
        actorService.delete(actorId);
        return Response.noContent().build();
    }

    @POST
    @Path("{actorId}/addMovie/{imdbId}")
    public Response addMovieToActor(@PathParam("actorId") Integer actorId, @PathParam("imdbId") Long imdbId) {
        actorService.addMovieToActor(actorId, imdbId);
        return Response.noContent().build();
    }

    @DELETE
    @Path("{actorId}/removeMovie/{imdbId}")
    public Response removeMovieFromActor(@PathParam("actorId") Integer actorId, @PathParam("imdbId") Long imdbId) {
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
