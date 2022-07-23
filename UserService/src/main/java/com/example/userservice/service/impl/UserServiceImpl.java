package com.example.userservice.service.impl;

import com.example.userservice.controller.UsersController;
import com.example.userservice.dao.UserDao;
import com.example.userservice.dto.AlbumDTO;
import com.example.userservice.dto.AlbumResponse;
import com.example.userservice.dto.UserDto;
import com.example.userservice.entity.User;
import com.example.userservice.exceptions.NotFoundException;
import com.example.userservice.mapper.UserMapper;
import com.example.userservice.service.AlbumServiceClient;
import com.example.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    UserDao usersRepository;
    private UserMapper userMapper;
    private AlbumServiceClient albumServiceClient;

    final String alphabet = "0123456789ABCDE";
    final int N = alphabet.length();

    @Value("${albums.url}")
    private String albumUri;

    public UserServiceImpl(UserDao usersRepository, UserMapper userMapper, AlbumServiceClient albumServiceClient) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.albumServiceClient = albumServiceClient;
    }

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<UserDto> getAllUser() {
        List<User> users = (List<User>) this.usersRepository.findAll();
        return users.stream().map(
                        user -> {
                            UserDto userDTO = userMapper.userToUserDTO(user);
                            userDTO.setUserUrl(getUserUrl(user.getId()));
                            userDTO.setFullName(returnUserFullName(user));

                            return userDTO;
                        }
                ).collect(Collectors.toList());
    }

    @Override
    public Optional<UserDto> getUser(Long id) throws Exception {
        Optional<User> user = this.usersRepository.findById(id);
        List<AlbumDTO> albumDTOList = new ArrayList<>();

        if (user.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value " + id);
        }

        AlbumResponse response = albumServiceClient.getAlbums(String.valueOf(user.get().getId()));
        albumDTOList = response.getData();

        Optional<UserDto> userDto = user.map(userMapper::userToUserDTO)
                .map(userDTO -> {
                    userDTO.setUserUrl(getUserUrl(user.get().getId()));
                    userDTO.setFullName(returnUserFullName(user.get()));
                    return userDTO;
                });

        userDto.get().setAlbums(albumDTOList);

        return userDto;
    }

    @Override
    public UserDto save(User user) throws Exception {
        Optional<User> checkUer = this.usersRepository.findByEmail(user.getEmail());
        if (checkUer.isPresent()) {
            throw new Exception("A user with this email already exist " + checkUer.get().getEmail());
        }

        return saveAndReturnDTO(user);
    }

    @Override
    public void delete(long id) {
        this.usersRepository.deleteById(id);
    }

    @Override
    public Optional<UserDto> update(User user, long id) {
        Optional<User> currentUser = this.usersRepository.findById(id);
        if (currentUser.isEmpty()) {
            throw new NotFoundException("User Not Found. for ID value" + id);
        }

        return currentUser.map(user1 -> {
            if (user.getEmail() != null) {
                user1.setEmail(user.getEmail());
            }


            if (user.getIsActive() == 0 || user.getIsActive() == 1) {
                user1.setIsActive(user.getIsActive());
            }

            return saveAndReturnDTO(user1);
        });
    }

    private UserDto saveAndReturnDTO(User user) {
        User savedUser = this.usersRepository.save(user);

        UserDto returnDTO = userMapper.userToUserDTO(savedUser);

        returnDTO.setFullName(returnUserFullName(savedUser));
        returnDTO.setUserUrl(getUserUrl(savedUser.getId()));

        return returnDTO;
    }


    private String getUserUrl(long id) {
        return UsersController.BASE_URL + "/" + id;
    }

    private String returnUserFullName(User user) {
        return user.getLastName() + " " + user.getFirstName();
    }
}
