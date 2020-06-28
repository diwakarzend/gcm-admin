package com.cms.global.capital.dao.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity(name = "title_table")
@Table(name = "title_table")
@Setter
@Getter
public class TitleEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "title_name")
	protected String titleName;

	@Column(name = "created_at")
	protected LocalDate createdAt;

	@Column(name = "user_id")
	protected Long userId;
}
