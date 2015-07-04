package ru.sbt.test.refactoring.commands.movement;

import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.units.FieldUnit;

/**
 * Created by artem on 04.07.15.
 */
public class MoveBackward extends MovementAbstract {
    public MoveBackward(FieldUnit fieldUnit) {
        super(fieldUnit);
    }

    @Override
    protected UnitPosition computeNewPosition(UnitPosition oldPosition) {
        oldPosition.moveBackward();
        return oldPosition;
    }
}
