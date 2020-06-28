package com.cms.global.capital.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.cms.global.capital.dao.PageEnum;
import com.cms.global.capital.dao.entity.ContentEntity;
import com.cms.global.capital.dao.entity.TitleEntity;
import com.cms.global.capital.dao.entity.UserEntity;
import com.cms.global.capital.dao.request.Content;
import com.cms.global.capital.dao.request.ContentCreateRequestDto;
import com.cms.global.capital.dao.response.ContentResponse;
import com.cms.global.capital.dao.response.ResponseDto;
import com.cms.global.capital.dao.response.UserAllContent;
import com.cms.global.capital.dao.response.UserProfile;
import com.cms.global.capital.repository.ContentRepository;
import com.cms.global.capital.repository.TitleRepository;
import com.cms.global.capital.repository.UserRepository;
import com.cms.global.capital.service.ContentService;
import com.cms.global.capital.util.Base64processing;
import com.cms.global.capital.util.ContentUtil;

@Service
public class ContentServiceImpl implements ContentService {

	@Autowired
	private TitleRepository titleRepository;

	@Autowired
	private ContentRepository contentRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${image.path}")
	public String basePath;

	@Override
	public ResponseDto<String, Void> createContent(ContentCreateRequestDto contentCreateRequest,
			HttpServletRequest request) throws Exception {

		HttpSession session = request.getSession();
		UserProfile userProfile = (UserProfile) session.getAttribute("profile");
		if (Objects.isNull(userProfile)) {
			return ResponseDto.failure("User session Expired!! Please login again", null);
		}
		TitleEntity titleEntity = ContentUtil.generateTitleEntity(contentCreateRequest.getTitle(),
				userProfile.getUserId());
		TitleEntity savedTitle = titleRepository.save(titleEntity);
		String totalContent = saveContent(contentCreateRequest.getContent(), userProfile.getUserId(),
				savedTitle.getId(), userProfile.getUserName());
		ContentEntity contentEntity = ContentUtil.generateContentUtil(totalContent, userProfile.getUserId(),
				savedTitle.getId());
		contentRepository.save(contentEntity);
		return ResponseDto.success("Saved user content", null);
	}

	private String saveContent(List<Content> content, Long userId, Long titleId, String userName) throws Exception {
		String s = new String();
		for (Content c : content) {
			if (c.getKey().equals(PageEnum.TITLE)) {
				s = s + c.getValue();
			} else {
				String imagePath = Base64processing.saveImage(c.getValue(), c.getFileExtension(), userId, titleId,
						userName, basePath);
				s = s + "###-image\"" + imagePath + "\"image-###";
			}
		}
		return s;
	}

	@Override
	public ResponseDto<List<UserAllContent>, Void> getAllContent(HttpServletRequest request) {
		List<TitleEntity> titleList = titleRepository.findAll();
		Map<Long, UserEntity> userDetailsMap = new HashMap<Long, UserEntity>();
		if (!titleList.isEmpty()) {
			List<Long> userIdList = titleList.stream().map(TitleEntity::getUserId).collect(Collectors.toList());
			List<UserEntity> userDetails = userRepository.findAllById(userIdList);
			userDetailsMap = userDetails.stream()
					.collect(Collectors.toMap(UserEntity::getId, UserEntity -> UserEntity));
		}
		return ResponseDto.success("Content Fetched", ContentUtil.convertToAllContentDto(titleList, userDetailsMap));
	}

	@Override
	public ResponseDto<String, Void> deleteContent(HttpServletRequest request, Long id) {
		try {
			Optional<TitleEntity> title = titleRepository.findById(id);
			if (title.isPresent()) {
				TitleEntity titleEntity = title.get();
				titleRepository.delete(titleEntity);
				ContentEntity contentEntity = contentRepository.findByTitleId(titleEntity.getId());
				contentRepository.delete(contentEntity);
				return ResponseDto.success("deleted success", null);
			} else
				return ResponseDto.failure("content not found", null);
		} catch (Exception e) {
			ResponseDto.failure("Something went Wrong", null);
		}
		return ResponseDto.failure("Something went Wrong", null);
	}

	@Override
	public ResponseDto<ContentResponse, Void> getByTitleId(Long titleId) {
		try {
			Optional<TitleEntity> titleEntity = titleRepository.findById(titleId);
			if (titleEntity.isPresent()) {
				ContentEntity contentEntity = contentRepository.findByTitleId(titleId);
				String str = contentEntity.getContent();
				String newContent = str.replaceAll("###-image","<p><img src=");
				String finalContentString = newContent.replaceAll("image-###", "</p>");

				return ResponseDto.success("content fetched success",
						ContentUtil.generateContentResponse(contentEntity,finalContentString, titleEntity.get()));
			} else {
				return ResponseDto.failure("content not found", null);
			}
		} catch (Exception e) {
			return ResponseDto.failure(e.getMessage(), null);
		}
	}

}
