# spring-aop-logging
This repo contain implementation of spring aop logging framework. It will use centralized logging mechanism.

For implementation:
1. We need to add spring aop dependency.
2. Implement LoggingAdvice with @Aspect which will contain the pointcut and advice for centralized logging.
3. ExecutionTimeTrackerAdvice Advice will track time taken by method to execute with the help of custom annotation @TrackExecutionTime

Annotations:-
 1. @Aspect – Used to create aspects and it consists all advice.
 2. @Before – Run before the method execution.
 3. @After – Run after the method returned a result.
 4. @AfterReturning – Run after the method returned a result, intercept the returned result as well.
 5. @AfterThrowing – Run after the method throws an exception.
 6. @Around – Run around the method execution.
 7. @EnableAspectJAutoProxy(proxyTargetClass = true) - if you are autowiring interfaces for the implementations.
  
Learning :- https://www.geeksforgeeks.org/usage-of-before-after-around-afterreturning-and-afterthrowing-in-a-single-spring-aop-project/


![image](https://user-images.githubusercontent.com/8069743/202154800-ba0d22f5-2b59-4000-9288-7f5a55483510.png)
