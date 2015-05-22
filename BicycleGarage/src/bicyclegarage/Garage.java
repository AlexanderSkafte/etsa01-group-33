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

public class Garage {
    
	public Garage() {
		Manager manager = new Manager();
		ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
		ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
		BarcodePrinter printer = new BarcodePrinterTestDriver();
		PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
		BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
		BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
		
		manager.registerHardwareDrivers(printer, entryLock, exitLock, terminal);
		terminal.register(manager);
		readerEntry.register(manager);
		readerExit.register(manager);

		manager.printAll();
	}

	public static void main(String[] args) {
		new Garage();
	}
}