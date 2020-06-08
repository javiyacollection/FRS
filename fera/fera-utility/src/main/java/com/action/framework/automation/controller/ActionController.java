package com.action.framework.automation.controller;

import com.action.framework.automation.beans.ErrorEvent;
import com.action.framework.automation.exception.ErrorRestException;
import com.action.framework.automation.service.CommonLoggingService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/action")
public class ActionController {

	/**
	 * This is just simple endpoint to test application.where provided orderId will
	 * be printed as it is.
	 * 
	 * @param orderId
	 * @return
	 */

	private static final Logger logger = LoggerFactory.getLogger(ActionController.class);


	@Autowired
    CommonLoggingService commonLoggingService;

	@ApiOperation(value = "Error Event",
			notes = "To get event from rule engine ")
	@RequestMapping(value = "/event", method = RequestMethod.POST)
	public ResponseEntity<ErrorEvent> actionEvent(@RequestBody ErrorEvent errorEvent)
			throws ErrorRestException {
		try {

			logger.info("received event for action : " + errorEvent);
			String actionUrl = errorEvent.getRule().getActionUrl();
			if(actionUrl.equalsIgnoreCase("action/findroute")){
				findRouteController(errorEvent);
			}else if(actionUrl.equalsIgnoreCase("action/cvlan")){
				cvLanController(errorEvent);
			}else if (actionUrl.equalsIgnoreCase("action/activation")){
				activationController(errorEvent);
			}else {
				errorEvent.setErrorMessage("no defined action");
				errorEvent.setStatus("FAILURE");
			}
			return new ResponseEntity<ErrorEvent>(errorEvent, HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorRestException(e.getMessage());
		}
	}

	@ApiOperation(value = "Error Event",
		    notes = "To fix find route related issue. ")
	@RequestMapping(value = "/findroute", method = RequestMethod.POST)
	public ResponseEntity<ErrorEvent> findRouteController(@RequestBody ErrorEvent errorEvent)
			throws ErrorRestException {
		try {
				if(errorEvent.getOrder() == null || (errorEvent.getOrder() != null && errorEvent.getOrder().getOrderId() == null) ){
					throw new ErrorRestException("OrderId is not present in the request");
				}

				logger.info("To fix find route related issue.");
			errorEvent.setStatus("SUCCESS");
			commonLoggingService.insertErrorEventToTable(errorEvent);
			return new ResponseEntity<ErrorEvent>(errorEvent, HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorRestException(e.getMessage());
		}
	}

	@ApiOperation(value = "Error Event",
			notes = "To fix cvLan related issue. ")
	@RequestMapping(value = "/cvlan", method = RequestMethod.POST)
	public ResponseEntity<ErrorEvent> cvLanController(@RequestBody ErrorEvent errorEvent)
			throws ErrorRestException {
		try {
			if(errorEvent.getOrder() == null || (errorEvent.getOrder() != null && errorEvent.getOrder().getOrderId() == null) ){
				throw new ErrorRestException("OrderId is not present in the request");
			}

			logger.info("To fix cvLan related issue.");
			errorEvent.setStatus("SUCCESS");
			commonLoggingService.insertErrorEventToTable(errorEvent);
			return new ResponseEntity<ErrorEvent>(errorEvent, HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorRestException(e.getMessage());
		}
	}

	@ApiOperation(value = "Error Event",
			notes = "To fix activation related issue. ")
	@RequestMapping(value = "/activation", method = RequestMethod.POST)
	public ResponseEntity<ErrorEvent> activationController(@RequestBody ErrorEvent errorEvent)
			throws ErrorRestException {
		try {
			if(errorEvent.getOrder() == null || (errorEvent.getOrder() != null && errorEvent.getOrder().getOrderId() == null) ){
				throw new ErrorRestException("OrderId is not present in the request");
			}
			logger.info("To fix activation related issue.");
			errorEvent.setStatus("SUCCESS");
			commonLoggingService.insertErrorEventToTable(errorEvent);
			return new ResponseEntity<ErrorEvent>(errorEvent, HttpStatus.OK);
		} catch (Exception e) {
			throw new ErrorRestException(e.getMessage());
		}
	}
}
