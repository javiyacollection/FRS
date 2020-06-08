package com.action.framework.automation.beans;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



public class StatusReportObject {

	public StatusReportObject(String projectName, String activityID, String waStatus,
			String waComment) {
		super();
		this.projectName = projectName;
		this.activityID = activityID;
		this.waStatus = waStatus;
		this.waComment = waComment;
	}
	private String projectName;
	private String activityID;
	private String waStatus;
	private String errorID;
	private String ticketID;
	
	public String getErrorID() {
		return errorID;
	}
	public void setErrorID(String errorID) {
		this.errorID = errorID;
	}
	public String getTicketID() {
		return ticketID;
	}
	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getActivityID() {
		return activityID;
	}
	public void setActivityID(String activityID) {
		this.activityID = activityID;
	}
	public String getWaStatus() {
		return waStatus;
	}
	public void setWaStatus(String waStatus) {
		this.waStatus = waStatus;
	}
	public String getWaComment() {
		return waComment;
	}
	public void setWaComment(String waComment) {
		this.waComment = waComment;
	}
	private String waComment ;
	
}
