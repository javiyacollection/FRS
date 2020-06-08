package com.action.framework.automation.dao;

import java.util.HashMap;

import com.action.framework.automation.beans.ErrorEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public class CommonLoggingDao {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate replicaJdbcTemplate;

		public void insertLogToTable(HashMap<String, Object> entryObject) {

		String insertSql = "insert into AUTOMATION_LOGGING(API,API_TYPE," + "REQUEST," + "RESPONSE," + "STACK_TRACE,"
				+ "EXECUTION_TIME,EXECUTED_BY,REMOTE_ADDRESS)values(?,?,?,?,?,?,?,?)";
		replicaJdbcTemplate.update(insertSql, entryObject.get("API"), entryObject.get("API_TYPE"),
				entryObject.get("REQUEST") != null ? entryObject.get("REQUEST").toString() : "",
				entryObject.get("RESPONSE") != null ? entryObject.get("RESPONSE").toString() : "",
				entryObject.get("STACK_TRACE") != null ? entryObject.get("STACK_TRACE") : "",
				entryObject.get("EXECUTION_TIME"), entryObject.get("EXECUTED_BY"), entryObject.get("REMOTE_ADDRESS"));
	}

    public void insertErrorEventToTable(ErrorEvent errorEvent) {

        String insertSql = "insert into AUTOMATION_REPORT(ACTION,RULE,ERROR_CODE,ORDER_ID,STATUS)values(?,?,?,?,?)";
        replicaJdbcTemplate.update(insertSql,
                errorEvent.getRule().getActionUrl(),
                errorEvent.getRule().getRule(),
                errorEvent.getErrorCode(),
                errorEvent.getOrder().getOrderId(),
                errorEvent.getStatus() );
    }
}
