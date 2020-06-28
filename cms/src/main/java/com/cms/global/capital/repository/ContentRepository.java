package com.cms.global.capital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.global.capital.dao.entity.ContentEntity;

public interface ContentRepository extends JpaRepository<ContentEntity, Long> {

	ContentEntity findByTitleId(Long id);

}
