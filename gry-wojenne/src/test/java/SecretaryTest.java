import org.example.Secretary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecretaryTest {

    @Test
    void testSingleton() {
        Secretary instance1 = Secretary.getInstance();
        Secretary instance2 = Secretary.getInstance();
        assertSame(instance1, instance2);
    }
}