import org.example.General;
import org.example.Rank;
import org.example.Secretary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GeneralTest {

    @Test
    void testBuySoldier() {
        Secretary secretary = Secretary.getInstance();
        General general = new General("General 1", 100, secretary);

        general.buySoldier(Rank.PRIVATE);
        general.buySoldier(Rank.MAJOR);
        assertEquals(2, general.getArmy().getUnits().size());
        assertEquals(50, general.getGold());
    }

    @Test
    void testTrainArmy() {
        Secretary secretary = Secretary.getInstance();
        General general = new General("General 1", 100, secretary);

        general.buySoldier(Rank.PRIVATE);
        general.buySoldier(Rank.MAJOR);
        general.trainUnits(general.getArmy().getUnits());

        assertEquals(10, general.getArmy().calculateTotalStrength());
        assertEquals(45, general.getGold());
    }

    @Test
    void testAttackSuccess() {
        Secretary secretary = Secretary.getInstance();
        General general1 = new General("General 1", 100, secretary);
        General general2 = new General("General 2", 100, secretary);

        general1.buySoldier(Rank.MAJOR);
        general2.buySoldier(Rank.CAPTAIN);

        boolean result = general1.attack(general2);
        assertTrue(result);
        assertEquals(67, general1.getGold());
        assertEquals(63, general2.getGold());
    }

    @Test
    void testAttackFailure() {
        Secretary secretary = Secretary.getInstance();
        General general1 = new General("General 1", 100, secretary);
        General general2 = new General("General 2", 100, secretary);

        general1.buySoldier(Rank.CAPTAIN);
        general2.buySoldier(Rank.MAJOR);

        boolean result = general1.attack(general2);
        assertFalse(result);
        assertEquals(63, general1.getGold());
        assertEquals(67, general2.getGold());
    }
}