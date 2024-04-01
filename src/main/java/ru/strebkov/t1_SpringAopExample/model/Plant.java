package ru.strebkov.t1_SpringAopExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plant { // LoggingAspect
    private String name;
    private String type;
}
