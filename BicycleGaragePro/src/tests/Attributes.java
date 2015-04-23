package tests;

import bicyclegarage.Bicycle;
import bicyclegarage.User;

public class Attributes {
    
    /*
     * User attributes
     */
    public final static String validFirstName      = "Per";
    public final static String validLastName       = "Holm";
    public final static String validNIN            = "199001011234";
    public final static int    validPIN            = 1234;
    
    /*
     * Bicycle attributes
     */
    public final static String validBicycleID      = "12345";
    public final static int    invalidBicycleID    = 12345;
    
    /*
     * Other
     */
    public final static User validUser = new User(
            validFirstName, validLastName, validNIN, validPIN);
    
    public final static Bicycle validBicycle = new Bicycle(
            validBicycleID, validUser);

}
