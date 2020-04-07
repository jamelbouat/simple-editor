package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI.HCI;

public class MoveCursorCommand implements Command {
	
	private Engine engine;
	private HCI hci;
	
	public MoveCursorCommand(Engine engine, HCI hci) {
		
		this.engine = engine;
		this.hci = hci;
	}

	/*
	 * Catch a an exception thrown by the cursorMove method if
	 * the new position is not between the delimiter positions,
	 * then display a warning, if any. 
	 */
	@Override
	public void execute() {
		try {
			this.engine.getSelection().moveCursor(this.hci.getCursorMove());
			
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Warning : Cannot move the cursor further\n");
		}
	}
}
