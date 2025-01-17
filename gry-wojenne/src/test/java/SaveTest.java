import org.example.General;
import org.example.Rank;
import org.example.Secretary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.File;

public class SaveTest {
    @Test
    void testSaveAndLoadState() {
        Secretary secretary = Secretary.getInstance();
        General original = new General("General 1", 100, secretary);

        original.buySoldier(Rank.PRIVATE);
        original.buySoldier(Rank.CAPTAIN);

        String filename = "test_general.dat";
        original.saveStateToFile(filename);

        File file = new File(filename);
        assertTrue(file.exists());

        General loaded = General.loadStateFromFile(filename, secretary);

        assertNotNull(loaded);
        assertEquals(original.getGold(), loaded.getGold());
        assertEquals(original.getArmy().getUnits().size(), loaded.getArmy().getUnits().size());
        assertEquals(original.getArmy().calculateTotalStrength(), loaded.getArmy().calculateTotalStrength());
    }
}
