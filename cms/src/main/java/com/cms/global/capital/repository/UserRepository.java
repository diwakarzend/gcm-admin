package com.cms.global.capital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cms.global.capital.dao.entity.UserEntity;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>{

	UserEntity findByStatusTrueAndUserName(String userName);
	
}
