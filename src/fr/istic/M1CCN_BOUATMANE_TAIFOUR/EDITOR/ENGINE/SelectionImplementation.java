package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public class SelectionImplementation implements Selection {
	
	private Engine engine;
	private int cursorPosition;
	private int markerPosition;
	
	public SelectionImplementation(Engine engine) {
		
		this.engine = engine;
		this.cursorPosition = 0;
		this.markerPosition = -1;
	}

	@Override
	public void moveCursor(int move) throws StringIndexOutOfBoundsException {
		if (!(getCursorPosition() + move >= 0 
				&& getCursorPosition() + move <= this.engine.getBufferLength())) {
			throw new StringIndexOutOfBoundsException();
		}
		
		this.cursorPosition += move;
	}

	@Override
	public void setMarkerPosition() {

		this.markerPosition = this.cursorPosition;
	}

	@Override
	public int getCursorPosition() {

		return this.cursorPosition;
	}

	@Override
	public int getMarkerPosition() {
		
		return this.markerPosition;
	}

	@Override
	public void moveToStart() {
		this.cursorPosition = 0;		
	}

	@Override
	public void moveToEnd() {
		
		this.cursorPosition = this.engine.getBufferLength();
	}

	@Override
	public void resetMarkerPosition() {
		
		this.markerPosition = -1;
	}

}
