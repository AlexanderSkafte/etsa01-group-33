package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import bicyclegarage.Bicycle;
import bicyclegarage.User;

public class TestBicycle extends Attributes {
    
    @Test
    public void testBicycleValid() {
        Bicycle b = new Bicycle("12345", validUser);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullID() {
        new Bicycle(null, new User(validFirstName, validLastName, validNIN, validPIN));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullTooShortID() {
        new Bicycle("123", new User(validFirstName, validLastName, validNIN, validPIN));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullTooLongID() {
        new Bicycle("123456", new User(validFirstName, validLastName, validNIN, validPIN));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullAlphaNumericID1() {
        new Bicycle("abc12", new User(validFirstName, validLastName, validNIN, validPIN));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullAlphaNumericID2() {
        new Bicycle("12abc", new User(validFirstName, validLastName, validNIN, validPIN));
    }
    
    @Test
    public void testGetID() {
        Bicycle b = new Bicycle(validBicycleID, validUser);
        assertEquals(validBicycleID, b.getID());
        assertNotEquals(invalidBicycleID, b.getID());
    }

    @Test
    public void testGetOwner() {
        User u = validUser;
        Bicycle b = new Bicycle(validBicycleID, u);
        assertEquals(u, b.getOwner());
    }

}
