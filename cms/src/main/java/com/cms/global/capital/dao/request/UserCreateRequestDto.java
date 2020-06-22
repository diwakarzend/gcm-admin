package com.cms.global.capital.dao.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreateRequestDto {
	private String firstName;
	private String lastName;
	private String userName;
	private String email;
	private Boolean status;
	private String phone;
	private String password;
}
