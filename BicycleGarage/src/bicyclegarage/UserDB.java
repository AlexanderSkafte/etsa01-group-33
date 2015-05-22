package bicyclegarage;
import java.util.*;
import java.io.*;

public class UserDB {
	private ArrayList<User> users;
	
	/**
	 * Initializes the DB. Either load from file or create new list
	 * if DB is empty
	 */
	public UserDB() {
		if (!loadDB()) {
			System.err.println("Could not load DB. Creating new user list");
			this.users = new ArrayList<User>(); 
		}
	}
	
	/**
	 * Get the user list
	 * @return ArrayList list of User objects
	 */
	public ArrayList<User> getList() {
		return users;
	}
	
	public int getCheckedInCount() {
		int checkedInCount = 0;
		for (User u : users) {
			for (Bicycle b : u.getBicycles()) {
				if (b.isCheckedIn()) checkedInCount += 1;
			}
		}
		return checkedInCount;
	}
	
	/**
	 * Saves DB to file
	 * @return boolean Whether or not the operation succeeded
	 */
	public boolean saveDB() {
		try {
			FileOutputStream fos = new FileOutputStream("users");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(users);
			oos.close();
			fos.close();
			return true;
		} catch(IOException e) {
			System.out.println(e);
			return false;
		}
	}
	
	/**
	 * Loads DB from file 
	 * @return boolean Whether or not the operation succeeded
	 */
	public boolean loadDB() {
		try {
			FileInputStream fis = new FileInputStream("users");
			ObjectInputStream ois = new ObjectInputStream(fis);
			users = (ArrayList) ois.readObject();
			ois.close();
			fis.close();
			return true;
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			return false;
		} catch (IOException e) {
			System.out.println(e);
			return false;
		}
	}
	
	/**
	 * Prints the user DB
	 */
	public void printDB() {
		for (User u : users) {
			System.out.println(u.toString());
			u.printBicycles();
			System.out.println("\n======================================");
		}
	}
}
