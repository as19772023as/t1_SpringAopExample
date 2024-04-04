package ru.strebkov.t1_SpringAopExample.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.strebkov.t1_SpringAopExample.annotation.Model;

@Data
@AllArgsConstructor
@Model
public class Plant { // LoggingAspect
    private String name;
    private String type;
}
