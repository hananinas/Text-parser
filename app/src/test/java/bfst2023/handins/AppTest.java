/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package bfst2023.handins;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test void appHasAGreeting() {
        App classUnderTest = new App();
        assertNotNull(classUnderTest.getGreeting(), "app should have a greeting");
    }
    @Test void simple() {
        var addr = Address.parse("Finsensvej 50, 2000 Frederiksberg");
        assertEquals("Finsensvej", addr.street);
        assertEquals("50", addr.house);
        assertEquals("2000", addr.postcode);
        assertEquals("Frederiksberg", addr.city);
    }

}
