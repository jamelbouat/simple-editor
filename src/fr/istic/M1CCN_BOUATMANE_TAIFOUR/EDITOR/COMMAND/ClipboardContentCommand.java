package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class ClipboardContentCommand implements Command {
	
	private Engine engine;
	
	public ClipboardContentCommand(Engine engine) {
		
		this.engine = engine;
	}

	@Override
	public void execute() {
		
		System.out.println("Clipboard Content : \"" + this.engine.getClipboardContent() + "\"");
	}

}
