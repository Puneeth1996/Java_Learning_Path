package com.developerblog.app.ws.service;

import com.developerblog.app.ws.UserRepository;
import com.developerblog.app.ws.io.entity.UserEntity;
import com.developerblog.app.ws.shared.dto.UserDto;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) {

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        userEntity.setEncryptedPassword("test");
        userEntity.setUserId("testUserId");

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValues = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValues);

        return returnValues;
    }
}
