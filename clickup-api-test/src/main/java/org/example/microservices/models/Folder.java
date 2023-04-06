package org.example.microservices.models;

public class Folder{
	private boolean access;
	private boolean hidden;
	private String name;
	private String id;

	public void setAccess(boolean access){
		this.access = access;
	}

	public boolean isAccess(){
		return access;
	}

	public void setHidden(boolean hidden){
		this.hidden = hidden;
	}

	public boolean isHidden(){
		return hidden;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(String id){
		this.id = id;
	}

	public String getId(){
		return id;
	}
}
