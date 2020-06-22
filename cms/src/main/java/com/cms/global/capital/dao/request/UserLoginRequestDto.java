package com.cms.global.capital.dao.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserLoginRequestDto {
	private String userName;
	private String password;
}
