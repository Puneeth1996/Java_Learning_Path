package com.developerblog.app.ws.service;

import com.developerblog.app.ws.shared.dto.UserDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends UserDetailsService {
    UserDto createUser(UserDto user);
    UserDto getUser(String email);
	UserDto getUserByUserId(String id);
	UserDto updateUser(String id, UserDto user);
	void deleteUser(String userId);
}
