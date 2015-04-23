package bicyclegarage;

import java.util.Date;

public class Bicycle {
    private final String ID;
//    private final User owner;

    private Date lastCheckedIn;
    private Date lastCheckedOut;
    private boolean isCheckedIn;

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
//        this.owner = owner;
        // isCheckedIn = false;
    }

    /**
     * Get the bicycle's 5-digit ID number.
     * @return The bicycle's 5-digit ID number.
     */
    public String getID() {
        return ID;
    }

//    /**
//     * Get the owner of the bicycle.
//     * @return The owner of the bicycle.
//     */
//    public User getOwner() {
//        return owner;
//    }

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
