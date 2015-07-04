package ru.sbt.test.refactoring.commands.movement;

import ru.sbt.test.refactoring.commands.FieldUnitCommand;
import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.units.FieldUnit;

/**
 * Created by artem on 04.07.15.
 */
public abstract class MovementAbstract extends FieldUnitCommand {
    public MovementAbstract(FieldUnit fieldUnit) {
        super(fieldUnit);
    }

    @Override
    protected void executeSelf() {
        UnitPosition oldPosition = fieldUnit.getUnitPosition();
        UnitPosition updatedPosition = computeNewPosition(oldPosition.copy());
        fieldUnit.changeUnitPositionIfValid(oldPosition, updatedPosition);
    }

    protected abstract UnitPosition computeNewPosition(UnitPosition oldPosition);
}
