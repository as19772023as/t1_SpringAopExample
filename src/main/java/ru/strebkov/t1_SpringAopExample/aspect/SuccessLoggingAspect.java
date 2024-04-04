package ru.strebkov.t1_SpringAopExample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
@Order(1) // ver 3
public class SuccessLoggingAspect { // ver 3 PlantService + приоритет

    @AfterReturning("within(ru.strebkov.t1_SpringAopExample.service.*) && @within(ru.strebkov.t1_SpringAopExample.annotation.SuccessLogging)")
    public  void  successLogging(JoinPoint joinPoint){
        log.info("Методы успешно выполнились: {}", joinPoint.getSignature().toLongString());
        log.info("____________________________");
    }

}
