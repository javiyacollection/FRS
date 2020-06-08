package com.action.framework.automation.service;

import java.util.HashMap;

import com.action.framework.automation.beans.ErrorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.action.framework.automation.dao.CommonLoggingDao;

/**
 * pranand
 */
@Component
public class CommonLoggingService {

	@Autowired
	CommonLoggingDao commonLoggingDao;

	public void insertLogToTable(HashMap<String, Object> entryObject)
	{
		commonLoggingDao.insertLogToTable(entryObject);
		
	}

	public void insertErrorEventToTable(ErrorEvent errorEvent)
	{
		commonLoggingDao.insertErrorEventToTable(errorEvent);

	}

}
