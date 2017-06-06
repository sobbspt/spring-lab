package com.wanichnun.lab.aop.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LogAspect {

    @Around("@annotation(com.wanichnun.lab.aop.annotation.LogExecutionTime)")
    public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        final long start = System.currentTimeMillis();
        final Object proceed = joinPoint.proceed();
        final long executionTime = System.currentTimeMillis() - start;
        log.info(joinPoint.getSignature() + " executed in " + executionTime + "ms");

        return proceed;
    }

    @Around("@annotation(com.wanichnun.lab.aop.annotation.LogActivity) && args(text)")
    public Object logActivity(ProceedingJoinPoint joinPoint, String text) throws Throwable {
        log.info("BEGIN: " + joinPoint.getSignature() + " INPUT: " + text);
        final Object proceed = joinPoint.proceed();
        log.info("END: " + joinPoint.getSignature());

        return proceed;
    }
}
