package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public class EngineImplementation implements Engine {
	
	private StringBuffer buffer;
	private Selection selection;
	private String clipBoard;

	public EngineImplementation() {
		this.buffer = new StringBuffer();
		this.selection = new SelectionImplementation(this);
		this.clipBoard = new String("");
	}
	
	@Override
	public int getBufferLength() {

		return this.buffer.length();
	}

	@Override
	public void cut() {
		int cursor = getSelection().getCursorPosition();
		int marker = getSelection().getMarkerPosition();
		
		/*
		 * No selection : method cut do nothing if the marker position is not set or 
		 * the cursor and marker have the same positions
		 */
		if (marker == -1 || cursor == marker) {
			return;
		}
		
		/*
		 * cursor > marker : forward selection (left to right move)
		 * cursor < marker : backward selection (right to left move)
		 */
		this.clipBoard = (cursor > marker) ? 
				this.getBufferContent().substring(marker, cursor) :
					this.getBufferContent().substring(cursor, marker);
				
		this.buffer = (cursor > marker) ? 
				this.buffer.delete(marker, cursor) :
					this.buffer.delete(cursor, marker);
				
		/*
		* Forward selection : Make the cursor moves from right to left by
		* the difference between by marker and cursor positions,
		* Backward selection (marker > cursor) : the cursor keeps it current position
		*/ 
		if (cursor > marker) {
			this.getSelection().moveCursor(marker - cursor);
		}
		
		this.getSelection().resetMarkerPosition();
	}

	@Override
	public void copy() {
		int cursor = getSelection().getCursorPosition();
		int marker = getSelection().getMarkerPosition();
		
		/*
		 * No selection : method copy do nothing if the marker position is not set or 
		 * the cursor and marker have the same positions
		 */
		if (marker == -1 || cursor == marker) {
			return;
		}
		
		this.clipBoard = (cursor > marker) ? 
				this.getBufferContent().substring(marker, cursor) :
					this.getBufferContent().substring(cursor, marker);
				
		this.getSelection().resetMarkerPosition();
	}

	@Override
	public void paste() {
		for(char c : this.getClipboardContent().toCharArray()) {
			this.insert(c);
		}
	}

	@Override
	public void insert(char c) {
		this.buffer.insert(this.getSelection().getCursorPosition(), c);
		this.getSelection().moveCursor(1);
	}

	/*
	 * Catch an exception thrown by the moveCursor method if 
	 * trying deleting at the 0 cursor position 
	 * A warning is displayed if an exception is thrown
	 */
	@Override
	public void delete() {
		try {
			this.getSelection().moveCursor(-1);
			this.buffer.deleteCharAt(getSelection().getCursorPosition());
			
		} catch (StringIndexOutOfBoundsException e) {
			System.out.println("Warning : There is no character to delete\n");
		}
	}

	@Override
	public Selection getSelection() {
		
		return this.selection;
	}

	@Override
	public String getBufferContent() {
		String bufferContent = "";
		for (int i = 0; i < this.getBufferLength(); i++) {
			bufferContent += this.buffer.charAt(i);
		}
		
		return bufferContent;
	}

	@Override
	public String getClipboardContent() {

		return clipBoard;
	}

}
