package ru.sbt.test.refactoring.units;

import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.manager.MapManager;

/**
 * Created by artem on 04.07.15.
 */
public class Soldier extends FieldUnit {
    private int ammo;

    public Soldier(UnitPosition unitPosition, MapManager mapManager, int ammo) {
        super(unitPosition, mapManager);
        this.ammo = ammo;
    }

    public int getAmmo() {
        return ammo;
    }

    public void setAmmo(int ammo) {
        this.ammo = ammo;
    }
}
