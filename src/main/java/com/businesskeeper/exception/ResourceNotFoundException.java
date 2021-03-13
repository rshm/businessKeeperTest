package com.businesskeeper.exception;

public class ResourceNotFoundException extends Exception{

	int status;
	String title;
	String description;
	public ResourceNotFoundException(int status, String title, String description) {
		super(description);
		this.status = status;
		this.title = title;
		this.description = description;

	}

	public ResourceNotFoundException(){}

	@Override
	public String toString() {
		return "ResourceNotFoundException{" +
				"status=" + status +
				", title='" + title + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
