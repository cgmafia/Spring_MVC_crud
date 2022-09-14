package com.anandvenky.playground.services;

import com.anandvenky.playground.dto.UserDto;
import com.anandvenky.playground.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findUserByEmail(String email);

    List<UserDto> findAllUsers();
}
