package ru.strebkov.t1_SpringAopExample.service;

import org.springframework.stereotype.Service;
import ru.strebkov.t1_SpringAopExample.annotation.Asynchronousle;
import ru.strebkov.t1_SpringAopExample.annotation.PreInvoke;
import ru.strebkov.t1_SpringAopExample.annotation.SuccessLogging;
import ru.strebkov.t1_SpringAopExample.annotation.Valid;
import ru.strebkov.t1_SpringAopExample.exception.PlantException;
import ru.strebkov.t1_SpringAopExample.model.Plant;
import ru.strebkov.t1_SpringAopExample.model.RoleType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@SuccessLogging // ver 3  и SuccessLoggingAspect и приоритет +
public class PlantService { // ver 1 - Plant LoggingAspect, а  c аннотациями - PreInvokeCheckerAspect
    // ver 2 - AsyncRunnerAspect
    private final Map<String, Plant> plants = new HashMap<>();


    @PreInvoke(roles = RoleType.ADMIN)
    @Asynchronousle
    public void addPlant(@Valid Plant plant) {
        plants.put(plant.getName(), plant);
    } // valid ver 6 ValidateAspect

    @PreInvoke(roles = RoleType.ADMIN)
    @Asynchronousle
    public void addPlants(@Valid List<Plant> newPlants) { // ver 4 ExceptionHandlerAspect
        plants.putAll(newPlants.stream().collect(Collectors.toMap(Plant::getName, Function.identity())));
        // ver 4
        if (newPlants.size() == 1){
            throw new PlantException("Используй метод addPlant(Plant plant) ");
        }
    }

    @PreInvoke(roles = {RoleType.ADMIN, RoleType.USER})
    public Plant getPlantByName(String name) {
        return plants.get(name);
    }

    @PreInvoke(roles = {RoleType.ADMIN, RoleType.USER})
    public List<Plant> getPlantByType(String type) {
        return plants.values().stream().filter(plant -> plant.getType().equals(type)).collect(Collectors.toList());
    }
}
