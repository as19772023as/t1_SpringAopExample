package ru.strebkov.t1_SpringAopExample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
@Aspect
@Slf4j
public class AsyncRunnerAspect { // ver 2 - PlantService

    // @Pointcut("execution(ru.strebkov.t1_SpringAopExample.annotation.Asynchronousle public void add*(..))")
    // *  - любое кол-во символов;  (..) -  люб кол аргументов
    @Pointcut("@annotation(ru.strebkov.t1_SpringAopExample.annotation.Asynchronousle)")
    public void asyncRunnerPointcut() {
    }

    @Around("asyncRunnerPointcut()")
    public Object asyncRunner(ProceedingJoinPoint joinPoint) {
        return CompletableFuture.runAsync(() -> {
            try {
                log.info("Асинхр запуск AsyncRunnerAspect"); //  методы запускаютмя асинхронно
                joinPoint.proceed();
            }catch (Throwable e){
                log.info("ERROR AsyncRunnerAspect", e);
            }
        });

    }


}
