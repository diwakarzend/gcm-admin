package com.cms.global.capital.dao.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserUpdateRequestDto {
	private Long id;
	private String userName;
	private String password;
	private String firstName;
	private String lastName;
	private String phone;
	private String email;
}
