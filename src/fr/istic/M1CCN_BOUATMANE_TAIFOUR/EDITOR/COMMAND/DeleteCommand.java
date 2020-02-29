package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class DeleteCommand implements Command {

	private Engine engine;
		
	public DeleteCommand(Engine engine) {
		
		this.engine = engine;
	}
	
	@Override
	public void execute() {
		
		this.engine.delete();
	}

}
