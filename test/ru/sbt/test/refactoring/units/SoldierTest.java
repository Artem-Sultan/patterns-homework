package ru.sbt.test.refactoring.units;

import org.junit.Before;
import org.junit.Test;
import ru.sbt.test.refactoring.commands.CommandChain;
import ru.sbt.test.refactoring.commands.movement.HorseMove;
import ru.sbt.test.refactoring.commands.movement.MoveForward;
import ru.sbt.test.refactoring.commands.movement.RotateClockwise;
import ru.sbt.test.refactoring.commands.shooting.ShootNTimes;
import ru.sbt.test.refactoring.containers.Orientation;
import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.manager.MapManager;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by artem on 04.07.15.
 */
public class SoldierTest {
    private final int xDim = 5;
    private final int yDim = 5;
    private MapManager mapManager;
    private FieldUnit vertushka;
    private Soldier commandos;
    private UnitPosition initialPosition1;
    private UnitPosition initialPosition2;
    private final int initialAmmo = 7;

    @Before
    public void setUp() {
        mapManager = new MapManager(xDim, yDim);
        initialPosition1 = new UnitPosition(0, 0, Orientation.NORTH);
        vertushka = new FieldUnit(initialPosition1, mapManager);
        initialPosition2 = new UnitPosition(0, 1, Orientation.NORTH);
        commandos = new Soldier(initialPosition2, mapManager, initialAmmo);
    }

    @Test
    public void testMove(){
        UnitPosition destinationPosition = new UnitPosition(1,3,Orientation.EAST);
        CommandChain movement = new HorseMove(commandos);
        movement.execute();
        assertEquals(destinationPosition, commandos.getUnitPosition());
    }

    @Test
    public void testShoot() {
        int shotsDone = 2;
        CommandChain shooting = new ShootNTimes(commandos, shotsDone);
        shooting.execute();
        assertEquals(commandos.getAmmo(), initialAmmo-shotsDone);
    }

    @Test
    public void testVietnam() {
        CommandChain baitPlan = new ShootNTimes(commandos,1)
                .addCommandToQueuee(new MoveForward(commandos))
                .addCommandToQueuee(new MoveForward(vertushka));

        CommandChain secretPlan = new RotateClockwise(commandos)
                .addCommandToQueuee(new HorseMove(commandos))
                .addCommandToQueuee(new ShootNTimes(commandos, 3));

        baitPlan.addCommandToQueuee(secretPlan);
        baitPlan.execute();

        UnitPosition enemyGeneralPosition = new UnitPosition(2,1,Orientation.SOUTH);
        UnitPosition landingPoint = new UnitPosition(0,1,Orientation.NORTH);

        assertEquals(enemyGeneralPosition, commandos.getUnitPosition());
        assertEquals(commandos.getAmmo(), initialAmmo-4);
        assertEquals(vertushka.getUnitPosition(), landingPoint);
    }

}
