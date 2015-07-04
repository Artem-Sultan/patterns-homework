package ru.sbt.test.refactoring.manager;

import ru.sbt.test.refactoring.containers.UnitPosition;
import ru.sbt.test.refactoring.units.FieldUnit;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by artem on 04.07.15.
 */
public class MapManager {
    private final int xDim;
    private final int yDim;
    private Map<Tuple, FieldUnit> gameMap;

    public MapManager(int xDim, int yDim) {
        this.xDim = xDim;
        this.yDim = yDim;
        gameMap = new HashMap<>();
    }

    public void addUnitToMapIfPossible(UnitPosition unitPosition, FieldUnit fieldUnit) {
        if (positionIsValid(unitPosition)) {
            gameMap.put(new Tuple(unitPosition.getX(), unitPosition.getY()),fieldUnit);
        }
        else throw new IllegalArgumentException("Indexes out of range or slot is occupied.  " + unitPosition);
    }
    
    public boolean positionIsValid(UnitPosition unitPosition) {
        int x = unitPosition.getX();
        int y = unitPosition.getY();
        return (validateX(x) && validateY(y) && !gameMap.containsKey(new Tuple(unitPosition)));
    }

    private boolean validateY(int y) {
        return !(y < 0 || y >= yDim);
    }

    private boolean validateX(int x) {
        return !(x < 0 || x >= xDim);
    }


    public void updatePosition(FieldUnit fieldUnit, UnitPosition oldPosition) {
        Tuple oldXY = new Tuple(oldPosition);
        gameMap.remove(oldXY);
        Tuple newXY = new Tuple(fieldUnit.getUnitPosition());
        gameMap.put(newXY, fieldUnit);
    }

    private class Tuple {
        public final int x;
        public final int y;

        public Tuple(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Tuple(UnitPosition unitPosition) {
            x = unitPosition.getX();
            y = unitPosition.getY();
        }

        @Override
        public int hashCode(){
            return 31*x + y;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Tuple))
                return false;
            if (o == this)
                return true;
            Tuple foo = (Tuple)o;
            return (foo.x == x && foo.y == y);
        }
    }
}
