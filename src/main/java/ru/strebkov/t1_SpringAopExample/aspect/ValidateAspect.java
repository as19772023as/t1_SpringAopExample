package ru.strebkov.t1_SpringAopExample.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import ru.strebkov.t1_SpringAopExample.exception.PlantException;
import ru.strebkov.t1_SpringAopExample.model.Plant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Aspect
@Slf4j
@Order(3)
public class ValidateAspect { // ver 6 PlantService

    private static final Map<String, List<String>> PLANTS = new HashMap<>();

    static {
        PLANTS.put("Цветок", List.of("Роза", "Тюльпан", "Кактус"));
        PLANTS.put("Дерево", List.of("Дуб", "Сосна", "Ель"));
        PLANTS.put("Трава", List.of("Рожь", "Овес", "Пшеница"));
    }

    @Pointcut("execution(public void add*(@ru.strebkov.t1_SpringAopExample.annotation.Valid (*), ..))")
    public void needValidPointcut() {
    }

    @Pointcut("args(ru.strebkov.t1_SpringAopExample.annotation.Model) && execution(public void add*(..))")
    public void needValidPointcutWithArgs() {
    }

    @Pointcut("needValidPointcut() && args(plant)")
    public void validPlantPointcut(Plant plant) {
    }

    @Pointcut("needValidPointcut() && args(plants)")
    public void validPlantListPointcut(List<Plant> plants) {
    }


    @Before(value = "validPlantListPointcut(plants)", argNames = "plants")
    public void validatePlantList(List<Plant> plants) {
        log.info("Валидация списка растений перед вызовом метода");
        plants.forEach(this::validate);
    }


    private void validate(Plant plant) {
        if (!PLANTS.containsKey(plant.getType()) ||
                PLANTS.get(plant.getType()).stream().noneMatch(p -> p.equals((plant.getName())))) {
            throw new PlantException("Ошибка валидации");
        }
    }

}
