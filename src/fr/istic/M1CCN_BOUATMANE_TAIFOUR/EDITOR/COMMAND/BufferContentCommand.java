package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.Engine;

public class BufferContentCommand implements Command {
	
	private Engine engine;
	
	public BufferContentCommand(Engine engine) {
		this.engine = engine;
	}

	@Override
	public void execute() {
		
		System.out.println("Buffer content : " + this.engine.getBufferContent());
	}

}
