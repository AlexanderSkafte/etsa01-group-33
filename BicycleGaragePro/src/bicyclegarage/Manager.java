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

    /* (!) Globals */
    public static final int MAX_BICYCLES = 500;
    public static int bicyclesRegistered = 0;
    
    
    public Manager() {
        users = new HashSet<User>();
    }

    /**
     * Adds a new user to the set of users of the garage.
     * Returns true if user could be added, i.e. the
     * garage is not full or user is not already registered.
     * @param firstName The user's first name(s)
     * @param lastName  The user's last name(s)
     * @param NIN       The user's National Identification Number
     * @return true if garage is not full and user is not already registered,
     *         false otherwise
     */
    public boolean addUser(String firstName, String lastName, String NIN) {
        int pin = generatePIN();
        if (pin == -1) {
            return false;
        } else {
            return users.add(new User(firstName, lastName, NIN, pin));
        }
    }

    /**
     * Remove/unregisters the user identified by [NIN] and returns true if the
     * user was removed, else returns false.
     * @param NIN The user's National Identification number
     * @return True if user was removed, false otherwise
     */
    public boolean removeUser(String NIN) {
        User u = getUserByNIN(NIN);
        if (u == null) {
            System.err.println("[!] No user with the NIN \"" + NIN
                    + "\" was found.");
            return false;
        }
        return users.remove(u);
    }

    /**
     * Returns the user identified by [NIN] if it could be found, else null.
     * @param NIN The user's National Identification Number
     * @return The user identified by [NIN] if found, else null
     */
    public User getUserByNIN(String NIN) {
        for (User u : users) {
            if (u.getNIN().equals(NIN)) {
                return u;
            }
        }
        return null;
    }

    /**
     * Returns a newline-delimited list of all users. If there are no users,
     * this method just returns and empty string ("").
     * @return A newline-delimited list of all users, or an empty string if
     *         there are no users.
     */
    public String listAllUsers() {
        if (users.isEmpty()) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (User u : users) {
            sb.append(u.toString()).append("\n");
        }
        return sb.toString();
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
