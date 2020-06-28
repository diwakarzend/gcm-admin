package com.cms.global.capital.dao.request;

import com.cms.global.capital.dao.PageEnum;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Content {
	PageEnum key;
	String value;
	String fileExtension;
}
