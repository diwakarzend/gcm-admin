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
import lombok.ToString;

@Entity(name = "content_table")
@Table(name = "content_table")
@Setter
@Getter
@ToString(callSuper = true)
public class ContentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "content")
	protected String content;

	@Column(name = "user_id")
	protected Long userId;

	@Column(name = "title_id")
	protected Long titleId;

	@Column(name = "created_at")
	protected LocalDate createdAt;
}
