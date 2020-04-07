package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

public interface Engine {
	
	/**
	 * @return the effective capacity of the buffer
	 */
	public int getBufferLength();
	
	/**
	 * The selected data is removed from the buffer
	 * The clipboard will have the selected data
	 * If the selected data is null, the current data in the clipboard is not affected
	 * To do a cut, a marker has to be set and its position is different from the cursor position
	 */
	public void cut();
	
	/**
	 * The selected data is not removed from the buffer 
	 * The clipboard will have the selected data 
	 * If the selected data is null, the current data in the clipboard is not affected
	 * To do a copy, a marker has to be set and its position is different from cursor position
	 */
	public void copy();
	
	/**
	 * The data in the clipboard is inserted into the current position cursor in the buffer
	 * The previous data in the clipboard is not affected,
	 * the buffer data after the cursor position is added after the inserted data, if any.
	 * The cursor will have its position incremented by the clipboard data length 
	 */
	public void paste();
	
	/**
	 * Insert character c into the current cursor position
	 * The new cursor position is incremented by 1
	 * @param character c to be inserted
	 */
	public void insert(char c);
	
	/**
	 * Delete one character before the current cursor position
	 * The cursor moves by -1
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
	 * Get the content of the clipboard
	 * @return a string of characters
	 */
	public String getClipboardContent();

}
