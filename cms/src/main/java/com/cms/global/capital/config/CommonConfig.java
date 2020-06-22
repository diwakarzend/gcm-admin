package com.cms.global.capital.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonConfig {

	public static String bcryptSalt;

	@Value("${BCRYPT_SALT_C4}")
	public void setBaseUrl(String bcryptSalt) {
		CommonConfig.bcryptSalt = bcryptSalt;
	}
}
