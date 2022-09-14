package info.interventure.milktank.controller;

import info.interventure.milktank.model.Instruction;
import info.interventure.milktank.service.MilkService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.invoke.MethodHandles;

@RestController
@RequestMapping("/milk")
public class MilkController {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final MilkService milkService;

    @Autowired
    public MilkController(MilkService milkService) {
        this.milkService = milkService;
    }

    @PostMapping("/make")
    public ResponseEntity<String> makeCoffee(@RequestBody Instruction instruction) {
        logger.info("Received request to get milk for coffee, type: {}", instruction.getIngredient());
        String milk = milkService.prepare(instruction);
        return ResponseEntity.ok(milk);
    }

    @PostMapping
    public ResponseEntity<Void> fillUpTank() {
        logger.info("Adding milk to the tank");
        milkService.fillUp();
        return ResponseEntity.ok().build();
    }
}
