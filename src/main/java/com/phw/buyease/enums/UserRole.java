package com.forum.api.enums;

public enum UserRole {

	ADMINISTRATOR("administrator"),
	MODERATOR("moderator"),
	REGULAR("regular"),
	GUEST("guest");

	private final String roleName;

	UserRole(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleName() {
		return roleName;
	}

}
