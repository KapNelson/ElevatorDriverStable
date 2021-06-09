package com.sytoss.edu2021.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
public class Route {

    private List<Integer> queueOfFloors;
    private Direction direction = Direction.STABLE;
    public Route() {
        queueOfFloors = new ArrayList<>();
    }
    public void addRoutFloor(int currentFloor, int floorNumber) {
        if (queueOfFloors.contains(floorNumber)) {
            return;
        }
        setDirection(currentFloor, floorNumber);
        queueOfFloors.add(floorNumber);
        queueOfFloors.sort(Collections.reverseOrder());
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
            System.err.println("You have already reached this floor.");
            direction = Direction.STABLE;
        }

    }

}