package com.example.entity;

public class User extends Entity {

	private String userId;

	private String name;

	private String email;

	@Override public boolean isValid() {
		throw new RuntimeException("Not implemented yet");
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
