package com.amdocs.oss.ruleframework.dao;

import com.amdocs.oss.ruleframework.beans.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleDao extends JpaRepository<Rule,String> {
    Rule findByErrorCode(String errorCode);
}
