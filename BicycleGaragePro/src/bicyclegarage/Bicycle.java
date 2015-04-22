package bicyclegarage;

public class Bicycle {
	private final String ID; // 5-digit ID number.
	private final User owner;

	// Add these eventually
//	private Date lastCheckIn;
//	private boolean isCheckedIn;

	/**
	 * Creates a new Bicycle.
	 * Bicycle ID and owner can't be changed after initialization.
	 * @param bicycleID	The 5-digit identification number of the bicycle
	 * @param owner		The bicycle's owner
	 */
	public Bicycle(String bicycleID, User owner) {
		ID = bicycleID;
		this.owner = owner;
//		isCheckedIn = false;
	}
	
	public String getID() {
		return ID;
	}
	
	public User getOwner() {
		return owner;
	}
	
	// Check in and check out should be handled by the manager, right?
	
//	void checkIn(Date date) {
//		
//		isCheckedIn = true;
//	}
//	
//	void checkOut(Date date) {
//		
//		isCheckedIn = false;
//	}
}
