package bicyclegarage;

import interfaces.BarcodePrinter;
import interfaces.BarcodeReader;
import interfaces.ElectronicLock;
import interfaces.PinCodeTerminal;
import testdrivers.BarcodePrinterTestDriver;
import testdrivers.BarcodeReaderEntryTestDriver;
import testdrivers.BarcodeReaderExitTestDriver;
import testdrivers.ElectronicLockTestDriver;
import testdrivers.PinCodeTerminalTestDriver;

public class BicycleGarage {
    public BicycleGarage() {
        BicycleGarageManager manager = new BicycleGarageManager();
        ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
        ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
        BarcodePrinter printer = new BarcodePrinterTestDriver();
        PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
        manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
        terminal.register(manager);
        BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
        BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
        readerEntry.register(manager);
        readerExit.register(manager);
    }
    public static void main(String[] args) {
        new BicycleGarage();
    }
}