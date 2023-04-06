package org.example.microservices.models;

import java.util.List;

public class Response{
	private List<ListsItem> lists;

	public void setLists(List<ListsItem> lists){
		this.lists = lists;
	}

	public List<ListsItem> getLists(){
		return lists;
	}
}