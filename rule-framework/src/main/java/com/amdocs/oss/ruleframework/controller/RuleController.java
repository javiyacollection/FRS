package com.amdocs.oss.ruleframework.controller;

import com.amdocs.oss.ruleframework.beans.ErrorEvent;
import com.amdocs.oss.ruleframework.service.RuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.QueryParam;

@RestController
@RequestMapping("/rule")
@RefreshScope
public class RuleController {

    Logger logger = LoggerFactory.getLogger(RestController.class);

    @Autowired
    MessageChannel output;

    @Autowired
    RuleService ruleService;

    @Autowired
    @Lazy
    private RestTemplate restTemplate;

    @Value("${microservice.fera.endpoints.endpoint.uri}")
    private String FERA_ACTION_ENDPOINT_URL;

    @RequestMapping(value = "/stream", method = RequestMethod.POST)
    public ErrorEvent publishError (@RequestBody ErrorEvent errorEvent){
        output.send(MessageBuilder.withPayload(errorEvent).build());
        return errorEvent;
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public void loadRules(){
        ruleService.addRules();
    }

    @RequestMapping(value = "/event", method = RequestMethod.POST)
    public ErrorEvent getDetails (@RequestBody ErrorEvent errorEvent){
        logger.info("finding Rule" + errorEvent.toString());
        errorEvent = ruleService.findRule(errorEvent);
        if(errorEvent.getOrder() == null || (errorEvent.getOrder() != null && errorEvent.getOrder().getOrderId() == null) ){
            logger.error("OrderId is not present in the request");
            errorEvent.setErrorMessage("OrderId is not present in the request");
            errorEvent.setStatus("FAILURE");
            return  errorEvent;
        }

        if(errorEvent.getRule() != null){
            logger.info("Rule: " + errorEvent.getRule().toString());
            if(errorEvent.getRule().isResponseRequired()){
                // rest call
                HttpHeaders headers = new HttpHeaders();
                headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

                errorEvent = restTemplate.postForObject(FERA_ACTION_ENDPOINT_URL, errorEvent, ErrorEvent.class,headers);

            }else{
                //kafka streaming

                output.send(MessageBuilder.withPayload(errorEvent).build());
                errorEvent.setStatus("SUCCESS");
            }
        }else{
            logger.error("there is no matching rules");
            errorEvent.setStatus("FAILURE");
        }
        return errorEvent;
    }

}
