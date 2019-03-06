package si.test.backend.coding.challenge;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Simple JAX-RS resource.
 *
 * @author Urban Malc
 * @since 1.0.0
 */
@Path("test")
public class SimpleResource {

    @GET
    public Response test() {
        return Response.ok("hello").build();
    }
}
