package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.Command;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class MoveToStartBufferCommand implements Command {

	private Engine engine;
	
	public MoveToStartBufferCommand(Engine engine) {
		
		this.engine = engine;
	}

	@Override
	public void execute() {
		
		this.engine.getSelection().moveToStart();
	}

}
