package ru.sbt.test.refactoring.commands;

import ru.sbt.test.refactoring.units.FieldUnit;

/**
 * Created by artem on 04.07.15.
 */
public abstract class FieldUnitCommand extends CommandChain {
    protected final FieldUnit fieldUnit;

    public FieldUnitCommand(FieldUnit fieldUnit) {
        this.fieldUnit = fieldUnit;
    }
}
