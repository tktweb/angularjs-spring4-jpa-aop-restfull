package ca.tktweb.demo.utils;

import ca.tktweb.demo.model.UserEntity;

public class SessionObject {
	
	final public static String SESSION_KEY="SESSION_KEY";
	
	private UserEntity user;

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}
