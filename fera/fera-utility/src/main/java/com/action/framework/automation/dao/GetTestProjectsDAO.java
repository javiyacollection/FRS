package com.action.framework.automation.dao;

import java.util.List;

import com.action.framework.automation.beans.ErrorDaoObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.amdocs.sfo.automation.beans.SfoDaoObject;

@Transactional
@Repository
public class GetTestProjectsDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	/*@Autowired
	@Qualifier("replicaJdbcTemplate")
	private JdbcTemplate replicaJdbcTemplate;
	
	@Autowired
	@Qualifier("armJdbcTemplate")
	private JdbcTemplate armJdbcTemplate;*/
	
	/**
	 * Fetch Project which name contains "TEST" and having any activity in error state.
	 * @return
	 */
	public List<ErrorDaoObject> getTestInstances() {
		String sql = "select b.name as projectName,b.status as status , b.id as projectId , a.plan_id as planId, a.id as actId \r\n" + 
				"				 from oss_activity_instance a ,sc_project_order_instance b where   \r\n" + 
				"				  b.name like '%TEST%' and b.is_latest_version=1  and \r\n" + 
				"				 a.plan_id= b.plan_id  and  a.is_latest_version=1  and a.state='In Error'";
		
		RowMapper<ErrorDaoObject> rowMapper = new BeanPropertyRowMapper<ErrorDaoObject>(ErrorDaoObject.class);
		return this.jdbcTemplate.query(sql, rowMapper);
	}
	
	
	

}
