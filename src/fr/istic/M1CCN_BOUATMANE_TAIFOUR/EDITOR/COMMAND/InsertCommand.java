package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI.HCI;

public class InsertCommand implements Command {

	private Engine engine;
	private HCI hci;
	
	public InsertCommand(Engine engine, HCI hci) {
		
		this.engine = engine;
		this.hci = hci;
	}
	
	/*
	 * Convert the inserted characters string to an array of characters,
	 * then loop though the array to insert one by one to the engine 
	 */
	@Override
	public void execute() {
		char[] inputChars = this.hci.getInsertedCharacters().toCharArray();
		
		for(char c : inputChars) {
			this.engine.insert(c);
		}
	}

}
