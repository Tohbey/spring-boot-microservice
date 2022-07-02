package com.example.userservice.controller;

import com.example.userservice.dto.ResponseObject;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserListDto;
import com.example.userservice.entity.User;
import com.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(UsersController.BASE_URL)
public class UsersController {

    @Autowired
    private Environment environment;

    @Value("${local.server.port}")
    private String port;

    private final UserService userService;
    public static final String BASE_URL = "/api/v1/user";

    private PasswordEncoder passwordEncoder;

    public UsersController(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }


    @GetMapping("/status/check")
    public String status(){
        return "Working on port "+port;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ResponseObject<UserListDto>> getAllUsers() {
        ResponseObject<UserListDto> object = new ResponseObject<>();
        try {
            List<UserDto> userList = userService.getAllUser();
            object.setData(Collections.singletonList(new UserListDto(userList)));
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
    public ResponseEntity<ResponseObject<Optional<UserDto>>> getUserById(@PathVariable long id) {
        ResponseObject<Optional<UserDto>> responseObject = new ResponseObject<>();
        try {
            Optional<UserDto> userDTO = userService.getUser(id);
            responseObject.setData(Collections.singletonList(userDTO));
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
    public ResponseEntity<ResponseObject<UserDto>> insertUser(@RequestBody User user) {
        ResponseObject<UserDto> responseObject = new ResponseObject<>();
        try {
            String pwd = user.getEncryptedPassword();
            String encryptPwd = this.passwordEncoder.encode(pwd);
            user.setEncryptedPassword(encryptPwd);

            UserDto userDTO = userService.save(user);
            responseObject.setData(Collections.singletonList(userDTO));
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
    public ResponseEntity<ResponseObject<String>> deleteUser(@PathVariable long id) {
        ResponseObject<String> responseObject = new ResponseObject<>();
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
    public ResponseEntity<ResponseObject<Optional<UserDto>>> updateUser(@PathVariable long id, @RequestBody User user) {
        ResponseObject<Optional<UserDto>> responseObject = new ResponseObject<Optional<UserDto>>();
        try {
            Optional<UserDto> userDTO = userService.update(user, id);
            responseObject.setData(Collections.singletonList(userDTO));
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
