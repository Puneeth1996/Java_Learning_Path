package com.developerblog.app.ws.service.impl;

import com.developerblog.app.ws.UserRepository;
import com.developerblog.app.ws.io.entity.UserEntity;
import com.developerblog.app.ws.service.UserService;
import com.developerblog.app.ws.shared.dto.UserDto;
import com.developerblog.app.ws.shared.utils.Utils;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Override
    public UserDto createUser(UserDto user) {

        if( userRepository.findByEmail(user.getEmail()) != null ) throw new RuntimeException("Record Alredy Exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.genrateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword("test");

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValues = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValues);

        return returnValues;
    }
}
