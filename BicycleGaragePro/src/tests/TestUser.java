package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import bicyclegarage.Bicycle;
import bicyclegarage.User;

public class TestUser extends Attributes {

    @Test
    public void testUser() {
        new User("Firstname", "Lastname", "199001011234", 1234);
    }
    
    /*
     * Add more tests for constructor. See TestBicycle class.
     */
    
    @Test
    public void testToString() {
        User u = validUser;
        assertEquals(validLastName + ", " + validFirstName + " (NIN: "
        + validNIN + ", PIN: " + validPIN + ")", u.toString());
    }

    @Test
    public void testGetNIN() {
        User u = validUser;
        assertEquals(validNIN, u.getNIN());
    }
    
    @Test
    public void testGetFirstName() {
        User u = validUser;
        assertEquals(validFirstName, u.getFirstName());
    }

    @Test
    public void testGetLastName() {
        User u = validUser;
        assertEquals(validLastName, u.getLastName());
    }
    
    @Test
    public void testGetPIN() {
        User u = validUser;
        assertEquals(validPIN, u.getPIN());
    }

    @Test
    public void testGetBicycles() {
        fail("Not yet implemented"); // TODO
    }

    @Test
    public void testAddBicycle() {
        User u = validUser;
        Bicycle b = validBicycle;
        assertTrue(u.addBicycle(b));
        assertFalse(u.addBicycle(b)); // Bicycle is already in garage, so fail
        assertFalse(u.addBicycle(null));
    }

    @Test
    public void testGetBicycleByID() {
        User u = validUser;
        Bicycle b = validBicycle;
        u.addBicycle(b);
        assertEquals(validBicycle, u.getBicycleByID(validBicycleID));
    }

    /* TODO
     *  Implement further! */
    @Test
    public void testIsValidNIN() {
        assertTrue(User.isValidNIN(validNIN));
    }

    @Test
    public void testIsValidPIN() {
        for (int i = 0; i < 10000; ++i)
            assertTrue(User.isValidPIN(i));
        assertFalse(User.isValidPIN(-1));
        assertFalse(User.isValidPIN(10000));
    }

}
