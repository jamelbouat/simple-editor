package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class CutCommand implements Command {

	private Engine engine;
	
	public CutCommand(Engine engine) {
		
		this.engine = engine;
	}

	@Override
	public void execute() {
		
		this.engine.cut();
	}

}
