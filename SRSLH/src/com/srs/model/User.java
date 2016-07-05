package com.srs.model;


public class User {
	private String username;
	private String password;
	private int relatedpersontype;
	private String relatedpersonno;

	public User() {
	}

	public User(String username, String password, int relatedpersontype, String relatedpersonno) {
		super();
		this.username = username;
		this.password = password;
		this.relatedpersontype = relatedpersontype;
		this.relatedpersonno = relatedpersonno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRelatedpersontype() {
		return relatedpersontype;
	}

	public void setRelatedpersontype(int relatedpersontype) {
		this.relatedpersontype = relatedpersontype;
	}

	public String getRelatedpersonno() {
		return relatedpersonno;
	}

	public void setRelatedpersonno(String relatedpersonno) {
		this.relatedpersonno = relatedpersonno;
	}

}
