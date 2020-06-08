package com.action.framework.automation.aop;

import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.action.framework.automation.exception.ErrorRestException;
import com.action.framework.automation.service.CommonLoggingService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;



@Aspect
@Component
public class RestApiAccess {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	@Autowired
	private CommonLoggingService commonLoggingService;

//	@Before("@annotation(org.springframework.web.bind.annotation.RequestMapping) && within(com.amdocs.sfo.automation.controller.*)")
	public void verifyCredential(JoinPoint joinPoint) throws ErrorRestException {
		logger.info("Before running loggingAdvice on method=" + joinPoint.toString());

		logger.info("Agruments Passed=" + Arrays.toString(joinPoint.getArgs()));
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		String userName = request.getHeader("username");
		String password = request.getHeader("password");

		if (userName == null || userName.isEmpty() || password == null || password.isEmpty()) {
			throw new ErrorRestException("Please provide username and password in header");
		} else {
			logger.info("username: " + userName);
			try {
				 // verifyCredentials(userName, password);
			} catch (Exception e) {
				logger.error("Combination of userName and password is wrong." + e.getStackTrace());
				throw new ErrorRestException("Combination of username and password is wrong.");
			}

		}

	}

	@Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) && within(com.amdocs.sfo.automation.controller.*)")
	public Object log(final ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
				.getRequest();

		Object value;
		HashMap<String, Object> entryObject = new HashMap<>();
		long startTime = System.currentTimeMillis();
		try {

			String userName = request.getHeader("username");
			entryObject.put("API_TYPE", request.getMethod());
			entryObject.put("REMOTE_ADDRESS", request.getRemoteAddr());
			entryObject.put("EXECUTED_BY", userName);
			entryObject.put("API", request.getRequestURI());
			entryObject.put("REQUEST", Arrays.toString(proceedingJoinPoint.getArgs()));
			value = proceedingJoinPoint.proceed();
			long endTime = System.currentTimeMillis();
			entryObject.put("RESPONSE", value);
			entryObject.put("EXECUTION_TIME", endTime - startTime);

		} catch (Throwable throwable) {

			long endTime = System.currentTimeMillis();
			entryObject.put("EXECUTION_TIME", endTime - startTime);
			entryObject.put("STACK_TRACE", throwable.getMessage() + Arrays.toString(throwable.getStackTrace()));
			logger.info("Entry Object - " + entryObject);
//			commonLoggingService.insertLogToTable(entryObject);
			throw throwable;
		} finally {
			logger.info("Entry Object - " + entryObject);
			commonLoggingService.insertLogToTable(entryObject);
		}

		return value;
	}

}
