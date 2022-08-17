package com.alek.apiCrud.services;

import com.alek.apiCrud.models.User;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class userService {
    private final List<User> users = new ArrayList<>();

    public List<User> getUsers() {
        return users;
    }
    public void adduser(User user) {
        users.add(user);
    }
    public User getUser(int id){
        for(User user : users){
            if(id == user.getId()){
                return user;
            }
        };
        return null;
    }
    public User deleteUser(int id){
        for(User user : users){
            if(id == user.getId()){
                users.remove(user);
                return user;
            }
        };
        return null;
    }
    public Response editUser(User user){
        List<User> found = this.users.stream().filter(
                x -> user.getId() == x.getId()).collect(Collectors.toList());

        //Throws error in case of the user not found
        if(found.isEmpty())
            return Response.status(Status.BAD_REQUEST).entity("User not found").build();

        User updateUser = found.get(0);
        updateUser.setName(user.getName());
        updateUser.setFirstname(user.getFirstname());
        updateUser.setLastname(user.getLastname());
        updateUser.setEmail(user.getEmail());
        updateUser.setPassword(user.getPassword());
        updateUser.setAge(user.getAge());
        return Response.ok(updateUser).build();

    }

    public String login(User user){
        List<User> login = this.users.stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(user.getEmail()))
                .filter(x -> x.getPassword().equalsIgnoreCase(user.getPassword()))
                .collect(Collectors.toList());

        if(login.isEmpty()){
            //return Response.status(Status.BAD_REQUEST).entity("Email not found").build();
            return "Email not found";
        }
        return login.toString();

    }

}
