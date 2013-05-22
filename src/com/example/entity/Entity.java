package com.example.entity;

import java.util.Date;

public abstract class Entity {

	private Date creationTime;

	private Date modificationTime;

	public Entity() {
	}

	public Entity(Date creationTime, Date modificationTime) {
		super();
		this.creationTime = creationTime;
		this.modificationTime = modificationTime;
	}

	public abstract boolean isValid();

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

}
