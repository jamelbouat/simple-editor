package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public interface Selection {
	
	/**
	 * Move the cursor forward or backward and set the new position
	 * cursorIsAtStart and cursorIsAtEnd
	 */
	public void moveCursor(int position);
	
	/**
	 * Return the cursor to position 0 in the buffer
	 */
	public void moveToStart();
	
	/**
	 * Return the cursor to the end of the buffer
	 */
	public void moveToEnd();
	
	/**
	 * Set a maker position for the current position of the cursor
	 */
	public void setMarkerPosition();
	
	/**
	 * Return the current position of the cursor
	 */
	public int getCursorPosition();
	
	/**
	 * Return the previously set marker position 
	 */
	public int getMarkerPosition();
	
	/**
	 * Affect no position to the marker
	 */
	public void resetMarkerPosition();

//	public boolean cursorIsAtStart();
//	
//	public boolean cursorIsAtEnd();

	// wipe selection 

}
