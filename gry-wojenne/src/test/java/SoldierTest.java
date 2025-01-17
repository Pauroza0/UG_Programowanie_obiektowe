import org.example.Rank;
import org.example.Soldier;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SoldierTest {

    @Test
    void testGetStrength() {
        Soldier soldier = new Soldier(Rank.PRIVATE);
        assertEquals(1, soldier.getStrength());

        soldier.gainExperience();
        assertEquals(2, soldier.getStrength());
    }

    @Test
    void testGainExperienceAndPromotion() {
        Soldier soldier = new Soldier(Rank.PRIVATE);

        for (int i = 0; i < 3; i++) {
            soldier.gainExperience();
        }
        assertTrue(soldier.gainExperience());
        assertFalse(soldier.gainExperience());
        assertEquals(Rank.CORPORAL, soldier.getRank());
        assertEquals(4, soldier.getStrength());
    }

    @Test
    void testLoseExperience() {
        Soldier soldier = new Soldier(Rank.PRIVATE);
        soldier.loseExperience();
        assertFalse(soldier.loseExperience());
    }
}