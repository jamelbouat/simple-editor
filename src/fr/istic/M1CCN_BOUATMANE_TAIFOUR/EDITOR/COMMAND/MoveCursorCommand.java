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

	@Override
	public void execute() {
		
		this.engine.getSelection().moveCursor(this.hci.getCursorMove());
	}
	
}
