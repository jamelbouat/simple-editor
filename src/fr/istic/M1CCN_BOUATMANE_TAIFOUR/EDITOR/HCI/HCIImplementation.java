package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI;

import java.io.*;
import java.util.*;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.Command;

public class HCIImplementation implements HCI {
	
	private Map<String, Command> commands;
	private Scanner inputStream;
	private boolean exitEditorFlag;
	
	/*
	 * HashMap is containing the HCI editor commands
	 * Scanner for reading the user input 
	 */
	public HCIImplementation() {
		this.commands = new HashMap<String, Command>();
		this.inputStream = new Scanner(System.in);
		this.exitEditorFlag = false;
	}

	@Override
	public void setCommand(String selector, Command command) {
		commands.put(selector, command);
	}

	/*
	 * while the ExitEditorCommand is not invoked the editor continues running
	 * For each loop : buffer, clipboard and cursor commands are executed 
	 */
	@Override
	public void startInvokerEditorLoop() {
		while(!exitEditorFlag) {
			String commandSelector = null;
			commands.get("buffer").execute();
			commands.get("clipboard").execute();
			commands.get("cursor").execute();
			System.out.print("\n-> ");
			
			/*
			 * Read the user invocation command
			 */
			try {
				commandSelector = inputStreamRead();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			/*
			 * Check if the command exists and execute it
			 */
			Command command = commands.get(commandSelector.trim());
			if (command != null) {
				command.execute();
			} else {
				System.out.println("Unknown command selector !");
			}			
		}
	}

	/*
	 * Reading the user input, and throws an IOException if it cannot read the data
	 */
	private String inputStreamRead() throws IOException {
		if (inputStream.hasNextLine()) {

			return inputStream.nextLine();
		} else {
			
			throw new IOException();
		}
	}

	@Override
	public void exitEditorLoop() {
		this.exitEditorFlag = true;
		System.out.print("Editor closed ...");
	}

	/*
	 * Read the user input data for moving the cursor,
	 * and catches IOException or NumberFormatException, if any
	 */
	@Override
	public int getCursorMove() {
		System.out.print("Enter the number of the cursor moves : ");
		int moveCursor = -1;
		
		try {
			moveCursor = Integer.parseInt(inputStreamRead());
		} catch (NumberFormatException | IOException e) {
			e.printStackTrace();
		}
		
		return moveCursor;
	}

	/*
	 * Read the character(s) entered  by the user,
	 * and catches IOException, if any
	 */
	@Override
	public String getInsertedCharacters() {
		System.out.print("Enter your characters : ");
		String inputChars = "";
		
		try {
			inputChars = inputStreamRead();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return inputChars;
	}
	
}
