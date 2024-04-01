package ru.strebkov.t1_SpringAopExample.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Info { // ExampleAspect
    public void info(){
        log.info("INFO METHOD");
    }
}
