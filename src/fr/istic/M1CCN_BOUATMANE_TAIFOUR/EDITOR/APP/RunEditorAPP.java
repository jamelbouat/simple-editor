package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.APP;

import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.COMMAND.*;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE.*;
import fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.HCI.*;

public class RunEditorAPP {
	
	private static Engine engine;
	private static HCI hci;
	
	public static void main (String[] args) {
		
		engine = new EngineImplementation();
		hci = new HCIImplementation();
		
		setInvokerCommands();
		displayCommandList();
		hci.startInvokerEditorLoop();		
	}
	
	public static void displayCommandList() {

		System.out.println("-------- Command List -------- \n"
				+ "mv : make a cursor move.\n"
				+ "i : insert a character at the current cursor position.\n"
				+ "mark : set a marker at the current cursor position.\n"
				+ "c : copy the current selection.\n"
				+ "x : cut the current selection.\n"
				+ "v : paste the clipboard content to the cursor current position.\n"
				+ "d : delete a character at the current cursor position.\n"
				+ "s : move the cursor to the start of the buffer.\n"
				+ "e : move the cursor to the end of the buffer.\n"
				+ "q : quit the editor.\n");
	}

	public static void setInvokerCommands() {
		
		hci.setCommand("mv", new MoveCursorCommand(engine, hci));
		hci.setCommand("i", new InsertCommand(engine, hci));
		hci.setCommand("mark", new SetMarkerCommand(engine));
		hci.setCommand("c", new CopyCommand(engine));
		hci.setCommand("x", new CutCommand(engine));
		hci.setCommand("v", new PasteCommand(engine));
		hci.setCommand("d", new DeleteCommand(engine));
		hci.setCommand("s", new MoveToStartBufferCommand(engine));
		hci.setCommand("e", new MoveToEndBufferCommand(engine));
		hci.setCommand("q", new ExitEditorCommand(hci));
		hci.setCommand("buffer", new BufferContentCommand(engine));
		hci.setCommand("curs", new CursorPositionCommand(engine));
	}
	
}
