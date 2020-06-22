package com.cms.global.capital.service.impl;

import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cms.global.capital.dao.entity.UserEntity;
import com.cms.global.capital.dao.request.UserCreateRequestDto;
import com.cms.global.capital.dao.request.UserLoginRequestDto;
import com.cms.global.capital.dao.request.UserUpdateRequestDto;
import com.cms.global.capital.dao.response.ResponseDto;
import com.cms.global.capital.dao.response.UserProfile;
import com.cms.global.capital.repository.UserRepository;
import com.cms.global.capital.service.UserService;
import com.cms.global.capital.util.CommonUtil;
import com.cms.global.capital.util.UserUtil;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public ResponseDto<String, Void> createUser(UserCreateRequestDto userRequestDto) {
		userRequestDto.setPassword(CommonUtil.getBcryptPassword(userRequestDto.getPassword()));

		UserEntity entity = UserUtil.createUserEntity(userRequestDto);
		try {
			userRepository.save(entity);
			return ResponseDto.success("Sucessfully registered user", null);
		} catch (Exception e) {
			log.debug(e);
			return ResponseDto.failure("UserName or Email is already registered! ", null);
		}
	}

	@Override
	public ResponseDto<UserProfile, Void> userProfile(Long id) {
		try {
			UserEntity entity = userRepository.findById(id).get();
			if (Objects.isNull(entity)) {
				return ResponseDto.failure("User not found", null);
			}
			return ResponseDto.success("User profile fetched success", UserUtil.setProfile(entity));

		} catch (Exception e) {
			log.debug(e);
			return ResponseDto.failure("Somwthing went wrong", null);
		}
	}

	@Override
	public ResponseDto<String, Void> updateUser(UserUpdateRequestDto userUpdateRequestDto) throws Exception {
		Optional<UserEntity> optionalUser = userRepository.findById(userUpdateRequestDto.getId());
		if (Objects.isNull(optionalUser) || !optionalUser.isPresent()) {
			return ResponseDto.failure("User not found with this id", null);
		}
		UserEntity entity = optionalUser.get();
		if (Objects.nonNull(userUpdateRequestDto.getEmail())) {
			entity.setEmail(userUpdateRequestDto.getEmail());
		}

		if (Objects.nonNull(userUpdateRequestDto.getFirstName())) {
			entity.setFirstName(userUpdateRequestDto.getFirstName());
		}

		if (Objects.nonNull(userUpdateRequestDto.getLastName())) {
			entity.setLastName(userUpdateRequestDto.getLastName());
		}

		if (Objects.nonNull(userUpdateRequestDto.getPassword())) {
			entity.setPassword(userUpdateRequestDto.getPassword());
		}

		if (Objects.nonNull(userUpdateRequestDto.getPhone())) {
			entity.setMobile(userUpdateRequestDto.getPhone());
		}
		userRepository.save(entity);
		return ResponseDto.success("User Updated Success", null);
	}

	@Override
	public ResponseDto<String, Void> deleteUser(Long id) {
		try {
			UserEntity entity = userRepository.findById(id).get();
			if (Objects.isNull(entity)) {
				return ResponseDto.failure("User not found", null);
			}
			entity.setStatus(false);
			userRepository.save(entity);
			return ResponseDto.success("User deleted success", null);
		} catch (Exception e) {
			log.debug(e);
			return ResponseDto.failure("Something went wrong while deleting user", null);
		}
	}

	@Override
	public ResponseDto<String, Void> loginUser(UserLoginRequestDto userLoginRequestDto, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			UserEntity entity = userRepository.findByStatusTrueAndUserName(userLoginRequestDto.getUserName());

			passwordValidation(userLoginRequestDto, entity);

			HttpSession session = request.getSession();
			session.setAttribute("profile", UserUtil.setProfile(entity));

			if (Objects.nonNull(entity)) {
				return ResponseDto.success("User Authenticated", null);
			}
			return ResponseDto.failure("Username and/or password is incorrect", null);
		} catch (Exception e) {
			log.debug(e);
			return ResponseDto.failure("User is not registered ", null);
		}
	}

	private void passwordValidation(UserLoginRequestDto userLoginRequestDto, UserEntity entity) throws Exception {
		if (!CommonUtil.comparePassword(userLoginRequestDto.getPassword(), entity.getPassword())) {
			log.error("Password didn't match for user: " + userLoginRequestDto.getUserName());
			throw new Exception("Password didn't match for user: " + userLoginRequestDto.getUserName());
		}

	}

	@Override
	public ResponseDto<String, Void> logoutUser(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return ResponseDto.success("You are successfully logged out!", null);
	}

}
