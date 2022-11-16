package com.javatechie.spring.aop.api.advice;

import com.javatechie.spring.aop.api.model.Product;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Aspect
@Component
public class LoggingAdvice {

	Logger log = LoggerFactory.getLogger(LoggingAdvice.class);

	/*@Aspect – Used to create aspects and it consists all advice.
	@Before – Run before the method execution.
	@After – Run after the method returned a result.
	@AfterReturning – Run after the method returned a result, intercept the returned result as well.
	@AfterThrowing – Run after the method throws an exception.
	@Around – Run around the method execution.*/

	/**
	 * @Pointcut annotation is to inform AOP what is the target where we need this logging or code of aspect.
	 * We need to provide pointcut as expression
	 * Syntax : Method/returnType  Directory.Package.Class.Method(Arguments)
	 * For Ex : Only apply to controller (* com.javatechie.spring.aop.api.controller.ProductController.saveProducts(...)
	 */
	@Pointcut(value="execution(* com.javatechie.spring.aop.api.*.*.*(..) )")
	public void myPointcut() {
		//Nothing to do here
	}

	/**
	 * ProceedingJoinPoint class internally uses reflection to track methods/classes invocations.
	 * @Around advice to define the pointcut where we need to execute this logic.
	 * This will serve as centralized logging mechanism.
	 * You can use inline pointcut as well with value tag in around annotation.
	 */
	//@Around(value = "execution(* com.javatechie.spring.aop.api.*.*.*(..) )")
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
		//For exception logging you can surroung proceed call with try/catch and log the exception.
		Object object = pjp.proceed();
		log.info(className + " : " + methodName + "()" + "Response : "
				+ mapper.writeValueAsString(object));
		return object;
	}

	/**
	 * Before and after method invoked on method call trace. Where as with around advice it will run on method completion.
	 * If exception occurs in a method then after advice will be invoked but around advice proceed will throw exception.
	 */

	/*@Before(value = "execution(* com.javatechie.spring.aop.api.*.*.*(..) )")
	public void logStatementBefore(JoinPoint joinPoint) {
		log.info("Before Execution {}", joinPoint);
	}

	@After(value = "execution(* com.javatechie.spring.aop.api.*.*.*(..) )")
	public void logStatementAfter(JoinPoint joinPoint) {
		log.info("After Execution {}", joinPoint);
	}*/

	/*@AfterReturning(value = "execution(* com.javatechie.spring.aop.api.*.*.*(..) )", returning = "productList")
	public void afterReturningAdvice(JoinPoint joinPoint, List<Product> productList) {
		log.info("After Returning method:" + joinPoint.getSignature());
		log.info("After Returning product list :" + productList);
	}

	@AfterThrowing(value = "execution(* com.javatechie.spring.aop.api.*.*.*(..) )", throwing = "ex")
	public void afterThrowingAdvice(JoinPoint joinPoint, Exception ex) {
		log.info("After Throwing exception in method:" + joinPoint.getSignature());
		log.info("After Throwing Exception is: " + ex.getMessage());
	}*/

}
