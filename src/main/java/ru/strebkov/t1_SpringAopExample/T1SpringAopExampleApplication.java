package ru.strebkov.t1_SpringAopExample;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import ru.strebkov.t1_SpringAopExample.model.Info;
import ru.strebkov.t1_SpringAopExample.model.Plant;
import ru.strebkov.t1_SpringAopExample.service.PlantService;
import ru.strebkov.t1_SpringAopExample.utils.UserContext;

import java.util.List;

import static java.util.List.*;

@RequiredArgsConstructor // @Autowired - не нужен (=> ______final______)
@SpringBootApplication
public class T1SpringAopExampleApplication {
   // private final Info info;
    // @Autowired
    private final PlantService plantService;

    public static void main(String[] args) {
        SpringApplication.run(T1SpringAopExampleApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void onReady() {
     //   info.info();

        // UserContext.setUsername("admin");
        UserContext.setUsername("user");
        plantService.addPlant(new Plant("Роза", "Цветок"));
        System.out.println(plantService.getPlantByType("Цветок"));
       System.out.println(plantService.getPlantByName("Роза"));
//        // plantService.addPlants(List.of(new Plant("Плод", "Яблоко")));
//        // plantService.addPlant(new Plant("Плод", "Яблоко"));
//        System.out.println(plantService.getPlantByType("Яблоко"));

    }
}
