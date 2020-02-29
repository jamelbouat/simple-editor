package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.Command;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class MoveToEndBufferCommand implements Command {
	
	private Engine engine;
	
	public MoveToEndBufferCommand(Engine engine) {
		
		this.engine = engine;
	}

	@Override
	public void execute() {
		
		this.engine.getSelection().moveToEnd();
	}

}
