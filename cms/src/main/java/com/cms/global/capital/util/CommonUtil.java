package com.cms.global.capital.util;

import org.mindrot.jbcrypt.BCrypt;

import com.cms.global.capital.config.CommonConfig;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CommonUtil {

	public static String getBcryptPassword(String password) {
		return BCrypt.hashpw(password, CommonConfig.bcryptSalt);
	}

	public static boolean comparePassword(String requestedPassword, String originalPassword) {
		return BCrypt.checkpw(requestedPassword, originalPassword);
	}
}
