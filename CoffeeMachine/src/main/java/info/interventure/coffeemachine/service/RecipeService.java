package info.interventure.coffeemachine.service;

import info.interventure.coffeemachine.model.Instruction;
import info.interventure.coffeemachine.model.Recipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import static info.interventure.coffeemachine.model.Ingredient.COFFEE;
import static info.interventure.coffeemachine.model.Ingredient.FOAM;
import static info.interventure.coffeemachine.model.Ingredient.MILK;
import static info.interventure.coffeemachine.model.Ingredient.WATER;
import static info.interventure.coffeemachine.model.Unit.CM;
import static info.interventure.coffeemachine.model.Unit.ML;
import static info.interventure.coffeemachine.model.Unit.SHOT;

@Service
public class RecipeService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private final List<Recipe> RECIPE_BOOK = new ArrayList<>();

    public RecipeService() {
        initRecipeBook();
    }

    public Recipe getRecipeByTypeId(final Long typeId) {
        logger.info("Fetching recipe from book, typeId: {}", typeId);
        return RECIPE_BOOK.stream()
                .filter(recipe -> recipe.getTypeId().equals(typeId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Does not contain recipe for typeId: " + typeId));
    }

    private void initRecipeBook() {
        logger.debug("Creating recipe book");
        Recipe espresso = Recipe.builder()
                .typeId(1L)
                .name("Espresso")
                .instructions(List.of(Instruction.builder().ingredient(COFFEE).amount(1).unit(SHOT).build(),
                        Instruction.builder().ingredient(WATER).amount(75).unit(ML).build()
                ))
                .build();
        Recipe americano = Recipe.builder()
                .typeId(2L)
                .name("Americano")
                .instructions(List.of(Instruction.builder().ingredient(WATER).amount(100).unit(ML).build(),
                        Instruction.builder().ingredient(COFFEE).amount(1).unit(SHOT).build(),
                        Instruction.builder().ingredient(WATER).amount(75).unit(ML).build()
                ))
                .build();
        Recipe latte = Recipe.builder()
                .typeId(3L)
                .name("Cafe Latte")
                .instructions(List.of(Instruction.builder().ingredient(COFFEE).amount(1).unit(SHOT).build(),
                        Instruction.builder().ingredient(WATER).amount(75).unit(ML).build(),
                        Instruction.builder().ingredient(MILK).amount(150).unit(ML).build(),
                        Instruction.builder().ingredient(FOAM).amount(1).unit(CM).build()
                ))
                .build();
        Recipe cappuccino = Recipe.builder()
                .typeId(4L)
                .name("Cappuccino")
                .instructions(List.of(Instruction.builder().ingredient(COFFEE).amount(1).unit(SHOT).build(),
                        Instruction.builder().ingredient(WATER).amount(75).unit(ML).build(),
                        Instruction.builder().ingredient(MILK).amount(150).unit(ML).build(),
                        Instruction.builder().ingredient(FOAM).amount(3).unit(CM).build()
                ))
                .build();
        RECIPE_BOOK.add(espresso);
        RECIPE_BOOK.add(americano);
        RECIPE_BOOK.add(latte);
        RECIPE_BOOK.add(cappuccino);
        logger.debug("Recipe book created, size: {}", RECIPE_BOOK.size());
    }
}
