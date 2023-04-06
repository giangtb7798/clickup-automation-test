package org.example.microservices.models;

public class ListsItem{
	private int orderindex;
	private Object dueDate;
	private Object priority;
	private String content;
	private Space space;
	private int taskCount;
	private boolean overrideStatuses;
	private boolean archived;
	private String permissionLevel;
	private Folder folder;
	private String name;
	private String id;
	private Object assignee;
	private Object status;
	private Object startDate;

	public void setOrderindex(int orderindex){
		this.orderindex = orderindex;
	}

	public int getOrderindex(){
		return orderindex;
	}

	public void setDueDate(Object dueDate){
		this.dueDate = dueDate;
	}

	public Object getDueDate(){
		return dueDate;
	}

	public void setPriority(Object priority){
		this.priority = priority;
	}

	public Object getPriority(){
		return priority;
	}

	public void setContent(String content){
		this.content = content;
	}

	public String getContent(){
		return content;
	}

	public void setSpace(Space space){
		this.space = space;
	}

	public Space getSpace(){
		return space;
	}

	public void setTaskCount(int taskCount){
		this.taskCount = taskCount;
	}

	public int getTaskCount(){
		return taskCount;
	}

	public void setOverrideStatuses(boolean overrideStatuses){
		this.overrideStatuses = overrideStatuses;
	}

	public boolean isOverrideStatuses(){
		return overrideStatuses;
	}

	public void setArchived(boolean archived){
		this.archived = archived;
	}

	public boolean isArchived(){
		return archived;
	}

	public void setPermissionLevel(String permissionLevel){
		this.permissionLevel = permissionLevel;
	}

	public String getPermissionLevel(){
		return permissionLevel;
	}

	public void setFolder(Folder folder){
		this.folder = folder;
	}

	public Folder getFolder(){
		return folder;
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

	public void setAssignee(Object assignee){
		this.assignee = assignee;
	}

	public Object getAssignee(){
		return assignee;
	}

	public void setStatus(Object status){
		this.status = status;
	}

	public Object getStatus(){
		return status;
	}

	public void setStartDate(Object startDate){
		this.startDate = startDate;
	}

	public Object getStartDate(){
		return startDate;
	}
}
