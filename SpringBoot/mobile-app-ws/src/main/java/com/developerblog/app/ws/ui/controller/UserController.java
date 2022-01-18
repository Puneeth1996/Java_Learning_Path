package com.developerblog.app.ws.ui.controller;

import com.developerblog.app.ws.service.UserService;
import com.developerblog.app.ws.shared.dto.UserDto;
import com.developerblog.app.ws.ui.modal.request.UserDetailsRequestModal;
import com.developerblog.app.ws.ui.model.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public String getUser(){
        return("get user was called");
    }

    @PostMapping
    public UserRest createUser(@RequestBody UserDetailsRequestModal userDetails){
        UserRest returnValue = new UserRest();

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return(returnValue);
    }

    @PutMapping
    public String updateUser(){
        return("update user was called");
    }

    @DeleteMapping
    public String deleteUser(){
        return("delete user was called");
    }

}
