package com.cms.global.capital.dao.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentCreateRequestDto {
	private String title;
	private List<Content> content;
}
