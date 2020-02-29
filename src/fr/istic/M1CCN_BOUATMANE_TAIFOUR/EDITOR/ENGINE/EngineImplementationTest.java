package fr.istic.M1CCN_BOUATMANE_TAIFOUR.EDITOR.ENGINE;

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
	void setUp() throws Exception {
		engine = new EngineImplementation();
	}
	
	@Test
	@DisplayName("Test case : insert method at the end of the buffer")
	void testInsertEndBuffer() {
		
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
	void testInsertInsideBuffer() {

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
	void testInsertStartBuffer() {

		this.insertStringOfChars("bc");
		engine.getSelection().moveCursor(-2);
		
		engine.insert('a');
		assertEquals("abc", engine.getBufferContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		assertEquals("", engine.getClipboardContent());
	}
	
	@Test
	@DisplayName("Test case : getBufferContent method")
	void testGetBufferContent() {
		
		assertEquals("", engine.getBufferContent());
		this.insertStringOfChars("abcde");
		assertEquals("abcde", engine.getBufferContent());
	}
		
//	@Test
//	@DisplayName("Test case : cursor is at start of the buffer")
//	void testCursorIsAtStart() {
//		
//		assertTrue(engine.getSelection().cursorIsAtStart());
//	}
	
//	s

	@Test
	@DisplayName("Test case : getClipboardContent method")
	void testGetClipboardContent() {
		
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
	void testNegativeMoveInStartBufferPosition() {
		
		assertEquals(0, engine.getSelection().getCursorPosition());
		assertThrows(IllegalArgumentException.class, () -> engine.getSelection().moveCursor(-1));
		
		this.insertStringOfChars("abcd");
		engine.getSelection().moveToStart();
		assertThrows(IllegalArgumentException.class, () -> engine.getSelection().moveCursor(-1));
	}
	
	@Test
	@DisplayName("Test case : forbid the cursor to go in a foward move at the end of the buffer")
	void testPositiveMoveInEndBufferPosition() {
		
		assertThrows(IllegalArgumentException.class, () -> engine.getSelection().moveCursor(1));
		
		this.insertStringOfChars("abcd");
		assertThrows(IllegalArgumentException.class, () -> engine.getSelection().moveCursor(1));
	}
	
//	@Test
//	@DisplayName("Test case : ")
	
	
	@Test
	@DisplayName("Test case : cut method forward selection")
	void testCutForwardSelection() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		engine.getSelection().moveCursor(2);
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(3);
		engine.cut();
		assertEquals("ab", engine.getBufferContent());
		assertEquals("cde", engine.getClipboardContent());
		assertEquals(2, engine.getSelection().getCursorPosition());
		assertEquals(0, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : cut method backward selection")
	void testCutBackwardSelection() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(-4);
		engine.cut();
		assertEquals("a", engine.getBufferContent());
		assertEquals("bcde", engine.getClipboardContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		assertEquals(0, engine.getSelection().getMarkerPosition()); // critical
	}

	@Test
	@DisplayName("Test case : copy method forward selection")
	void testCopyForwardSelection() {
	
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		engine.getSelection().moveCursor(2);
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(3);
		engine.copy();
		assertEquals("abcde", engine.getBufferContent());
		assertEquals("cde", engine.getClipboardContent());
		assertEquals(5, engine.getSelection().getCursorPosition());
		assertEquals(2, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : copy method forward selection")
	void testCopyBackwardSelection() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveCursor(-4);
		engine.copy();
		assertEquals("abcde", engine.getBufferContent());
		assertEquals("bcde", engine.getClipboardContent());
		assertEquals(1, engine.getSelection().getCursorPosition());
		assertEquals(5, engine.getSelection().getMarkerPosition());
	}

	@Test
	@DisplayName("Test case : paste method after a copy on a selection")
	void testPasteAfterCopy() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveToEnd();
		
		engine.copy();
		engine.paste();
		assertEquals("abcdeabcde", engine.getBufferContent());
		assertEquals("abcde", engine.getClipboardContent());
		assertEquals(10, engine.getSelection().getCursorPosition());
		assertEquals(0, engine.getSelection().getMarkerPosition());
	}
	
	@Test
	@DisplayName("Test case : paste method after a cut method")
	void testPasteAfterCut() {
		
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
	void testPasteInsideBuffer() {
		
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
	void testDeleteEndBuffer() {
		
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
	void testDeleteInsideBuffer() {
		
		this.insertStringOfChars("abcde");
		assertEquals(5, engine.getSelection().getCursorPosition());		
		engine.getSelection().moveCursor(-2);
		assertEquals(3, engine.getSelection().getCursorPosition());		
		engine.delete();
		assertEquals("abde", engine.getBufferContent());
		assertEquals(2, engine.getSelection().getCursorPosition());		
	}
	
	@Test
	@DisplayName("Test case : delete character at the start of the buffer")
	void testDeleteStartBuffer() {
		
		assertThrows(IllegalArgumentException.class, () -> engine.delete());	

		this.insertStringOfChars("abcde");
		engine.getSelection().moveToStart();		
		assertThrows(IllegalArgumentException.class, () -> engine.delete());	
	}
		
	@Test
	@DisplayName("Test case : reset marker position")
	void testResetMarkerPosition() {
		
		this.insertStringOfChars("abcde");
		engine.getSelection().setMarkerPosition();
		engine.getSelection().moveToStart();
		engine.getSelection().resetMarkerPosition();
		assertThrows(IllegalArgumentException.class, () -> engine.delete());	
	}
	
}
