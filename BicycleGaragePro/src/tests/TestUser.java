package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import bicyclegarage.Bicycle;
import bicyclegarage.FullGarageException;
import bicyclegarage.Manager;
import bicyclegarage.User;

public class TestUser extends Attributes {
    
    private void reset() {
        validUser.getBicycles().clear();
        Manager.bicyclesRegistered = 0;
    }

    @Test
    public void testUser() {
        new User("Firstname", "Lastname", "199001011234", 1234);
    }
    
    /*
     * Add more tests for constructor. See TestBicycle class for examples.
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
    public void testAddBicycleTwiceAndNull() /*throws FullGarageException */{
        User u = validUser;
        Bicycle b = validBicycle;
        assertTrue(u.addBicycle(b));
        assertFalse(u.addBicycle(b));
        assertFalse(u.addBicycle(null));
        reset();
    }
    
    @Test
    public void testAddBicyclesMoreThanMax() {
        User u = validUser;
        boolean passed = false;
        for (int i = 0; i < Manager.MAX_BICYCLES + 1; ++i) {
            passed = u.addBicycle(
                    new Bicycle(String.format("%05d", i),
                                u));
        }
        assertFalse(passed);
        reset();
    }
    
    @Test
    public void testGetBicycleByID() throws FullGarageException {
        User u = validUser;
        Bicycle b = validBicycle;
        u.addBicycle(b);
        assertEquals(validBicycle, u.getBicycleByID(validBicycleID));
        reset();
    }

    @Test
    public void testIsValidNIN() {
        assertTrue(User.isValidNIN(validNIN));
        fail("Test implementation not finished");
    }

    @Test
    public void testIsValidPIN() {
        for (int i = 0; i < 10000; ++i)
            assertTrue(User.isValidPIN(i));
        assertFalse(User.isValidPIN(-1));
        assertFalse(User.isValidPIN(10000));
    }

}
