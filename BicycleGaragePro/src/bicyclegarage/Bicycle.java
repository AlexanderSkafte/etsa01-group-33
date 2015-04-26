package bicyclegarage;

import java.util.Date;

public class Bicycle {
    private final String ID;
    private Date lastCheckedIn;
    private Date lastCheckedOut;
    private boolean isCheckedIn;

    /**
     * Creates a new Bicycle. Bicycle ID and owner can't be changed after
     * initialization.
     * @param bicycleID The 5-digit ID number of the bicycle
     */
    public Bicycle(String ID) {
        if (ID == null) {
            throw new IllegalArgumentException(
                    "Error! Null parameter to Bicycle constructor.");
        }
        if (!ID.matches("\\d{5}")) {
            throw new IllegalArgumentException(
                    "Error! Length of bicycle ID must be exactly 5 digits.");
        }

        this.ID = ID;
        // isCheckedIn = false;
    }

    /**
     * Get the bicycle's 5-digit ID number.
     * @return The bicycle's 5-digit ID number.
     */
    public String getID() {
        return ID;
    }

    public void checkIn() {
        if (isCheckedIn)
            return;
        lastCheckedIn = new Date();
        isCheckedIn = true;
    }

    public void checkOut() {
        lastCheckedOut = new Date();
        isCheckedIn = false;
    }
}
