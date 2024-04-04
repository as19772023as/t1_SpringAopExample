package ru.strebkov.t1_SpringAopExample.exception;

import ru.strebkov.t1_SpringAopExample.annotation.Throw;
@Throw // ver 4
public class PlantException extends RuntimeException {

    public PlantException() {
    }

    public PlantException(String message) {
        super(message);
    }

    public PlantException(String message, Throwable cause) {
        super(message, cause);
    }

    public PlantException(Throwable cause) {
        super(cause);
    }

}
