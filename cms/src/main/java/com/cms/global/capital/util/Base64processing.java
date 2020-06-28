package com.cms.global.capital.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Base64processing {

	public static String saveImage(String value, String fileExtension, Long userId, Long titleId, String userName,
			String basePath) throws Exception {
		byte[] dearr = Base64.getDecoder().decode(value);
		String filePath = null;
		filePath = getFilePath(userId, titleId, userName, fileExtension, basePath);
		try {
			FileOutputStream fos = new FileOutputStream(new File(filePath));
			fos.write(dearr);
			fos.close();
			return filePath;
		} catch (FileNotFoundException ex) {
			throw new Exception("File Not Found at path" + filePath);
		} catch (IOException ex) {
			throw new Exception("Not able to write to a file on path" + filePath);
		}
	}

	private static String getFilePath(Long userId, Long titleId, String userName, String fileExtension,
			String basePath) {
		return basePath + "/" + titleId + userId + userName + "_" + LocalDate.now() + "." + fileExtension;
	}
}
