package ru.strebkov.t1_SpringAopExample.annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME) //  жизненный цикл аннотации
@Target(ElementType.TYPE)
public @interface Model {
}
