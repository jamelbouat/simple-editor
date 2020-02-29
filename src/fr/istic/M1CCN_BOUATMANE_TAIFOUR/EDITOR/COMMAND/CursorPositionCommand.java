package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.Command;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class CursorPositionCommand implements Command {

	private Engine engine;
	
	public CursorPositionCommand(Engine engine) {
		
		this.engine = engine;
	}

	@Override
	public void execute() {
		
		System.out.print("Cursor position : " + this.engine.getSelection().getCursorPosition());
	}
}
