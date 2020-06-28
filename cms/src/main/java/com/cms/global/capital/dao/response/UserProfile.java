package com.cms.global.capital.dao.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserProfile {
	
	private Long userId;
	private String userName;
	private String firstName;
	private String lastName;
	private String mobile;
	private String email;
}
