package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public interface Selection {
	
	/**
	 * Move the cursor forward or backward and set the new cursor position
	 * The cursor position has delimiter positions :
	 * position 0 and the position equivalent to buffer length
	 */
	public void moveCursor(int position);
	
	/**
	 * Move the cursor to the start of the buffer (position 0)
	 */
	public void moveToStart();
	
	/**
	 * Move the cursor to the end of the buffer
	 */
	public void moveToEnd();
	
	/**
	 * Set a marker position at the current position of the cursor
	 */
	public void setMarkerPosition();
	
	/**
	 * Return the current position of the cursor
	 */
	public int getCursorPosition();
	
	/**
	 * Return the current marker position 
	 */
	public int getMarkerPosition();
	
	/**
	 * Affect a position to the marker (-1), it is an unknown position for the buffer
	 * This position has no effect on the selection
	 */
	public void resetMarkerPosition();

}
