package bfst2023.handins;

import org.junit.jupiter.api.Test;

import bfst2023.handins.Model.Address;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void simple() {
        var addr = Address.parse("Finsensvej 50, 2000 Frederiksberg");
        assertEquals("Finsensvej", addr.street);
        assertEquals("50", addr.house);
        assertEquals("2000", addr.postcode);
        assertEquals("Frederiksberg", addr.city);
    }

    @Test
    void simplespaces() {
        var addr = Address.parse("Finsensvej        50, 2000 Frederiksberg");
        assertEquals("Finsensvej", addr.street);
        assertEquals("50", addr.house);
        assertEquals("2000", addr.postcode);
        assertEquals("Frederiksberg", addr.city);
    }
}
