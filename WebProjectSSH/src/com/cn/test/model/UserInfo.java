package com.cn.test.model;

import java.sql.Timestamp;

public class UserInfo implements java.io.Serializable {
	
	// Fields

	private Integer userid;
	private String username;
	private String userpwd;
	private Timestamp datecreate;
	private Timestamp dateupdate;

	// Constructors

	/** default constructor */
	public UserInfo() {
	}

	/** minimal constructor */
	public UserInfo(String username, String userpwd) {
		this.username = username;
		this.userpwd = userpwd;
	}

	/** full constructor */
	public UserInfo(String username, String userpwd, Timestamp datecreate,
			Timestamp dateupdate) {
		this.username = username;
		this.userpwd = userpwd;
		this.datecreate = datecreate;
		this.dateupdate = dateupdate;
	}

	// Property accessors

	public Integer getUserid() {
		return this.userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return this.userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public Timestamp getDatecreate() {
		return this.datecreate;
	}

	public void setDatecreate(Timestamp datecreate) {
		this.datecreate = datecreate;
	}

	public Timestamp getDateupdate() {
		return this.dateupdate;
	}

	public void setDateupdate(Timestamp dateupdate) {
		this.dateupdate = dateupdate;
	}


}
