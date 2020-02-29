package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public interface Engine {
	
	/**
	 * 
	 * @return the number effective capacity of the buffer
	 */
	public int getBufferLength();
	
	/**
	 * The selected data is removed from the buffer
	 * The clipboard will have the selected data
	 * If the selected data is null, the previous data in the clipboard is not affected
	 */
	public void cut();
	
	/**
	 * The selected data is not removed from the buffer 
	 * The clipboard will have the selected data 
	 * If the selected data is null, the previous data in the clipboard is not affected
	 */
	public void copy();
	
	/**
	 * The data in the clipboard is inserted into the current position cursor in the buffer
	 * The previous data in the clipboard is not affected, the data is added after it.
	 * The cursor will have its position incremented by the data length 
	 */
	public void paste();
	
	/**
	 * Insert character c into the current position cursor
	 * The new cursor position is incremented by 1
	 * @param character c to be inserted
	 */
	public void insert(char c);
	
	
	/**
	 * Delete one character before the cursor
	 * Cursor moves will have a new position position - 1
	 */
	public void delete();
	
	/**
	 * 
	 * @return Selection reference object
	 */
	public Selection getSelection();
	
	/**
	 * 
	 * @return the current buffer content
	 */
	public String getBufferContent();
	
	/**
	 * Get all the content of the clipboard
	 * @return a string of characters
	 */
	public String getClipboardContent();

}
