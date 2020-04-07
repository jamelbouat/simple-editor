package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class EngineImplementationTest {

	private EngineImplementation engine;
	
	private void insertStringOfChars(String str) {
		for(char c : str.toCharArray()) {
			engine.insert(c);
		}
	}

	@BeforeEach
	public void setUp() throws Exception {
		engine = new EngineImplementation();
	}
	
	@Test
	@DisplayName("Test case : insert method at the end of the buffer")
	public void testInsertEndBuffer() {
		
		assertEquals("", engine.getBufferContent());
		assertEquals("", engine.getClipboardContent());
		assertEquals(0, engine.getSelection().getCursorPosition());
		
		engine.insert('a');
		assertEquals("a", engine.getBufferContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		
		engine.insert('c');
		assertEquals("ac", engine.getBufferContent());
		assertEquals(2, engine.getSelection().getCursorPosition());
		assertEquals("", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : insert method inside the buffer")
	public void testInsertInsideBuffer() {

		this.insertStringOfChars("ad");
		engine.getSelection().moveCursor(-1);
		
		engine.insert('b');
		assertEquals("abd", engine.getBufferContent());
		assertEquals(2, engine.getSelection().getCursorPosition());
		
		engine.insert('c');
		assertEquals("abcd", engine.getBufferContent());
		assertEquals(3, engine.getSelection().getCursorPosition());
		assertEquals("", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : insert method at start of the buffer")
	public void testInsertStartBuffer() {

		this.insertStringOfChars("bc");
		engine.getSelection().moveCursor(-2);
		
		engine.insert('a');
		assertEquals("abc", engine.getBufferContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		assertEquals("", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : getBufferContent method")
	public void testGetBufferContent() {
		
		assertEquals("", engine.getBufferContent());
		this.insertStringOfChars("abcde");
		assertEquals("abcde", engine.getBufferContent());
	}

	@Test
	@DisplayName("Test case : getClipboardContent method")
	public void testGetClipboardContent() {
		
		assertEquals("", engine.getClipboardContent());
		this.insertStringOfChars("abcde");
		assertEquals("", engine.getClipboardContent());
		assertEquals(5, engine.getSelection().getCursorPosition());
		
		
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveToStart();
		engine.copy();
		assertEquals("abcde", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : forbid the cursor to go in a backward move at the start of the buffer")
	public void testNegativeMoveInStartBufferPosition() {
		
		assertEquals(0, engine.getSelection().getCursorPosition());
		assertThrows(StringIndexOutOfBoundsException.class, () -> engine.getSelection().moveCursor(-1));
		
		this.insertStringOfChars("abcd");
		engine.getSelection().moveToStart();
		assertThrows(StringIndexOutOfBoundsException.class, () -> engine.getSelection().moveCursor(-1));
	}
	
	@Test
	@DisplayName("Test case : forbid the cursor to go in a foward move at the end of the buffer")
	public void testPositiveMoveInEndBufferPosition() {
		
		assertThrows(StringIndexOutOfBoundsException.class, () -> engine.getSelection().moveCursor(1));
		
		this.insertStringOfChars("abcd");
		assertThrows(StringIndexOutOfBoundsException.class, () -> engine.getSelection().moveCursor(1));
	}
	
	@Test
	@DisplayName("Test case : cut method forward selection")
	public void testCutForwardSelection() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		engine.getSelection().moveCursor(2);
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(3);
		engine.cut();
		assertEquals("ab", engine.getBufferContent());
		assertEquals("cde", engine.getClipboardContent());
		assertEquals(2, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : cut method backward selection")
	public void testCutBackwardSelection() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(-4);
		engine.cut();
		assertEquals("a", engine.getBufferContent());
		assertEquals("bcde", engine.getClipboardContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : cut method with a non set marker position or equivalent to the cursor position")
	public void testCutWithoutSelection() {
		
		this.insertStringOfChars("abcde");
		assertEquals(5, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
		
		engine.cut();
		assertEquals("abcde", engine.getBufferContent());
		assertEquals("", engine.getClipboardContent());
		
		engine.getSelection().setMarkerPosition();
		assertEquals(5, engine.getSelection().getMarkerPosition());
		assertEquals("abcde", engine.getBufferContent());
		assertEquals("", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : copy method forward selection")
	public void testCopyForwardSelection() {
	
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		engine.getSelection().moveCursor(2);
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(3);
		engine.copy();
		assertEquals("abcde", engine.getBufferContent());
		assertEquals("cde", engine.getClipboardContent());
		assertEquals(5, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : copy method forward selection")
	public void testCopyBackwardSelection() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(-4);
		engine.copy();
		assertEquals("abcde", engine.getBufferContent());
		assertEquals("bcde", engine.getClipboardContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
	}

	@Test
	@DisplayName("Test case : copy method with a non set marker position or equivalent to the cursor position")
	public void testCopyWithoutSelection() {
		
		this.insertStringOfChars("abcde");
		assertEquals(5, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
		
		engine.copy();
		assertEquals("", engine.getClipboardContent());
		
		engine.getSelection().setMarkerPosition();
		assertEquals(5, engine.getSelection().getMarkerPosition());
		assertEquals("", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : paste method after a copy on a selection")
	public void testPasteAfterCopy() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveToEnd();
		
		engine.copy();
		engine.paste();
		assertEquals("abcdeabcde", engine.getBufferContent());
		assertEquals("abcde", engine.getClipboardContent());
		assertEquals(10, engine.getSelection().getCursorPosition());
		assertEquals(-1, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : paste method after a cut method")
	public void testPasteAfterCut() {
		
		this.insertStringOfChars("abcdeabcde");
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveToStart();
		assertEquals(10, engine.getSelection().getMarkerPosition());
		
		engine.cut();
		engine.paste();
		assertEquals("abcdeabcde", engine.getBufferContent());
		assertEquals("abcdeabcde", engine.getClipboardContent());
		assertEquals(10, engine.getSelection().getCursorPosition());
	}
	
	@Test
	@DisplayName("Test case : paste method inside the buffer")
	public void testPasteInsideBuffer() {
		
		this.insertStringOfChars("abcdefghik");
		engine.getSelection().moveCursor(-5);
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(+3);
		
		engine.copy();
		engine.paste();
		assertEquals("abcdefghfghik", engine.getBufferContent());
		assertEquals("fgh", engine.getClipboardContent());
		assertEquals(11, engine.getSelection().getCursorPosition());
	}
	
	@Test
	@DisplayName("Test case : delete character at the end of the buffer")
	public void testDeleteEndBuffer() {
		
		this.insertStringOfChars("abcde");
		assertEquals(5, engine.getSelection().getCursorPosition());
		
		engine.delete();
		assertEquals("abcd", engine.getBufferContent());
		assertEquals(4, engine.getSelection().getCursorPosition());
		
		engine.delete();
		assertEquals("abc", engine.getBufferContent());
		assertEquals(3, engine.getSelection().getCursorPosition());

	}
	
	@Test
	@DisplayName("Test case : delete character inside the buffer")
	public void testDeleteInsideBuffer() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().moveCursor(-2);
		assertEquals(3, engine.getSelection().getCursorPosition());		
		engine.delete();
		assertEquals("abde", engine.getBufferContent());
		assertEquals(2, engine.getSelection().getCursorPosition());		
	}
	
	@Test()
	@DisplayName("Test case : delete character at the start of the buffer, no exception is thrown")
	public void testDeleteAtStartBuffer() {

		assertDoesNotThrow(() -> this.engine.delete());
		
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		assertDoesNotThrow(() -> this.engine.delete());
	}
	
}