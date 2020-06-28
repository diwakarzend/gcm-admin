package com.cms.global.capital.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cms.global.capital.dao.entity.TitleEntity;

public interface TitleRepository extends JpaRepository<TitleEntity, Long>{

}
