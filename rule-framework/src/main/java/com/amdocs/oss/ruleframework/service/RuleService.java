package com.amdocs.oss.ruleframework.service;

import com.amdocs.oss.ruleframework.beans.ErrorEvent;
import com.amdocs.oss.ruleframework.beans.Rule;
import com.amdocs.oss.ruleframework.dao.RuleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuleService {

    Logger logger= LoggerFactory.getLogger(RuleService.class);

    @Autowired
    RuleDao ruleDao;

    List<Rule> ruleList;

    public List<Rule> addRules(){

        logger.info("Adding rules");
        ruleList = new ArrayList<>();
        ruleList.add(new Rule("rule1", "FindRouteException","action/findroute",true));
        ruleList.add(new Rule("rule2", "CvLanException","action/cvlan",true));
        ruleList.add(new Rule("rule3", "ActivationException","action/activation",true));
        ruleList.add(new Rule("rule4", "NullPointerException",null,false));
            ruleList.add(new Rule("rule5", "CustomException",null,false));
        ruleDao.saveAll(ruleList);

        logger.info("rules added - " + ruleList.toString());
        return ruleList;
    }

    public ErrorEvent findRule(ErrorEvent errorEvent) {
        Rule ruleEntity = ruleDao.findByErrorCode(errorEvent.getErrorCode());
//        rule.ifPresent(ruleEntity -> {
            errorEvent.setRule(new Rule( ruleEntity.getRule(), errorEvent.getErrorCode(),ruleEntity.getActionUrl(),ruleEntity.isResponseRequired()));
//        });
        return  errorEvent;
    }
}
