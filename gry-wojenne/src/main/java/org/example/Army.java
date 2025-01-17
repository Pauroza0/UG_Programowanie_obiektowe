package org.example;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Army implements Serializable {
    private final List<Unit> units = new ArrayList<>();

    public void addUnit(Unit unit) {
        units.add(unit);
    }

    public void removeUnit(Unit unit) {
        units.remove(unit);
    }
    public int calculateTotalStrength() {
        return units.stream().mapToInt(Unit::getStrength).sum();
    }

    public List<Unit> getUnits() {
        return units;
    }

    public void removeRandomUnit() {
        if (!units.isEmpty()) {
            Random random = new Random();
            units.remove(random.nextInt(units.size()));
        }
    }
}
