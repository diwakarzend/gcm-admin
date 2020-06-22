package com.cms.global.capital.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cms.global.capital.dao.request.UserCreateRequestDto;
import com.cms.global.capital.dao.request.UserLoginRequestDto;
import com.cms.global.capital.dao.request.UserUpdateRequestDto;
import com.cms.global.capital.dao.response.ResponseDto;
import com.cms.global.capital.dao.response.UserProfile;

public interface UserService {

	ResponseDto<String, Void> createUser(UserCreateRequestDto userRequestDto);

	ResponseDto<String, Void> loginUser(UserLoginRequestDto userLoginRequestDto, HttpServletRequest request,
			HttpServletResponse response);

	ResponseDto<String, Void> logoutUser(HttpServletRequest request, HttpServletResponse response);

	ResponseDto<String, Void> updateUser(UserUpdateRequestDto userUpdateRequestDto) throws Exception;

	ResponseDto<String, Void> deleteUser(Long id);

	ResponseDto<UserProfile, Void> userProfile(Long id);

}
