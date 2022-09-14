package info.interventure.coffeemachine.service;

import info.interventure.coffeemachine.model.Instruction;
import info.interventure.coffeemachine.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.lang.invoke.MethodHandles;

import static info.interventure.coffeemachine.model.Ingredient.FOAM;
import static info.interventure.coffeemachine.model.Ingredient.MILK;

@Service
public class CoffeeMakingService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private static final String COFFEE = "ⅽ[ː̠̈ː̠̈ː̠̈] ͌";

    private final RecipeService recipeService;

    private final MilkService milkService;

    @Autowired
    public CoffeeMakingService(RecipeService recipeService, MilkService milkService) {
        this.recipeService = recipeService;
        this.milkService = milkService;
    }

    public String makeCoffeeOfType(final Long coffeeTypeId) {
        validate(coffeeTypeId);
        Recipe recipe = getRecipeByTypeId(coffeeTypeId);
        logger.debug("Found recipe for coffee typeId: {}, recipe: {}", coffeeTypeId, recipe);
        String coffee = makeCoffeeByRecipe(recipe);
        logger.info("Coffee made, ready for serving");
        return coffee;
    }

    private void validate(final Long coffeeTypeId) {
        Assert.notNull(coffeeTypeId, "Type id cannot be null");
    }

    private Recipe getRecipeByTypeId(final Long coffeeTypeId) {
        logger.info("Fetching recipe for coffee typeId: {}", coffeeTypeId);
        return recipeService.getRecipeByTypeId(coffeeTypeId);
    }

    private String makeCoffeeByRecipe(final Recipe recipe) {
        logger.debug("Performing instructions, size: {}", recipe.getInstructions().size());
        recipe.getInstructions().forEach(this::performInstruction);
        logger.info("Performed all instructions from recipe");
        return COFFEE;
    }

    private void performInstruction(final Instruction instruction) {
        if (instruction.getIngredient().equals(MILK) || instruction.getIngredient().equals(FOAM)) {
            milkService.getMilk(instruction);
        }

        try {
            Thread.sleep(200L);
            logger.info("Performed instruction: {}", instruction);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
