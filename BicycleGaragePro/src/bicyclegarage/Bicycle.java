package bicyclegarage;

public class Bicycle {
    private final String ID;
    private final User owner;

    // Add these eventually
    // private Date lastCheckIn;
    // private boolean isCheckedIn;

    /**
     * Creates a new Bicycle. Bicycle ID and owner can't be changed after
     * initialization.
     * @param bicycleID The 5-digit ID number of the bicycle
     * @param owner     The bicycle's owner
     */
    public Bicycle(String ID, User owner) {
        if (ID == null || owner == null) {
            throw new IllegalArgumentException(
                    "Error! Null parameter to Bicycle constructor.");
        }
        if (!ID.matches("\\d{5}")) {
            throw new IllegalArgumentException(
                    "Error! Length of bicycle ID must be exactly 5 digits.");
        }
        
        this.ID = ID;
        this.owner = owner;
        // isCheckedIn = false;
    }

    /**
     * Get the bicycle's 5-digit ID number.
     * @return The bicycle's 5-digit ID number.
     */
    public String getID() {
        return ID;
    }

    /**
     * Get the owner of the bicycle.
     * @return The owner of the bicycle.
     */
    public User getOwner() {
        return owner;
    }

    // Check in and check out should be handled by the manager, right?

    // void checkIn(Date date) {
    //
    // isCheckedIn = true;
    // }
    //
    // void checkOut(Date date) {
    //
    // isCheckedIn = false;
    // }
}
