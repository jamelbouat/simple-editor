package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public class EngineImplementation implements Engine {
	
	private StringBuffer buffer;
	private String clipBoard = "";
	private Selection selection;
	
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
		
		this.clipBoard = (cursor != marker && cursor > marker) ? 
				this.getBufferContent().substring(marker, cursor) :
					this.getBufferContent().substring(cursor, marker);
				
		this.buffer = (cursor != marker && cursor > marker) ? 
				this.buffer.delete(marker, cursor) :
					this.buffer.delete(cursor, marker);

		if (cursor > marker) {
			getSelection().moveCursor(marker - cursor);
		}
		
		this.getSelection().resetMarkerPosition();
	}

	@Override
	public void copy() {

		int cursor = getSelection().getCursorPosition();
		int marker = getSelection().getMarkerPosition();
		
		this.clipBoard = (cursor != marker && cursor > marker) ? 
				this.getBufferContent().substring(marker, cursor) :
					this.getBufferContent().substring(cursor, marker);
				
		this.getSelection().resetMarkerPosition();
	}

	@Override
	public void paste() {

		for(char c : this.getClipboardContent().toCharArray()) {
			this.insert(c);
		}
		
		this.getSelection().resetMarkerPosition();
	}

	@Override
	public void insert(char c) {
		
		this.buffer.insert(getSelection().getCursorPosition(), c);
		this.getSelection().moveCursor(1);
		this.getSelection().resetMarkerPosition();
	}

	@Override
	public void delete() {
		this.getSelection().moveCursor(-1);
		this.buffer.deleteCharAt(getSelection().getCursorPosition());
		this.getSelection().resetMarkerPosition();
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
