package com.cms.global.capital.dao.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentResponse {
	
	private String title;
	private Long titleId;
	private String content;
	private Long userId;
	private String createdOn;
	private String createdBy;
}
