package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.Command;

public interface HCI {
	
	/**
	 * Add a new a command to the HCI editor
	 * @param selector is string letter defining the the command operation
	 * @param command is the new defined command operation
	 */
	public void setCommand(String selector, Command command);
	
	/**
	 * start of the watching loop editor
	 */
	public void startInvokerEditorLoop();
	
	/**
	 * stop or exit the watching loop editor
	 */
	public void exitEditorLoop();
	
	/**
	 * Ask the user for the amount of cursor move
	 * @return value of cursor move
	 */
	public int getCursorMove();
	
	/**
	 * Ask the user to enter characters
	 * @return the character(s) inserted
	 */
	public String getInsertedCharacters();

}
