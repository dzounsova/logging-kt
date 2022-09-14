package info.interventure.coffeemachine.controller;

import info.interventure.coffeemachine.service.CoffeeMakingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.lang.invoke.MethodHandles;


@RestController
@RequestMapping("/coffee")
public class CoffeeController {

    //JUL
    //private static final Logger logger = Logger.getLogger(CoffeeController.class.getName());
    //slf4j
    //Hosting class as an argument
    //private static final Logger logger = LoggerFactory.getLogger(CoffeeController.class);
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final CoffeeMakingService coffeeMakingService;

    @Autowired
    public CoffeeController(CoffeeMakingService coffeeMakingService) {
        this.coffeeMakingService = coffeeMakingService;
    }

    @PostMapping("/make")
    public ResponseEntity<String> makeCoffee(@RequestParam Long typeId) {
        //JUL
        //logger.log(Level.INFO, "Received request to make coffee of typeId: {0}, {1}", typeId, "dummy");
        logger.info("Received request to make coffee of typeId: {}", typeId);
        String coffee = coffeeMakingService.makeCoffeeOfType(typeId);
        return ResponseEntity.ok(coffee);
    }
}
