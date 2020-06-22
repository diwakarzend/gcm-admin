package com.cms.global.capital.util;

import java.time.LocalDate;

import com.cms.global.capital.dao.entity.UserEntity;
import com.cms.global.capital.dao.request.UserCreateRequestDto;
import com.cms.global.capital.dao.response.UserProfile;

import lombok.experimental.UtilityClass;

@UtilityClass
public class UserUtil {

	public static UserEntity createUserEntity(UserCreateRequestDto userRequestDto) {
		UserEntity entity = new UserEntity();
		entity.setUserName(userRequestDto.getUserName());
		entity.setEmail(userRequestDto.getEmail());
		entity.setFirstName(userRequestDto.getFirstName());
		entity.setLastName(userRequestDto.getLastName());
		entity.setPassword(userRequestDto.getPassword());
		entity.setMobile(userRequestDto.getPhone());
		entity.setStatus(userRequestDto.getStatus());
		entity.setCreatedAt(LocalDate.now());
		return entity;
	}

	public static UserProfile setProfile(UserEntity entity) {
		return UserProfile.builder().email(entity.getEmail()).firstName(entity.getFirstName())
				.lastName(entity.getLastName()).mobile(entity.getMobile()).userName(entity.getUserName()).build();
	}

}
