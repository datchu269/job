package com.example.customvalidation.entity;

import lombok.Data;

@Data
public class ActionRequest {
	public int actionIdentifier;
	public Create create;
	public Get get;
	public List list;
	public Update update;

	public ActionRequest(int actionIdentifier, Create create, Get get, List list, Update update) {
		this.actionIdentifier = actionIdentifier;
		this.create = create;
		this.get = get;
		this.list = list;
		this.update = update;
	}
}
