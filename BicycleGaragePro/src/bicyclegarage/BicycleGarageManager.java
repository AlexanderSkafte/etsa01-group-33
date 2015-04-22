package bicyclegarage;

import interfaces.BarcodePrinter;
import interfaces.ElectronicLock;
import interfaces.PinCodeTerminal;

import java.util.Set;

public class BicycleGarageManager {

    private Set<User> users;

    public boolean addUser(User user) {
        return users.add(user);
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

}
