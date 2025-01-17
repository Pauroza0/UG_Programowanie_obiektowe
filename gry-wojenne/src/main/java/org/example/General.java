package org.example;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class General implements Serializable {
    private Army army;
    private int gold;
    private transient Secretary secretary;
    private final String name;

    public General(String name, int initialGold, Secretary secretary) {
        this.name = name;
        this.army = new Army();
        this.gold = initialGold;
        this.secretary = secretary;
    }

    public void trainUnits(List<Unit> units) {
        for (Unit unit : units) {
            if (army.getUnits().contains(unit) && gold >= unit.getCost()) {
                unit.gainExperience();
                gold -= unit.getCost();
                secretary.logAction(name,"Training", "Unit trained: " + unit);
            }
        }
    }

    public boolean attack(General opponent) {
        int myStrength = army.calculateTotalStrength();
        int opponentStrength = opponent.army.calculateTotalStrength();

        secretary.logAction(name, "Attack", "[START] My strenght: " + myStrength);

        if (myStrength > opponentStrength) {
            Iterator<Unit> iterator = opponent.army.getUnits().iterator();
            while (iterator.hasNext()) {
                Unit unit = iterator.next();
                if (!unit.loseExperience()) {
                    iterator.remove();
                    secretary.logAction(name, "Attack", "Opponent lost a unit");
                }
            }
            secretary.logAction(name, "Attack", "[SUCCESS] Opponent lost experience.");
            opponent.transferGold(this, 10);
            return true;
        } else if (myStrength < opponentStrength) {
            Iterator<Unit> iterator = army.getUnits().iterator();
            while (iterator.hasNext()) {
                Unit unit = iterator.next();
                if (!unit.loseExperience()) {
                    iterator.remove();
                    secretary.logAction(name, "Attack", "Lost a unit");
                }
            }
            secretary.logAction(name,"Attack", "[FAILED] Lost experience.");
            transferGold(opponent, 10);
            return false;
        } else {
            army.removeRandomUnit();
            opponent.army.removeRandomUnit();
            secretary.logAction(name, "Attack", "[DRAW] Both sides lost one random unit");
            return false;
        }
    }

    public void buySoldier(Rank rank) {
        int cost = 10 * rank.getValue();
        if (gold >= cost) {
            army.addUnit(new Soldier(rank));
            gold -= cost;
            secretary.logAction(name, "Recruit", "Bought soldier: " + rank);
        }
    }

    private void transferGold(General to, int percentage) {
        int amount = gold * percentage / 100;
        this.gold -= amount;
        to.gold += amount;
        secretary.logAction(name, "Gold Transfer", "Transferred " + amount + " gold to opponent");
    }

    public int getGold() {
        return gold;
    }

    public void printReport() {
        System.out.println("Gold: " + gold);
        System.out.println("Army size: " + army.getUnits().size());
        System.out.println("Army strength: " + army.calculateTotalStrength());
    }

    public void saveStateToFile(String filename) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        } catch (IOException e) {
            System.err.println("Error saving state to file: " + e.getMessage());
        }
    }

    public static General loadStateFromFile(String filename, Secretary secretary) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            General general = (General) ois.readObject();
            general.secretary = secretary;
            return general;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading state from file: " + e.getMessage());
            return null;
        }
    }

    public Army getArmy() {
        return  army;
    }
}
