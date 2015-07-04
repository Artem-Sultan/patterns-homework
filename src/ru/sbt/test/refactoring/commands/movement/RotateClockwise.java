package ru.sbt.test.refactoring.commands.movement;

import ru.sbt.test.refactoring.commands.FieldUnitCommand;
import ru.sbt.test.refactoring.units.FieldUnit;

/**
 * Created by artem on 04.07.15.
 */
public class RotateClockwise extends FieldUnitCommand {

    public RotateClockwise(FieldUnit fieldUnit) {
        super(fieldUnit);
    }

    @Override
    protected void executeSelf() {
        fieldUnit.getUnitPosition().rotateClockwise();
    }
}
