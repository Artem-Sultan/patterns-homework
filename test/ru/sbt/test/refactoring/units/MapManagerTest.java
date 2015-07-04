package ru.sbt.test.refactoring.units;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.test.refactoring.commands.CommandChain;
import ru.sbt.test.refactoring.commands.movement.HorseMove;
import ru.sbt.test.refactoring.commands.movement.MoveBackward;
import ru.sbt.test.refactoring.containers.Orientation;
import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.manager.MapManager;

public class MapManagerTest extends TestCase {
    private final int xDim = 5;
    private final int yDim = 5;
    private MapManager mapManager;
    private FieldUnit testUnit1;
    private FieldUnit testUnit2;
    private UnitPosition initialPosition1;
    private UnitPosition initialPosition2;
    private UnitPosition freePosition;


    @Before
    public void setUp() {
        initialPosition1 = new UnitPosition(0, 0, Orientation.NORTH);
        mapManager = new MapManager(xDim, yDim);
        testUnit1 = new FieldUnit(initialPosition1, mapManager);
        initialPosition2 = new UnitPosition(0, 1, Orientation.NORTH);
        testUnit2 = new FieldUnit(initialPosition2, mapManager);
        freePosition = new UnitPosition(2, 2, Orientation.NORTH);
    }

    @Test
    public void testPositionIsValid() {
        assertFalse(managerValidatesPosition(initialPosition2));
        assertTrue(managerValidatesPosition(freePosition));
        initialPosition1.rotateClockwise();
        assertFalse(managerValidatesPosition(initialPosition1));
    }

    @Test
    public void testManagerAfterMovement() {
        UnitPosition destinationPosition = new UnitPosition(1, 3, Orientation.EAST);
        CommandChain horseMove = new HorseMove(testUnit2);
        horseMove.execute();
        assertTrue(managerValidatesPosition(initialPosition2));
        assertFalse(managerValidatesPosition(destinationPosition));
    }

    @Test
    public void testUnitShouldntMove() {
        CommandChain backWard = new MoveBackward(testUnit1);
        backWard.execute();
        assertEquals(initialPosition1, testUnit1.getUnitPosition());
    }

    private boolean managerValidatesPosition(UnitPosition destinationPosition) {
        return mapManager.positionIsValid(destinationPosition);
    }


}