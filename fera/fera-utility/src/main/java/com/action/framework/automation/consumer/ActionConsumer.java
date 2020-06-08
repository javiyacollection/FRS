package com.action.framework.automation.consumer;


import com.action.framework.automation.beans.ErrorEvent;
import com.action.framework.automation.service.CommonLoggingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.stereotype.Component;

@Component
public class ActionConsumer {

    private Logger logger = LoggerFactory.getLogger(ActionConsumer.class);

    @Autowired
    CommonLoggingService commonLoggingService;

    @StreamListener("input")
    public void consumeErrorEvent(ErrorEvent errorEvent) throws InterruptedException {
        logger.info("Event consumed" + errorEvent.toString());

        String errorCode = errorEvent.getRule().getErrorCode();
        if(errorCode.equalsIgnoreCase("NullPointerException")){

            logger.info("fixing NullPointerException related issue");
            Thread.sleep(5000);
        }else if(errorCode.equalsIgnoreCase("CustomException")){

            logger.info("fixing CustomException related issue in 3 steps");
            logger.info("fixing issue 1");
            Thread.sleep(5000);
            logger.info("fixing issue 2");
            Thread.sleep(2000);
            logger.info("fixing issue 3");
            Thread.sleep(3000);
        }
        errorEvent.setStatus("SUCCESS");
        commonLoggingService.insertErrorEventToTable(errorEvent);
    }
}
