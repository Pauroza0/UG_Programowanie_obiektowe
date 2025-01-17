package org.example;

public enum Rank {
    PRIVATE(1), CORPORAL(2), CAPTAIN(3), MAJOR(4);

    private final int value;

    Rank(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
