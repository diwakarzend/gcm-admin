package com.cms.global.capital.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.cms.global.capital.dao.request.ContentCreateRequestDto;
import com.cms.global.capital.dao.response.ContentResponse;
import com.cms.global.capital.dao.response.ResponseDto;
import com.cms.global.capital.dao.response.UserAllContent;

public interface ContentService {

	ResponseDto<String, Void> createContent(ContentCreateRequestDto contentCreateRequest, HttpServletRequest request) throws Exception;

	ResponseDto<List<UserAllContent>, Void> getAllContent(HttpServletRequest request);

	ResponseDto<String, Void> deleteContent(HttpServletRequest request, Long id);

	ResponseDto<ContentResponse, Void> getByTitleId(Long titleId);

}
