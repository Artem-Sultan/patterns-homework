package ru.sbt.test.refactoring.units;

import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.manager.MapManager;

/**
 * Created by artem on 04.07.15.
 */
public class FieldUnit {
    private UnitPosition unitPosition;
    private MapManager mapManager;

    public FieldUnit(UnitPosition unitPosition, MapManager mapManager) {
        this.unitPosition = unitPosition;
        this.mapManager = mapManager;
        mapManager.addUnitToMapIfPossible(unitPosition,this);
    }

    public UnitPosition getUnitPosition() {
        return unitPosition;
    }

    public void changeUnitPositionIfValid(UnitPosition oldPosition, UnitPosition newPosition) {
        if (mapManager.positionIsValid(newPosition)) {
            this.unitPosition = newPosition;
            mapManager.updatePosition(this,oldPosition);
        }
    }


}

