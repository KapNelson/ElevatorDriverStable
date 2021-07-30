package com.sytoss.edu2021.bom;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sytoss.edu2021.common.Direction;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;

@Setter
@Getter
public class RouteBOM {

    private Set<Integer> queueOfFloors;
    private Direction direction = Direction.STABLE;

    public RouteBOM() {
        queueOfFloors = new TreeSet<>();
    }

    public void addRoutFloor(int currentFloor, int floorNumber) {
        if (queueOfFloors.contains(floorNumber)) {
            return;
        }
        if (direction.equals(Direction.UP) && floorNumber < this.getMinValue()) {
            return;
        }
        if (direction.equals(Direction.DOWN) && floorNumber > this.getMaxValue()) {
            return;
        }

        setDirection(currentFloor, floorNumber);
        if (currentFloor != floorNumber)
            queueOfFloors.add(floorNumber);
    }

    public void clearRoute() {
        direction = Direction.STABLE;
        queueOfFloors.clear();
    }

    public void setDirection(int initFloor, int aimFloor) {

        if (initFloor < aimFloor)
            direction = Direction.UP;
        else if (initFloor > aimFloor)
            direction = Direction.DOWN;
        else {
            direction = Direction.STABLE;
        }
    }

    @JsonIgnore
    public int getMaxValue() {
        return Collections.max(queueOfFloors);
    }

    @JsonIgnore
    public int getMinValue() {
        return Collections.min(queueOfFloors);
    }
}
