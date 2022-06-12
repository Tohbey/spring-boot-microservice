package com.example.userservice.controller;

import com.example.userservice.dto.ResponseObject;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserListDto;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(UsersController.BASE_URL)
public class UsersController {

    @Autowired
    private Environment environment;


    private final UserService userService;
    public static final String BASE_URL = "/api/v1/user";

    private PasswordEncoder passwordEncoder;

    public UsersController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/status/check")
    public String status(){
        return "Working on port "+environment.getProperty("local.server.port");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getAllUsers() {
        ResponseObject object = new ResponseObject();
        try {
            List<UserDto> userList = userService.getAllUser();
            object.setData(new UserListDto(userList));
            object.setValid(true);
            object.setMessage("Resource Retrieved Successfully");
        } catch (Exception e) {
            object.setValid(false);
            object.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(object);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ResponseObject> getUserById(@PathVariable long id) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Optional<UserDto> userDTO = userService.getUser(id);
            responseObject.setData(userDTO);
            responseObject.setValid(true);
            responseObject.setMessage("Resource Retrieved Successfully");
        } catch (Exception e) {
            responseObject.setValid(false);
            responseObject.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(responseObject);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ResponseObject> insertUser(@RequestBody User user) {
        ResponseObject responseObject = new ResponseObject();
        try {
            String pwd = user.getEncryptedPassword();
            String encryptPwd = this.passwordEncoder.encode(pwd);
            user.setEncryptedPassword(encryptPwd);

            UserDto userDTO = userService.save(user);
            responseObject.setData(userDTO);
            responseObject.setValid(true);
            responseObject.setMessage("Resource Created Successfully");
        } catch (Exception e) {
            responseObject.setValid(false);
            responseObject.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return new ResponseEntity<>(responseObject, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<ResponseObject> deleteUser(@PathVariable long id) {
        ResponseObject responseObject = new ResponseObject();
        try {
            userService.delete(id);
            responseObject.setValid(true);
            responseObject.setMessage("Resource Deleted Successfully");
        } catch (Exception e) {
            responseObject.setValid(false);
            responseObject.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(responseObject);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<ResponseObject> updateUser(@PathVariable long id, @RequestBody User user) {
        ResponseObject responseObject = new ResponseObject();
        try {
            Optional<UserDto> userDTO = userService.update(user, id);
            responseObject.setData(userDTO);
            responseObject.setValid(true);
            responseObject.setMessage("Resource Updated Successfully");
        } catch (Exception e) {
            responseObject.setValid(false);
            responseObject.setMessage(e.getMessage());
            e.printStackTrace();
        }
        return ResponseEntity.ok().body(responseObject);
    }
}
