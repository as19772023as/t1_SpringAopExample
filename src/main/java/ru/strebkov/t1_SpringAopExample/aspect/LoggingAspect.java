package ru.strebkov.t1_SpringAopExample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect { //  Сервис цветов
    @Around("bean(plantService)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();

        String methodName = proceedingJoinPoint.getSignature().getName(); // читает имя метода
        Object[] methodArgs = proceedingJoinPoint.getArgs(); //  читает аргументы

        log.info("Выполнение метода {} с аргументами {}", methodName, methodArgs);

        Object result = proceedingJoinPoint.proceed(); //  результат метода

        long endTime = System.currentTimeMillis();

        log.info("Метод {} выполнился за {} мс с результатом {}", methodName, endTime - startTime, result);

        return result;
    }
}
