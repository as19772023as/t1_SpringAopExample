package ru.strebkov.t1_SpringAopExample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1)
public class ExceptionHandlerAspect { // ver 4 PlantService
    @AfterThrowing(pointcut = "within(ru.strebkov.t1_SpringAopExample.service.*) &&" +
            "execution(* *(..) throws @ru.strebkov.t1_SpringAopExample.annotation.Throw *)", throwing = "exception")
    public void afterThrowing(JoinPoint joinPoint, Exception exception) { // Exception exception == throwing = "exception"
        log.info("Произошла ошибка при вызове методов: {}", joinPoint.getSignature().toShortString());
        log.info("Ошибку: {}", exception.getMessage());
    }
}
