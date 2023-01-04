package com.burtsev.rest_test.controller;

import com.burtsev.rest_test.model.User;
import com.burtsev.rest_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final UserService userService;

    @Autowired
    public RestController(UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/admins")
    public List<User> showAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/users/{id}")
//    public User getOneUser(@PathVariable("id") int id) {
//        User user = userService.getUser(id);
//        if (user == null) {
//            throw new UserNotFoundException("Пользователь с id " + id + " не найден.");
//        }
//        return user;
//    }
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getOneUser(@PathVariable("id") int id) {
        try {
            return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    //    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("/admins/new")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        try {
            userService.save(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
        }
    }



//    @PostMapping("/admin/{id}/edit")
//    public ResponseEntity<HttpStatus> editUser(@PathVariable ("id")int id){
//
//    }



}
