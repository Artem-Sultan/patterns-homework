package ru.sbt.test.refactoring.containers;

import java.util.Arrays;
import java.util.List;

/**
 * Created by artem on 04.07.15.
 */
public class UnitPosition {
    private static Orientation[] ors =  {Orientation.NORTH, Orientation.EAST, Orientation.SOUTH, Orientation.WEST};
    private static final List<Orientation> orientations = Arrays.asList(ors);
    private int x;
    private int y;
    private Orientation currentOrientation;

    public UnitPosition(int x, int y, Orientation currentOrientation) {
        this.x = x;
        this.y = y;
        this.currentOrientation = currentOrientation;
    }

    public void rotateClockwise() {
        int indexOfCurrentOrientation = orientations.indexOf(currentOrientation);
        currentOrientation = orientations.get(++indexOfCurrentOrientation%orientations.size());
    }

    public String toString() {
        return (x+" " +y+" " + currentOrientation);
    }

    public void moveForward() {
        int indexOfCurrentOrientation = orientations.indexOf(currentOrientation);
        x = x + (2-indexOfCurrentOrientation)%2;
        y = y + (1 - indexOfCurrentOrientation)%2;
    }

    public void moveBackward() {
        int indexOfCurrentOrientation = orientations.indexOf(currentOrientation);
        x = x - (2-indexOfCurrentOrientation)%2;
        y = y - (1 - indexOfCurrentOrientation)%2;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    @Override
    public int hashCode() {
        return ((currentOrientation.hashCode() + 17*x)* 31 + y);
    }

    @Override
    public boolean equals(Object foo) {
        if (!(foo instanceof UnitPosition))
            return false;
        if (foo == this)
            return true;
        UnitPosition fooUP = (UnitPosition)foo;
        return (fooUP.x == x && fooUP.y == y && fooUP.currentOrientation == currentOrientation);
    }

    public UnitPosition copy() {
        return new UnitPosition(x,y, currentOrientation);
    }
}
