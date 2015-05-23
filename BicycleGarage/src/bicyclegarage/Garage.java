package bicyclegarage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

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
	private Manager manager = new Manager();
	private ElectronicLock entryLock = new ElectronicLockTestDriver("Entry lock");
	private ElectronicLock exitLock = new ElectronicLockTestDriver("Exit lock");
	private BarcodePrinter printer = new BarcodePrinterTestDriver();
	private PinCodeTerminal terminal = new PinCodeTerminalTestDriver();
	private BarcodeReader readerEntry = new BarcodeReaderEntryTestDriver();
	private BarcodeReader readerExit = new BarcodeReaderExitTestDriver();
	
	public Garage() {
		manager.registerHardwareDrivers(entryLock, exitLock, terminal);
		terminal.register(manager);
		readerEntry.register(manager);
		readerExit.register(manager);
		
		UserDB uDB = manager.getDB();
		
		/*
		 * Roll out the frame
		 */
		JFrame frame = new JFrame("Bicycle Garage Pro");
		frame.getContentPane().setBackground(new Color(77, 72, 62));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(300, 300);
		frame.setSize(610, 470);
		frame.setResizable(false);
		
		/*
		 * Textarea
		 */
		JTextArea textarea = new JTextArea(30, 80);
		JScrollPane scrollPane = new JScrollPane(textarea);
		scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		textarea.setFont(new Font("Consolas", Font.BOLD, 12));
		textarea.setForeground(Color.WHITE);
		textarea.setMargin(new Insets(10,10,10,10));
		textarea.setEditable(false);
		textarea.setLineWrap(true);
		textarea.setBackground(new Color(77, 72, 62));
		textarea.setBorder(new CompoundBorder(BorderFactory.createLineBorder(new Color(77, 72, 62), 5), new EmptyBorder(8, 10, 8, 10)));

		/*
		 * Get the ASCII art rollin'       
		 */
		textarea.setText(" _ __                 _       ,___                      _ __      \r\n( /  )o              //      /   /                     ( /  )     \r\n /--<,  _, __  , _, // _    /  ____,  _   __,  _,  _    /--'_   __\r\n/___/(_(__/ (_/_(__(/_(/_  (___/(_/(_/ (_(_/(_(_)_(/_  /   / (_(_)\r\n             /                                 /|                 \r\n            '                                 (/                  \n");
		textarea.append("Type \"help\" to get a list of available commands\n\n");
		frame.add(scrollPane, BorderLayout.NORTH);
		
		/*
		 * Command input field
		 */
		
		JTextField inputfield = new JTextField();
		inputfield.setFont(new Font("Consolas", Font.BOLD, 12));
		inputfield.setMargin(new Insets(50,50,50,50));
		Border line = BorderFactory.createLineBorder(new Color(77, 72, 62), 5);
		Border empty = new EmptyBorder(10, 10, 10, 10);
		CompoundBorder border = new CompoundBorder(line, empty);
		inputfield.setBorder(border);
		inputfield.addKeyListener(new KeyListener() {
			ArrayList<String> commandHistory = new ArrayList<String>();
			int commandHistoryOffset = 0;
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				switch (e.getKeyCode()) {
				case 27: // ESC
					inputfield.setText("");
					break;
				case 38: // Up arrow
					if (commandHistory.size() - commandHistoryOffset == 0){
						// do nothing
					} else {
						commandHistoryOffset += 1;
						inputfield.setText(commandHistory.get(commandHistory.size() - commandHistoryOffset));
					}
					break;
				case 40: // Down arrow
					if (commandHistoryOffset == 0) {
						// Do nothing
					} else if (commandHistoryOffset == 1) {
						inputfield.setText("");
						commandHistoryOffset = 0;
					} else {
						commandHistoryOffset -= 1;
						inputfield.setText(commandHistory.get(commandHistory.size() - commandHistoryOffset));
					}
					break;
				case 10:
					if (!inputfield.getText().trim().equals("")){
						// If previous command was the same, skip adding
						int size = commandHistory.size();
						if (size > 0 && inputfield.getText().equals(commandHistory.get(size - 1))) {
							// No dupes
						} else {
							commandHistory.add(inputfield.getText());
						}
						commandHistoryOffset = 0;	
					}
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		inputfield.addActionListener(new ActionListener() {
	    public void actionPerformed(ActionEvent e) {
	    if (inputfield.getText().trim().equals("")) return;
	    String[] input = inputfield.getText().split(" ");
	    
	    /*
	     * If we have an invalid command, stop.
	     */
	    if (input.length > 2){
	    	inputfield.setText("");
	    	return;
	    }
	    
	    /*
	     * Split text into command + argument
	     */
	    String command = input[0];
	    String argument = "";
	    
	    /*
	     * Some commands don't require an argument
	     */
	    if (input.length == 2) {
	    	 argument = input[1];
	    }
	    
	    /*
	     * If we have a non-empty command, clear console
	     */
    	if(inputfield.getText() != "") {
    		textarea.append("> " + inputfield.getText() + "\n");
    	}
    	
    	/*
    	 * Bunch of commands :D
    	 */
    	/*
    	 * Covered:
    	 * list
    	 * clear
    	 * addUser
    	 * getUser
    	 * removeUser
    	 * addBicycle
    	 * getBicycle
    	 * removeBicycle
    	 * printBarcode
    	 */
	       switch (command) {
	       case "help": {
	    	   textarea.append("Following commands are supported: \n");
	    	   textarea.append("  list                              List all users and their bikes\n");
	    	   textarea.append("  clear                             Clear the console\n");
	    	   textarea.append("  addUser <firsname;lastname;NIN>   Add a new user\n");
	    	   textarea.append("  getUser <NIN>                     Get user by NIN\n");
	    	   textarea.append("  removeUser <NIN>                  Remove user by NIN\n");
	    	   textarea.append("  addBicycle <NIN>                  Add a new bicycle to user by NIN\n");
	    	   textarea.append("  getBicycle <bicycle ID>           Get bicycle by bicycle ID\n");
	    	   textarea.append("  removeBicycle <bicycle ID>        Remove bicycle by bicycle ID\n");
	    	   textarea.append("  printBarcode <bicycle ID>         Print new barcode by bicycle ID\n");
	    	   textarea.append("Example usage (only select few):\n");
	    	   textarea.append("  addUser John;Smith;1234567891");
	    	   textarea.append("  addBicycle 1234567891");
	    	   break;
	       }
	       case "list":
	    	   String stuffToShow = uDB.getDBPrint();
	    	   if (stuffToShow == "") {
	    		   textarea.append("Database is empty at the moment :(");
	    	   } else {
	    		   textarea.append(stuffToShow);
	    	   }
	    	   break;
	       case "clear":
	    	   textarea.setText("");
	    	   break;
	       case "addUser": {
	    	   String[] userInfo = argument.split(";");
	    	   if (userInfo.length == 3) {
	    		   if (manager.getUserByNIN(userInfo[2]) != null) {
	    			   textarea.append("This user already exists");
	    		   } else if(manager.addUser(userInfo[0], userInfo[1], userInfo[2])) {
	    			   textarea.append(userInfo[0] + " " + userInfo[1] + " has been added");
	    		   } else {
	    			   textarea.append("Please make sure that the infomation you entered is correct.");
	    		   }	    		   
	    	   } else {
	    		   textarea.append("Argument format should be: firstname;lastname;NIN\n");
	    		   textarea.append("Remember to separate the values by semi-colon");
	    	   }
	    	   break;
	       }
	       case "getUser": {
	    	   User user = manager.getUserByNIN(argument);
	    	   if (user != null) {
	    		   textarea.append(user.toString() + "\n");
	    		   textarea.append(user.bicyclesToString());
	    	   } else {
	    		   textarea.append("No user found with NIN: " + argument);
	    	   }
	    	   break;
	       }
	       case "removeUser":
	    	   if (manager.removeUser(argument)) {
	    		   textarea.append("Removed user with NIN: " + argument);
	    	   } else {
	    		   textarea.append("No user found with NIN: " + argument);
	    	   }
	    	   break;
	       case "addBicycle":
	    	   User user = manager.getUserByNIN(argument);
	    	   if (user != null) {
	    		   String newBicycleID = manager.addBicycle(user);
	    		   if (newBicycleID == null) {
	    			   textarea.append("No more bicycle IDs left");
	    		   } else {
	    			   textarea.append("Added new bicycle to user\n");
	    			   textarea.append("Printing barcode for bicycle ID: " + newBicycleID);
	    			   printer.printBarcode(newBicycleID);
	    		   }
	    	   } else {
	    		   textarea.append("No user found with NIN: " + argument);
	    	   }
	    	   break;
	       case "getBicycle": {
	    	   Bicycle bicycle = manager.findBicycle(argument);
	    	   if (bicycle != null) {
	    		   textarea.append(bicycle.toString());
	    	   } else {
	    		   textarea.append("No bicycle found with ID: " + argument);
	    	   }
	    	   break;
	       }
	       case "removeBicycle":
	    	   if (manager.removeBicycle(argument)) { 
	    		   textarea.append("Removed bicycle with ID: " + argument);
	    	   } else {
	    		   textarea.append("No bicycle found with ID: " + argument);
	    	   }
	    	   break;
	       case "printBarcode": {
	    	   Bicycle bicycle = manager.findBicycle(argument);
	    	   if (bicycle != null) {
	    		   textarea.append("Printing barcode for bicycle ID: " + bicycle.getID());
    			   printer.printBarcode(bicycle.getID());
	    	   } else {
	    		   textarea.append("No bicycle found with ID: " + argument);
	    	   }
	    	   break;
	       }
	       default:
	    	   textarea.append("\"" + command + "\" does not exist!");
	       }
	       inputfield.setText("");
	       if (!command.equals("clear")){
	    	   textarea.append("\n");
	       }
	       textarea.setCaretPosition(textarea.getDocument().getLength());
	    }
		});
		frame.add(inputfield, BorderLayout.PAGE_END);
		
		/*
		 * Initialize GUI
		 */
		
		frame.pack();
		frame.setVisible(true);
		inputfield.requestFocusInWindow();
	}

	public static void main(String[] args) {
		new Garage();
	}
}