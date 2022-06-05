package com.example.userservice.controller;

import com.example.userservice.dto.CreateUserRequest;
import com.example.userservice.dto.CreateUserResponse;
import com.example.userservice.dto.UserDto;
import com.example.userservice.dto.UserResponse;
import com.example.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UsersController {

    @Autowired
    private Environment environment;

    @Autowired
    UserService usersService;

    @GetMapping("/status/check")
    public String status(){
        return "Working on port "+environment.getProperty("local.server.port");
    }

    @PostMapping()
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest userDetails)
    {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDto userDto = modelMapper.map(userDetails, UserDto.class);

        UserDto createdUser = usersService.createUser(userDto);

        CreateUserResponse returnValue = modelMapper.map(createdUser, CreateUserResponse.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(returnValue);
    }

    @GetMapping(value="/{userId}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId) {

        UserDto userDto = usersService.getUserByUserId(userId);
        UserResponse returnValue = new ModelMapper().map(userDto, UserResponse.class);

        return ResponseEntity.status(HttpStatus.OK).body(returnValue);
    }
}
