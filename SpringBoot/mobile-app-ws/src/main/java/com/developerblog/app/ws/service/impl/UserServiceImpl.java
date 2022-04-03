package com.developerblog.app.ws.service.impl;

import com.developerblog.app.ws.exceptions.UserServiceException;
import com.developerblog.app.ws.io.repositories.UserRepository;
import com.developerblog.app.ws.io.entity.UserEntity;
import com.developerblog.app.ws.service.UserService;
import com.developerblog.app.ws.shared.dto.UserDto;
import com.developerblog.app.ws.shared.utils.Utils;
import com.developerblog.app.ws.ui.modal.response.ErrorMessages;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Utils utils;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDto createUser(UserDto user) {

        if( userRepository.findByEmail(user.getEmail()) != null ) throw new RuntimeException("Record Already Exists");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        String publicUserId = utils.genrateUserId(30);
        userEntity.setUserId(publicUserId);
        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getPassword()));

        UserEntity storedUserDetails = userRepository.save(userEntity);

        UserDto returnValues = new UserDto();
        BeanUtils.copyProperties(storedUserDetails, returnValues);

        return returnValues;
    }

    @Override
    public UserDto getUser(String email) {

        UserEntity  userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            throw new UsernameNotFoundException(email);
        }

        UserDto returnValue = new UserDto();

        BeanUtils.copyProperties(userEntity, returnValue);

        return returnValue;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email);

        if(userEntity == null){
            throw new UsernameNotFoundException(email);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), new ArrayList<>());
    }

    @Override
	public UserDto getUserByUserId(String id) {
		
		UserDto returnValue= new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(id);
        if(userEntity == null){
            throw new UsernameNotFoundException(id);
        }
		BeanUtils.copyProperties(userEntity, returnValue);
		
		
		return returnValue;
	}
	
	
	@Override
	public UserDto updateUser(String id, UserDto user) {

		UserDto returnValue= new UserDto();
		
		UserEntity userEntity = userRepository.findByUserId(id);
			if (userEntity == null) {
				throw new UserServiceException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
			}
			
		userEntity.setFirstName(user.getFirstName());
		userEntity.setLastName(user.getLastName());
		
		UserEntity updatedUserDetails = userRepository.save(userEntity);
		
		BeanUtils.copyProperties(updatedUserDetails, returnValue);
		
		return returnValue;
	}


}
