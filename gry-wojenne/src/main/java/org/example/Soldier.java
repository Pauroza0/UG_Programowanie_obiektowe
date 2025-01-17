package org.example;

import java.io.Serializable;

public class Soldier implements Serializable, Unit {

    private Rank rank;
    private int experience;

    public Soldier(Rank rank) {
        this.rank = rank;
        this.experience = 1;
    }


    @Override
    public int getStrength() {
        return rank.getValue() * experience;
    }

    public boolean gainExperience() {
        experience++;
        if (experience == 5 * rank.getValue()) {
            promote();
            return true;
        }
        return false;
    }

    @Override
    public boolean loseExperience() {
        experience--;
        return experience > 0;
    }

    @Override
    public int getCost() {
        return getRank().getValue();
    }

    private void promote(){
        switch (rank) {
            case PRIVATE -> rank = Rank.CORPORAL;
            case CORPORAL -> rank = Rank.CAPTAIN;
            case CAPTAIN -> rank = Rank.MAJOR;
        }
        experience = 1;
    }
    public Rank getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Soldier(" +
                "RANK:" + rank +
                ", EXP=" + experience +
                ')';
    }
}



