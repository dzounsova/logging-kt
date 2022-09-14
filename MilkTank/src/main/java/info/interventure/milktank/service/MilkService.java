package info.interventure.milktank.service;

import info.interventure.milktank.model.Instruction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class MilkService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final Integer TANK_CAPACITY = 1_000;
    private static final Integer THRESHOLD = 200;
    private final AtomicInteger currentLevel;

    public MilkService() {
        this.currentLevel = new AtomicInteger(TANK_CAPACITY);
    }

    public String getMilk(final Instruction instruction) {
        validate(instruction.getAmount());

        int newLevel = currentLevel.get() - instruction.getAmount();
        currentLevel.set(newLevel);


        checkThreshold();
        return "MILK";
    }

    public String getFoam(final Instruction instruction) {
        int foamToMilkConversion = instruction.getAmount() * 10;
        validate(foamToMilkConversion);

        int newLevel = currentLevel.get() - foamToMilkConversion;
        currentLevel.set(newLevel);

        checkThreshold();
        return "FOAM";
    }

    private void validate(final Integer amount) {
        if (currentLevel.get() < amount) {
            throw new IllegalArgumentException("Not enough milk in container");
        }
    }

    private void checkThreshold() {
        if (currentLevel.get() <= THRESHOLD) {
            logger.warn("Tank level met threshold.");
        }
    }

    public String prepare(Instruction instruction) {
        switch (instruction.getIngredient()) {
            case MILK:
                return getMilk(instruction);
            case FOAM:
                return getFoam(instruction);
            default:
                throw new UnsupportedOperationException("Machine doesn't know to operate with ingredient" + instruction.getIngredient());
        }
    }

    public void fillUp() {
        currentLevel.set(TANK_CAPACITY);
    }
}
