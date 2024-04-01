package ru.strebkov.t1_SpringAopExample.annotation;

import ru.strebkov.t1_SpringAopExample.model.RoleType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //  жизненный цикл аннотации
@Target(ElementType.METHOD) //  область применения над методами
public @interface PreInvoke {
    RoleType[] roles();
}