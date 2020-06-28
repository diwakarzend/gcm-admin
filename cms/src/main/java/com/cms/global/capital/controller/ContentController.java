package com.cms.global.capital.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cms.global.capital.dao.request.ContentCreateRequestDto;
import com.cms.global.capital.dao.response.ContentResponse;
import com.cms.global.capital.dao.response.ResponseDto;
import com.cms.global.capital.dao.response.UserAllContent;
import com.cms.global.capital.service.ContentService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping(value = "/content/")
public class ContentController {

	@Autowired
	ContentService contentService;

	@PostMapping(value = "create")
	public ResponseDto<String, Void> create(@RequestBody ContentCreateRequestDto contentCreateRequest,
			HttpServletRequest request) throws Exception {
		log.debug("request recieved to create content");
		return contentService.createContent(contentCreateRequest, request);
	}

	@GetMapping(value = "get")
	public ResponseDto<List<UserAllContent>, Void> get(HttpServletRequest request) throws Exception {
		log.debug("request recieved to create content");
		return contentService.getAllContent(request);
	}

	@GetMapping(value = "get/{id}")
	public ResponseDto<ContentResponse, Void> getByTitleId(@PathVariable("id") Long titleId, HttpServletRequest request)
			throws Exception {
		log.debug("request recieved to create content");
		return contentService.getByTitleId(titleId);
	}

	@DeleteMapping(value = "delete/{id}")
	public ResponseDto<String, Void> delete(@PathVariable("id") Long id, HttpServletRequest request) throws Exception {
		log.debug("request recieved to delete content");
		return contentService.deleteContent(request, id);
	}
}
