package tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

import bicyclegarage.Bicycle;

public class TestBicycle extends Attributes {
    
    @Test
    public void testBicycleValid() {
        Bicycle b = new Bicycle("12345");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullID() {
        new Bicycle(null);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullTooShortID() {
        new Bicycle("123");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullTooLongID() {
        new Bicycle("123456");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullAlphaNumericID1() {
        new Bicycle("abc12");
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testBicycleNullAlphaNumericID2() {
        new Bicycle("12abc");
    }
    
    @Test
    public void testGetID() {
        Bicycle b = new Bicycle(validBicycleID);
        assertEquals(validBicycleID, b.getID());
        assertNotEquals(invalidBicycleID, b.getID());
    }

//    @Test
//    public void testGetOwner() {
//        User u = validUser;
//        Bicycle b = new Bicycle(validBicycleID, u);
//        assertEquals(u, b.getOwner());
//    }

}
