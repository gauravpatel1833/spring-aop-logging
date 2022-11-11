package com.javatechie.spring.aop.api.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

	/**
	 * @Pointcut annotation is to inform AOP what is the target where we need this logging or code of aspect.
	 * We need to provide pointcut as expression
	 * Syntax : Method/returnType  Directory.Package.Class.Method(Arguments)
	 * For Ex : Only apply to controller (* com.javatechie.spring.aop.api.controller.ProductController.saveProducts(...)
	 */
	@Pointcut(value="execution(* com.javatechie.spring.aop.api.*.*.*(..) )")
	public void myPointcut() {
		
	}

	/**
	 * ProceedingJoinPoint class internally uses reflection to track methods/classes invocations.
	 * @Around advice to define the pointcut where we need to execute this logic.
	 * This will serve as centralized logging mechanism
	 */
	@Around("myPointcut()")
	public Object applicationLogger(ProceedingJoinPoint pjp) throws Throwable {
		ObjectMapper mapper = new ObjectMapper();
		String methodName = pjp.getSignature().getName();
		String className = pjp.getTarget().getClass().toString();

		//To get input parameters to the method
		Object[] array = pjp.getArgs();
		log.info("method invoked " + className + " : " + methodName + "()" + " arguments : "
				+ mapper.writeValueAsString(array));

		//To get the return object from the method when method invocation is completed
		Object object = pjp.proceed();
		log.info(className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(object));
		return object;
	}

}
