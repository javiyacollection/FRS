package com.action.framework.automation.beans;

import java.util.List;

public class ContextStoreResponse {
	
	String orderId;
	List<String> omsIdList;
	List<String> attributeList;
	String attributeValue;
	String piid;
	
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public void setOmsIdList(List<String> omsIdList) {
		this.omsIdList = omsIdList;
	}
	public void setAttributeList(List<String> attributeList) {
		this.attributeList = attributeList;
	}
	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}
	public String getOrderId() {
		return orderId;
	}
	public List<String> getOmsIdList() {
		return omsIdList;
	}
	public List<String> getAttributeList() {
		return attributeList;
	}
	public String getAttributeValue() {
		return attributeValue;
	}
	public String getPiid() {
		return piid;
	}
	public void setPiid(String piid) {
		this.piid = piid;
	}
	
	
	
	
	
}
