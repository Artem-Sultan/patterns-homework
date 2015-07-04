package ru.sbt.test.refactoring.units;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import ru.sbt.test.refactoring.commands.CommandChain;
import ru.sbt.test.refactoring.commands.movement.HorseMove;
import ru.sbt.test.refactoring.commands.movement.MoveBackward;
import ru.sbt.test.refactoring.commands.movement.MoveForward;
import ru.sbt.test.refactoring.commands.movement.RotateClockwise;
import ru.sbt.test.refactoring.containers.Orientation;
import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.manager.MapManager;

public class MovementCommandsTest extends TestCase {

    private final UnitPosition initialPosition = new UnitPosition(0,0, Orientation.NORTH);
    private FieldUnit testUnit;

    @Before
    public void setUp() {
        MapManager mapManager = new MapManager(5,5);
        testUnit = new FieldUnit(initialPosition, mapManager);
    }

    private void resultPositionIsCorrect(UnitPosition destinationPosition) {
        assertTrue(testUnit.getUnitPosition().equals(destinationPosition));
    }

    @Test
    public void testShouldRotateOnce() {
        CommandChain chain1 = new RotateClockwise(testUnit);
        Orientation resultOrientation = Orientation.EAST;
        chain1.execute();
        assertTrue(testUnit.getUnitPosition().getCurrentOrientation().equals(resultOrientation));
    }

    @Test
    public void testChainShouldRotate4Times() {
        CommandChain chain1 = new RotateClockwise(testUnit);
        Orientation resultOrientation = Orientation.NORTH;
        for (int i = 0; i< 3; i++) {
            chain1.addCommandToQueuee(new RotateClockwise(testUnit));
        }
        chain1.execute();
        assertTrue(testUnit.getUnitPosition().getCurrentOrientation().equals(resultOrientation));
    }

    @Test
    public void testShouldRunSquare() {
        CommandChain chain1 = new RotateClockwise(testUnit);
        CommandChain chain2 = new MoveForward(testUnit);

        chain2.execute();
        resultPositionIsCorrect(new UnitPosition(0, 1, Orientation.NORTH));
        chain1.execute();
        chain2.execute();
        resultPositionIsCorrect(new UnitPosition(1, 1, Orientation.EAST));
        chain1.execute();
        chain2.execute();
        resultPositionIsCorrect(new UnitPosition(1, 0, Orientation.SOUTH));
        chain1.execute();
        chain2.execute();
        resultPositionIsCorrect(new UnitPosition(0,0, Orientation.WEST));
    }

    @Test
    public void testShouldRunLongSquare() {
        CommandChain rotateClockwise = new RotateClockwise(testUnit);
        CommandChain moveForward = new MoveForward(testUnit);

        move3Times(moveForward);
        assertTrue(testUnit.getUnitPosition().equals(new UnitPosition(0, 3, Orientation.NORTH)));
        rotateClockwise.execute();
        move3Times(moveForward);
        assertTrue(testUnit.getUnitPosition().equals(new UnitPosition(3, 3, Orientation.EAST)));
        rotateClockwise.execute();
        move3Times(moveForward);
        assertTrue(testUnit.getUnitPosition().equals(new UnitPosition(3, 0, Orientation.SOUTH)));
        rotateClockwise.execute();
        move3Times(moveForward);

        assertTrue(testUnit.getUnitPosition().equals(new UnitPosition(0,0,Orientation.WEST)));

    }

    private void move3Times(CommandChain chain2) {
        chain2.execute();
        chain2.execute();
        chain2.execute();
    }


    @Test
    public void testShouldRunChainForward() {
        UnitPosition destinationPosition = new UnitPosition(0,3,Orientation.NORTH);

        CommandChain chain1 = new MoveForward(testUnit);
        chain1.addCommandToQueuee(new MoveForward(testUnit))
                .addCommandToQueuee(new MoveForward(testUnit));

        chain1.execute();
        resultPositionIsCorrect(destinationPosition);
    }

    @Test
    public void testShouldRunSmallChain() {
        UnitPosition destinationPosition = new UnitPosition(0,1,Orientation.EAST);

        CommandChain chain1 = new MoveForward(testUnit);
        chain1.addCommandToQueuee(new RotateClockwise(testUnit));

        chain1.execute();
        resultPositionIsCorrect(destinationPosition);
    }


    @Test
    public void testShouldRunComplexChain() {
        UnitPosition destinationPosition = new UnitPosition(2,1,Orientation.WEST);

        CommandChain chain1 = new MoveForward(testUnit);
        buildComplexChain(chain1);

        chain1.execute();
        resultPositionIsCorrect(destinationPosition);
    }

    private void buildComplexChain(CommandChain chain1) {
        chain1.addCommandToQueuee(new MoveForward(testUnit))
                .addCommandToQueuee(new RotateClockwise(testUnit))
                .addCommandToQueuee(new MoveForward(testUnit))
                .addCommandToQueuee(new MoveForward(testUnit))
                .addCommandToQueuee(new RotateClockwise(testUnit))
                .addCommandToQueuee(new MoveForward(testUnit))
                .addCommandToQueuee(new RotateClockwise(testUnit))
        ;
    }

    @Test
    public void testShouldRunHorseMove() {
        UnitPosition destinationPosition = new UnitPosition(1,2,Orientation.EAST);

        CommandChain horseMove = new HorseMove(testUnit);

        horseMove.execute();
        resultPositionIsCorrect(destinationPosition);
    }

    @Test
    public void testShouldRunDoubleChainedHorseMove() {
        UnitPosition destinationPosition = new UnitPosition(3,1,Orientation.SOUTH);
        CommandChain doubleHorseMove = new HorseMove(testUnit);
        doubleHorseMove.addCommandToQueuee(new HorseMove(testUnit));

        doubleHorseMove.execute();
        resultPositionIsCorrect(destinationPosition);
    }

    @Test
    public void testShouldRunForwardBackwardChain() {
        UnitPosition destinationPosition = new UnitPosition(-1,1,Orientation.EAST);
        CommandChain forwardBackWardChain = new MoveForward(testUnit)
                .addCommandToQueuee(new RotateClockwise(testUnit))
                .addCommandToQueuee(new MoveBackward(testUnit));

        forwardBackWardChain.execute();
        resultPositionIsCorrect(destinationPosition);
    }
}