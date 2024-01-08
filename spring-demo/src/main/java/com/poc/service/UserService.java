package com.poc.service;

import java.util.List;

import com.poc.dto.UserDto;
import com.poc.entity.User;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAllUsers();
}