package com.developerblog.app.ws.service;

import com.developerblog.app.ws.shared.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto createUser(UserDto user);
}
