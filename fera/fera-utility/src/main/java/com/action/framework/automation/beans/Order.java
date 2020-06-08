package com.action.framework.automation.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private String orderId;
    private String actId;
    private String planId;
    private String actionType;
    private String action;
    private String flowType;
    private String serviceOrderId;
    private String projectId;
    private String projectName;
    private Map<String, String> attributeMap;



}
