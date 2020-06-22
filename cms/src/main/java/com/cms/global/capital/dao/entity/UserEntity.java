package com.cms.global.capital.dao.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "user_table")
@Table(name = "user_table")
@Setter
@Getter
@ToString(callSuper = true)
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Column(name = "first_name")
	protected String firstName;

	@Column(name = "last_name")
	protected String lastName;

	@Column(name = "user_name")
	protected String userName;

	@Column(name = "password")
	protected String password;

	@Column(name = "email")
	protected String email;

	@Column(name = "status", columnDefinition = "bit(1) default 1")
	protected Boolean status;

	@Column(name = "phone")
	protected String mobile;

	@Column(name = "created_at")
	protected LocalDate createdAt;
}
