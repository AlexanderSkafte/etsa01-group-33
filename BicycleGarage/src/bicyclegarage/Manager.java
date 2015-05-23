package bicyclegarage;
import java.util.*;
import interfaces.ElectronicLock;
import interfaces.PinCodeTerminal;

import java.util.Random;

public class Manager {

    private static final int MAX_PINS = 9999;
    private static final int ERR_OUT_OF_PINS = -1;
    
    /* Hardware */
    private ElectronicLock entryLock;
    private ElectronicLock exitLock;
    private PinCodeTerminal terminal;
    
    /* Random */
    private static Random rand = new Random();
    
    /* User DB */
    public UserDB uDB = new UserDB();
    public ArrayList<User> users = uDB.getList();
    
    public Manager() {
    	// Do nothing
    }
    
    /**
     * Generates a random integer between min and max (inclusive)
     * @param min minimum integer
     * @param max maximum integer
     * @return the integer that is generated
     */
    public static int randInt(int min, int max) {
        int randomNum = rand.nextInt((max - min) + 1) + min;
        return randomNum;
    }
    
    /**
     * Check in bicycle by ID
     * @param ID bicycle ID
     * @return true if the bicycle is checked in
     */
    public boolean checkInBicycle(String ID) {
    	Bicycle bicycle = findBicycle(ID);
    	if (bicycle != null) return bicycle.checkIn();
    	return false;
    }
    
    /**
     * Check out bicycle by ID
     * @param ID bicycle ID
     * @return true if the bicycle is checked out
     */
    public boolean checkOutBicycle(String ID) {
    	Bicycle bicycle = findBicycle(ID);
    	if (bicycle != null) return bicycle.checkOut();
    	return false;
    }
    
    /**
     * Find bicycle by ID
     * @param ID bicycle ID
     * @return Bicycle object containing the bicycle info
     */
    public Bicycle findBicycle(String ID) {
    	for (User u : users) {
    		for (Bicycle b : u.getBicycles()) {
    			if (b.getID().equals(ID)) return b;
    		}
    	}
    	return null;
    }
    
    /**
     * Adds bicycle to a user
     * @param user User that the bicycle should be coupled with
     * @return String containing the bicycle ID that was generated
     */
    public String addBicycle(User user) {
    	int counter = 0;
    	int tempID = randInt(10000, 99999);
    	
    	while (bicycleIDExists(Integer.toString(tempID))) {
    		if (counter > 89999) {
    			// No IDS left
    			return null;
    		}
    		tempID = randInt(10000, 99999);
    		counter++;
    	}
    	
    	user.addBicycle(Integer.toString(tempID));
    	uDB.saveDB();
    	return Integer.toString(tempID);
    }
    
    /**
     * Removes bicycle based on bicycle ID
     * @param ID bicycle ID
     * @return true if the bicycle is removed
     */
    public boolean removeBicycle(String ID) {
    	for (User u : users) {
    		if (u.removeBicycle(ID)){
    			uDB.saveDB();
    			return true;
    		}
    	}
    	return false;
    }
    
    public void clearUserBicycles(User user) {
    	user.clearBicycles();
    	uDB.saveDB();
    }
    
    /**
     * Checks if a bicycle ID exists
     * @param ID bicycle ID
     * @return true if bicycle ID exists
     */
    public boolean bicycleIDExists(String ID) {
    	for (User u : users) {
    		for (Bicycle b : u.getBicycles()) {
    			if (b.getID().equals(ID)) return true;
    		}
    	}
    	return false;
    }
    
    /**
     * Counts how many bicycles that are currently checked in
     * @return int on how many bicycles that are checked in
     */
    public int checkedInBicycles() {
    	int checkedInCount = 0;
    	for (User u : users) {
    		for (Bicycle b : u.getBicycles()) {
    			if (b.isCheckedIn()) checkedInCount++;
    		}
    	}
    	return checkedInCount;
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
        	if (NINExists(NIN)) {
        		// User exists
        		return false;
        	}
            users.add(new User(firstName, lastName, NIN, pin));
            return uDB.saveDB();
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
            // No user found
            return false;
        }
        users.remove(u);
        return uDB.saveDB();
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
    
    public boolean NINExists(String NIN) {
    	return getUserByNIN(NIN) instanceof User;
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
    
    /**
     * Get UserDB object
     * @return UserDB object
     */
    public UserDB getDB() {
    	return uDB;
    }

    public void entryBarcode(String code) {
        // TODO Auto-generated method stub
    	if (bicycleIDExists(code)) {
    		Bicycle bicycle = findBicycle(code);
    		bicycle.checkIn();
    		entryLock.open(10);
    		uDB.saveDB();
    	}
    }

    public void exitBarcode(String code) {
        // TODO Auto-generated method stub
    	if (bicycleIDExists(code)) {
    		Bicycle bicycle = findBicycle(code);
    		bicycle.checkOut();
    		exitLock.open(10);
    		uDB.saveDB();
    	}
    }

    /**
     * Handles PIN entry at the entrance
     * @param c PIN code that is entered
     */
    
    private Date startTimer = null;
    private String tempPIN = "";
    public void entryCharacter(char c) {
        // TODO Auto-generated method stub
    	if (startTimer  == null) {
    		startTimer = new Date();
		} else if (startTimer.getTime() + 1000 * 5 < new Date().getTime()) {
			startTimer = null;
			tempPIN = "";
			return;
		}
    	
    	tempPIN += c;
    	if (tempPIN.length() == 4) {
        	if (pinExists(Integer.parseInt(tempPIN))) {
        		entryLock.open(10);
        		terminal.lightLED(PinCodeTerminal.GREEN_LED, 4);
        	} else {
        		terminal.lightLED(PinCodeTerminal.RED_LED, 4);
        	}
        	tempPIN = "";
        	startTimer = null;
    	}
    }
    
    /*
     * Registers hardware drivers
     */
    public void registerHardwareDrivers(
            ElectronicLock entryLock,
            ElectronicLock exitLock,
            PinCodeTerminal terminal) {
        // TODO Auto-generated method stub
    	this.entryLock = entryLock;
    	this.exitLock = exitLock;
    	this.terminal = terminal;
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
        int pinsGenerated = 0;
        int pin;

        do {
            if (pinsGenerated >= MAX_PINS) {
                // No more pins
                return ERR_OUT_OF_PINS;
            }
            pin = randInt(1000, MAX_PINS);
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
