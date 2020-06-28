package com.cms.global.capital.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cms.global.capital.dao.entity.ContentEntity;
import com.cms.global.capital.dao.entity.TitleEntity;
import com.cms.global.capital.dao.entity.UserEntity;
import com.cms.global.capital.dao.response.ContentResponse;
import com.cms.global.capital.dao.response.UserAllContent;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ContentUtil {

	public static TitleEntity generateTitleEntity(String title, Long userId) {
		TitleEntity entity = new TitleEntity();
		entity.setTitleName(title);
		entity.setUserId(userId);
		entity.setCreatedAt(LocalDate.now());
		return entity;

	}

	public static ContentEntity generateContentUtil(String totalContent, Long userId, Long titleId) {
		ContentEntity content = new ContentEntity();
		content.setContent(totalContent);
		content.setCreatedAt(LocalDate.now());
		content.setTitleId(titleId);
		content.setUserId(userId);
		return content;
	}

	public static List<UserAllContent> convertToAllContentDto(List<TitleEntity> titleList,
			Map<Long, UserEntity> userDetailsMap) {
		List<UserAllContent> userContent = new ArrayList<UserAllContent>();
		if (!titleList.isEmpty()) {
			for (TitleEntity entity : titleList) {
				UserAllContent content = new UserAllContent();
				content.setCreatedOn(entity.getCreatedAt().toString());
				content.setTitle(entity.getTitleName());
				content.setTitleId(entity.getId());
				content.setCreatedBy(userDetailsMap.get(entity.getId()).getUserName());
				userContent.add(content);
			}
		}
		return userContent;
	}

	public static ContentResponse generateContentResponse(ContentEntity contentEntity, String finalContentString, TitleEntity titleEntity) {
		ContentResponse contentResponse = new ContentResponse();
		contentResponse.setContent(finalContentString);
		contentResponse.setCreatedOn(contentEntity.getCreatedAt().toString());
		contentResponse.setTitle(titleEntity.getTitleName());
		contentResponse.setTitleId(titleEntity.getId());
		return contentResponse;
	}
}
