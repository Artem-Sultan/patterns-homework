package ru.sbt.test.refactoring.commands.movement;

import ru.sbt.test.refactoring.commands.CommandChain;
import ru.sbt.test.refactoring.units.FieldUnit;

/**
 * Created by artem on 04.07.15.
 */
public class HorseMove extends CommandChain {
    private CommandChain chain;

    public HorseMove(FieldUnit fieldUnit) {
        chain = new MoveForward(fieldUnit);
        chain.addCommandToQueuee(new MoveForward(fieldUnit))
                .addCommandToQueuee(new RotateClockwise(fieldUnit))
                .addCommandToQueuee(new MoveForward(fieldUnit));
    }

    @Override
    protected void executeSelf() {
        chain.execute();
    }
}
