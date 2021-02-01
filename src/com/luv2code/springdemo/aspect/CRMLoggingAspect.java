package com.luv2code.springdemo.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;



@Aspect
@Component
public class CRMLoggingAspect {

	private Logger myLogger = Logger.getLogger(getClass().getName());
	
	@Pointcut("execution(* com.luv2code.springdemo.controller.*.*(..))")
	private void forControllerPacage() {
		
	}
	
	@Pointcut("execution(* com.luv2code.springdemo.service.*.*(..))")
	private void forServicePacage() {
		
	}
	
	
	
	@Pointcut("execution(* com.luv2code.springdemo.dao.*.*(..))")
	private void forDaoPacage() {
		
	}
	
	@Pointcut("forControllerPacage() || forServicePacage() || forDaoPacage()")
	private void forAppFlow() {
		
	}
	
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		String theMethod= theJoinPoint.getSignature().toShortString();
		myLogger.info("====> @Before Calling the method : "+ theMethod);
		
		//YOU CAN ALSO GET THE ARGUMNTS
		Object[] argsObjects = theJoinPoint.getArgs();
		for(Object object : argsObjects) {
			myLogger.info("===> arguments: "+ object);
		}
	}
	@AfterReturning(
			pointcut = "forAppFlow()",
			returning = "theResult")
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		String theMethod= theJoinPoint.getSignature().toShortString();
		myLogger.info("====> @AfterReturnin from method : "+ theMethod);
		
		myLogger.info("====> result : "+ theResult);
	}
	
}
