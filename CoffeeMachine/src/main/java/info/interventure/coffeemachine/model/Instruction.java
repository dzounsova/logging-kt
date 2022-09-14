package info.interventure.coffeemachine.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class Instruction {

    private Ingredient ingredient;

    private Integer amount;

    private Unit unit;
}
