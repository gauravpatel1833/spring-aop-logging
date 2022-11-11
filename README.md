# spring-aop-logging
This repo contain implementation of spring aop logging framework. It will use centralized logging mechanism.

For implementation:
1. We need to add spring aop dependency.
2. Implement LoggingAdvice with @Aspect which will contain the pointcut and advice for centralized logging.
