package com.cms.global.capital.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.global.capital.dao.request.UserCreateRequestDto;
import com.cms.global.capital.dao.request.UserLoginRequestDto;
import com.cms.global.capital.dao.request.UserUpdateRequestDto;
import com.cms.global.capital.dao.response.ResponseDto;
import com.cms.global.capital.dao.response.UserProfile;
import com.cms.global.capital.service.UserService;

@RestController
@RequestMapping(value = "/user/")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping(value = "create")
	public ResponseDto<String, Void> create(@RequestBody UserCreateRequestDto userCreateRequestDto) {
		return userService.createUser(userCreateRequestDto);
	}

	@GetMapping(value = "get/{id}")
	public ResponseDto<UserProfile, Void> getUserDetails(@PathVariable("id") Long id) throws Exception {
		return userService.userProfile(id);
	}

	@PutMapping(value = "update")
	public ResponseDto<String, Void> update(@RequestBody UserUpdateRequestDto userUpdateRequestDto) throws Exception {
		return userService.updateUser(userUpdateRequestDto);
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseDto<String, Void> delete(@PathVariable("id") Long id) throws Exception {
		return userService.deleteUser(id);
	}

	@PostMapping(value = "login")
	public ResponseDto<String, Void> login(@RequestBody UserLoginRequestDto userLoginRequestDto,
			HttpServletRequest request, HttpServletResponse response) {
		return userService.loginUser(userLoginRequestDto, request, response);
	}

	@PostMapping(value = "logout")
	public ResponseDto<String, Void> logout(HttpServletRequest request, HttpServletResponse response) {
		return userService.logoutUser(request, response);
	}

}
