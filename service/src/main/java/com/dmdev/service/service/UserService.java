package com.dmdev.service.service;

import com.dmdev.common.common.UserRepository;
import com.dmdev.common.dto.UserDto;

import java.util.Optional;

public class UserService {

    private final UserRepository userRepository = new UserRepository();

    public Optional<UserDto> findById(Long id){
        return userRepository.findById(2L)
                .map(it -> new UserDto());
    }
}
