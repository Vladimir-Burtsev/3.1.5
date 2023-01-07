package com.burtsev.rest_test.controller;

import com.burtsev.rest_test.model.Role;
import com.burtsev.rest_test.model.User;
import com.burtsev.rest_test.service.RoleService;
import com.burtsev.rest_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/api")
public class RestController {
    private final UserService userService;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public RestController(UserService userService, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userService = userService;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Autowired


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @GetMapping("/admins")
    public ResponseEntity<List <User>> showAllUsers() {
        try {
            return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

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

//    @PostMapping("/admins/{id}/edit")
//    public ResponseEntity<User> userEdit(@PathVariable("id") int id, @RequestBody User user) {
//        if(user.getPassword().length()!=60) {
//            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
//        }
//        Set<Role> resultList = new HashSet<>();
//        Set<Role> roleList = user.getRoles();
//        Optional<Role> roleUser = userService.findById(1);
//        Optional<Role> roleAdmin = userService.findById(2);
//        if (roleList.isEmpty() || roleList==null) {
//            resultList.add(roleUser.get());
//        } else if (roleList.get(0).getRole()=="ROLE_ADMIN"
//        roleList.iterator().next();) {
//            resultList.add(roleUser.get());
//        }
//        if (roleList.get(0).getId() == 1) {
//            resultList.add(roleUser.get());
//        } else if (roleList.get(0).getId() == 2) {
//            resultList.add(roleAdmin.get());
//        }
//        user.setRoles(resultList);
//        service.updateUser(id,user);
//        return new ResponseEntity<>(user,HttpStatus.OK);
//    }

    @PutMapping("/admins/{id}/edit")
    public ResponseEntity<User> updateUser(@RequestBody User user, @PathVariable ("id") int id) {
        try {
            userService.update(user, id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }



}
