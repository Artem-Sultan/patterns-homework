package ru.sbt.test.refactoring.commands.shooting;

import ru.sbt.test.refactoring.commands.CommandChain;
import ru.sbt.test.refactoring.units.Soldier;

import static java.lang.Math.min;

/**
 * Created by artem on 04.07.15.
 */
public class ShootNTimes extends CommandChain{
    private final Soldier soldier;
    private final int timesToShoot;

    public ShootNTimes(Soldier soldier, int timesToShoot) {
        this.soldier = soldier;
        this.timesToShoot = timesToShoot;
    }

    @Override
    protected void executeSelf() {
        int currentAmmo = soldier.getAmmo();
        int shootsAvailable = min(currentAmmo,timesToShoot);
        for (int i = 0; i< shootsAvailable; i++) {
            System.out.println("Shoooot " + i);
        }
        currentAmmo = currentAmmo - shootsAvailable;
        if (currentAmmo ==0) System.out.println("Need reload!!");
        soldier.setAmmo(currentAmmo);
    }
}
