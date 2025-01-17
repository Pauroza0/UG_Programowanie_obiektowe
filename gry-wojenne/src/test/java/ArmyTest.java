import org.example.Army;
import org.example.Rank;
import org.example.Soldier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ArmyTest {

    @Test
    void testAddAndRemoveUnit() {
        Army army = new Army();
        Soldier soldier = new Soldier(Rank.PRIVATE);

        army.addUnit(soldier);
        assertEquals(1, army.getUnits().size());

        army.removeUnit(soldier);
        assertEquals(0, army.getUnits().size());
    }

    @Test
    void testCalculateTotalStrength() {
        Army army = new Army();
        army.addUnit(new Soldier(Rank.PRIVATE));
        army.addUnit(new Soldier(Rank.CORPORAL));

        assertEquals(3, army.calculateTotalStrength());
    }

    @Test
    void testRemoveRandomUnit() {
        Army army = new Army();
        army.addUnit(new Soldier(Rank.PRIVATE));
        army.addUnit(new Soldier(Rank.CORPORAL));

        army.removeRandomUnit();
        assertEquals(1, army.getUnits().size());
    }
}