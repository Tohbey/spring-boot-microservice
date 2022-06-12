package com.example.userservice.service;

import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService{
    List<UserDto> getAllUser();

    Optional<UserDto> getUser(Long id);

    UserDto save(User user) throws Exception;

    void delete(long id);

    Optional<UserDto> update(User user, long id);

}
