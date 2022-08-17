package com.alek.apiCrud.controllers;

import com.alek.apiCrud.models.User;
import com.alek.apiCrud.services.userService;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.List;

@Component
@Path("/")
public class userController {

    private final userService userservice;

    public userController(userService userservice) {
        this.userservice = userservice;
    }

    @GET
    @Path("/users")
    @Produces("application/json")
    public List<User> getUsers() {
        return userservice.getUsers();
    }

    @POST
    @Path("/users")
    @Produces("application/json")
    @Consumes("application/json")
    public Response add(User user) {
        userservice.adduser(user);
        //return Response.created(URI.create("/users/" + user.getName())).build();
        return Response.ok("Success").build();
    }

    @POST
    @Path("/editusers")
    @Produces("application/json")
    @Consumes("application/json")
    public Response editUser(User user) {
        userservice.editUser(user);
        //return Response.created(URI.create("/users/" + user.getName())).build();
        return Response.ok("Success").build();
    }

    @GET
    @Path("/users/{id}")
    @Produces("application/json")
    public User getUser(@PathParam("id") int id) {
        return userservice.getUser(id);
    }

    @POST
    @Path("/deleteusers/{id}")
    @Produces("application/json")
    @Consumes("application/json")
    public Response deleteUser(@PathParam("id") int id) {
        userservice.deleteUser(id);
        return Response.ok("User deleted succefully").build();
    }

    @POST
    @Path("/login")
    @Produces("application/json")
    @Consumes("application/json")
    public User login(User user) {
        userservice.login(user);
       // return Response.ok("Access granted").build();
        return user;
    }



}
