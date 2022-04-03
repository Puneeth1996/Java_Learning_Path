package com.developerblog.app.ws.ui.controller;

import com.developerblog.app.ws.exceptions.UserServiceException;
import com.developerblog.app.ws.service.UserService;
import com.developerblog.app.ws.shared.dto.UserDto;
import com.developerblog.app.ws.ui.modal.request.UserDetailsRequestModal;
import com.developerblog.app.ws.ui.modal.response.ErrorMessages;
import com.developerblog.app.ws.ui.modal.response.UserRest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")  // http://localhost:8080/users
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping(path="/{id}",
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest getUser(@PathVariable String id) {
		
		UserRest returnValue = new UserRest();		
		
		UserDto userDto = userService.getUserByUserId(id);
		BeanUtils.copyProperties(userDto, returnValue);
		
		return returnValue;
	}
	

	@PostMapping(
			consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
			produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    public UserRest createUser(@RequestBody UserDetailsRequestModal userDetails) throws Exception {
        UserRest returnValue = new UserRest();

        if(userDetails.getFirstName().isEmpty()) throw new NullPointerException("The object is hardcoded null . . .");

        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userDetails, userDto);

        UserDto createdUser = userService.createUser(userDto);
        BeanUtils.copyProperties(createdUser, returnValue);

        return(returnValue);
    }

	@PutMapping(path="/{id}",
		consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
		produces = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
	public UserRest updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModal userDetails) {
		
		UserRest returnValue = new UserRest();
		
		UserDto userDto = new UserDto();
		BeanUtils.copyProperties(userDetails, userDto);
		
		UserDto updatedUser = userService.updateUser(id, userDto);
		
		BeanUtils.copyProperties(updatedUser, returnValue);
		
		return returnValue;
	}

    @DeleteMapping
    public String deleteUser(){
        return("delete user was called");
    }

}
