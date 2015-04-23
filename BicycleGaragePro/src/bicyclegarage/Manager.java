package bicyclegarage;

import interfaces.BarcodePrinter;
import interfaces.ElectronicLock;
import interfaces.PinCodeTerminal;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Manager {

    private Set<User> users;
    private static final int MAX_PINS = 10000;
    private static final int ERR_OUT_OF_PINS = -1;
    
    public Manager() {
        users = new HashSet<User>();
    }

    /**
     * Adds a new to the set of users of the garage.
     * Returns true if user could be added, i.e. the
     * garage is not full or user is not already registered.
     * @param firstName The first name(s) of the user
     * @param lastName  The last name(s) of the user
     * @param NIN       The National Identification Number of the user
     * @return true if garage is not full and user is not already registered
     */
    public boolean addUser(String firstName, String lastName, String NIN) {
        int pin = generatePIN();
        if (pin == -1) {
            return false;
        } else {
            return users.add(new User(firstName, lastName, NIN, pin));
        }
    }

    public void listAllUsers() {
        for (User u : users) {
            System.out.println(u.toString());
        }
    }

    public void entryBarcode(String code) {
        // TODO Auto-generated method stub

    }

    public void exitBarcode(String code) {
        // TODO Auto-generated method stub

    }

    public void entryCharacter(char c) {
        // TODO Auto-generated method stub

    }

    public void registerHardwareDrivers(BarcodePrinter printer,
            ElectronicLock entryLock, ElectronicLock exitLock,
            PinCodeTerminal terminal) {
        // TODO Auto-generated method stub

    }
    
    
    /* ===================================================================== *\
     *  PRIVATE METHODS
     * ===================================================================== */
    
    /**
     * Generate a unique PIN code (unique meaning it is not attached to
     * any user.
     * @return A unique PIN code if it could be generated, else -1
     */
    private int generatePIN() {
        Random r = new Random();
        int pinsGenerated = 0;
        int pin;
        
        do {
            if (pinsGenerated >= MAX_PINS) {
                System.err.println("Failure: Out of PIN codes.");
                return ERR_OUT_OF_PINS;
            }
            pin = r.nextInt(MAX_PINS);
            pinsGenerated += 1;
        } while (pinExists(pin));
        
        return pin;
    }
    
    /**
     * Returns true if any user in the set of users already has [PIN] attached
     * to them.
     * @param PIN The PIN code that shall be checked for existence
     * @return True if a user has [PIN] attached to them, else false
     */
    private boolean pinExists(int PIN) {
        for (User u : users) {
            if (u.getPIN() == PIN) {
                return true;
            }
        }
        return false;
    }

}
