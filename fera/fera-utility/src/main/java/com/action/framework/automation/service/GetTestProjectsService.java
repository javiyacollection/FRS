package com.action.framework.automation.service;

import com.action.framework.automation.beans.ErrorDaoObject;
import com.action.framework.automation.dao.GetTestProjectsDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetTestProjectsService {

	@Autowired
	GetTestProjectsDAO getTestProjects;
	
	public List<ErrorDaoObject> getTestInstances() {
		return getTestProjects.getTestInstances();
	}
}
