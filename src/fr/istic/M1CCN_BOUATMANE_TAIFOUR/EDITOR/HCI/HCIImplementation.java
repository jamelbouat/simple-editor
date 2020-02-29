package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI;

import java.io.*;
import java.util.*;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.Command;

public class HCIImplementation implements HCI {
	
	private Map<String, Command> commands;
	private Scanner inputStream;
	private boolean exitEditorFlag;
	
	
	public HCIImplementation() {
		this.commands = new HashMap<String, Command>();
		this.inputStream = new Scanner(System.in);
		exitEditorFlag = false;
	}

	@Override
	public void setCommand(String selector, Command command) {

		commands.put(selector, command);
	}

	@Override
	public void startInvokerEditorLoop() {
		
		while(!exitEditorFlag) {
			
			String commandSelector = null;
			
			commands.get("buffer").execute();
			commands.get("curs").execute();
			
			System.out.print("\n-> ");
			
			try {
				commandSelector = inputStreamRead();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			Command command = commands.get(commandSelector);
			if (command != null) {
				command.execute();
			} else {
				System.out.println("Unknown command selector");
			}			
		}
	}

	private String inputStreamRead() throws IOException {

		if (inputStream.hasNext()) {
			
			return inputStream.nextLine();
		} else {
			throw new IOException();
		}
	}

	@Override
	public void exitEditorLoop() {

		exitEditorFlag = true;
		System.out.print("Editor closed ...");
	}

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

	@Override
	public String getInsertedCharacters() {
		
		System.out.print("Enter characters : ");
		
		String inputChars = "";
		try {
			
			inputChars = inputStreamRead();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		return inputChars;
	}
	
}
