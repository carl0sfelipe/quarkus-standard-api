package SharePrint.controllers;

import SharePrint.models.User;
import SharePrint.service.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserController {

    @Inject
    UserService userService;

    @GET
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GET
    @Path("/{id}")
    public User getUserById(@PathParam("id") Long id) {
        return userService.getUserById(id);
    }

    @POST
    public Response createUser(User user) {
        userService.createUser(user);
        return Response.status(Response.Status.CREATED).entity(user).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateUser(@PathParam("id") Long id, User user) {
        User updatedUser = userService.updateUser(user);
        if (updatedUser == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        } else {
            return Response.ok(updatedUser).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        userService.deleteUser(id);
        return Response.noContent().build();
    }
}
