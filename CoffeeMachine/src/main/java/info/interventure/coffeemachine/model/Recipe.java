package info.interventure.coffeemachine.model;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@Builder
@ToString
public class Recipe {

    private Long typeId;

    private String name;

    private List<Instruction> instructions;
}
