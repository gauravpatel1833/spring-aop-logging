package com.javatechie.spring.aop.api.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTimeTrackerAdvice {

    Logger logger=LoggerFactory.getLogger(ExecutionTimeTrackerAdvice.class);

    /**
     * Here we are not using pointcut as we are defining custom annotation at method level
     * So we are registering out annotation class in around advice.
     */
    @Around("@annotation(com.javatechie.spring.aop.api.advice.TrackExecutionTime)")
    public Object trackTime(ProceedingJoinPoint pjp) throws Throwable {

        //Before advice
        long startTime=System.currentTimeMillis();

        //After method execution is complete
        Object obj=pjp.proceed();

        //After advice
        long endTime=System.currentTimeMillis();
        logger.info("Method name "+pjp.getSignature()+" time taken to execute : "+(endTime-startTime));
        return obj;
    }

}
