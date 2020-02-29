package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI.HCI;

public class ExitEditorCommand implements Command {

	private HCI hci;
	
	public ExitEditorCommand(HCI hci) {
		
		this.hci = hci;
	}

	@Override
	public void execute() {
		
		this.hci.exitEditorLoop();
	}

}
