import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * A test-class for methods in the MyTextEditor class.
 *
 * @author Saqlain Anjum
 * @author Jonathan Johnson
 * @version 1.0.0
 */
public class EditorTest {

    /**
     * A test for the isEmpty and size methods of MyTextEditor.
     */
    @Test
    void emptyEditor() {

        SimpleTextEditor edit = new MyTextEditor();

        // Verify isEmpty() and size()
        assertTrue(edit.isEmpty());
        assertEquals(edit.size(), 0);
    }

    /**
     * Loading the document in the editor and verifying the content matched each corresponding stage.
     */
    @Test
    void testEditor() {

        SimpleTextEditor editor = new MyTextEditor();

        // Verify initial text state
        applyInitialState(editor);
        assertEquals(getText("initial.txt"), editor.toString());

        System.out.format("""
                ====================
                The initial document
                                
                {%s}
                                
                """, editor);

        // Verify middle text state
        applyMiddleState(editor);
        assertEquals(getText("middle.txt"), editor.toString());

        System.out.format("""
                ====================
                The middle document
                                
                {%s}
                                
                """, editor);

        // Verify middle text state
        applyFinalState(editor);
        assertEquals(getText("final.txt"), editor.toString());

        System.out.format("""
                ====================
                The final document
                                
                {%s}
                                
                """, editor);
    }

    /**
     * Join the array line with newlines to make it look like a file document.
     *
     * @param content The lines of the document
     * @return The full document in a single string joined with newlines.
     */
    private String join(String[] content) {
        return String.join("\n", content).trim();
    }

    /**
     * Loading the  initial document to get to the initial document state.
     *
     * @param editor the text editor object.
     */
    private void applyInitialState(SimpleTextEditor editor) {
        String[] initialText = EditorUtilities.readResourceFile("initial.txt");
        for (String line : initialText) {
            editor.insertAfterCursor(line);
        }
    }

    /**
     * Applying changes to the initial state of the document to be corresponding to the middle-state.
     *
     * @param editor the text-editor object.
     */
    void applyMiddleState(SimpleTextEditor editor) {
        editor.moveCursorToLine(0);
        editor.replaceAtCursor("Narnia... where the woods are thick and cool, where Talking Beasts are called to");
        while (!editor.isCursorAtLastLine()) {
            editor.cursorDown();
            if (editor.getAtCursor().equals("where evil men turn into donkeys, where boys go into battle.")) {
                editor.replaceAtCursor("where evil men turn into donkeys, where boys and girls go into battle.\n");
            } else if (editor.getAtCursor().equals("where anything can happen(and most oftan does).")) {
                editor.replaceAtCursor("where anything can happen(and most oftan does).\n");
            } else if (editor.getAtCursor().equals("Narnia...where owls are wise, where some of the giants like to")) {
                editor.replaceAtCursor("Narnia... where professors are wise, where some of the giants like to");
            } else if (editor.getAtCursor().equals("where animals talk, where magical things happen,the world of wicked dragons")) {
                editor.replaceAtCursor("where animals talk, where magical things happen, the world of wicked deans");
            } else if (editor.getAtCursor().equals("snack on humans (and, if carefully cooked, on Marsh-wiggles, too),")) {
                editor.replaceAtCursor("snack on students (and, if carefully cooked, on Marsh-wiggles, too),");
            } else if (editor.getAtCursor().equals("where a prince is put under an evil spell.")) {
                editor.replaceAtCursor("where a prince is put under an evil spell.\n");
            } else if (editor.getAtCursor().equals("Narnia...where the woods are thick and cool, where Talking Beasts are called to")) {
                editor.replaceAtCursor("Narnia ...where the woods are thick and cool, where Talking Beasts are called to");
            } else if (editor.getAtCursor().equals("Narnia...the land between the lamp-post and the castle of Cair Paravel,")) {
                editor.replaceAtCursor("Narnia... the land between the lamp-post and the castle of Cair Paravel,");
            } else if (editor.getAtCursor().equals("Narnia...where dwarfs are loyal and tough and strong-or are they?")) {
                editor.replaceAtCursor("Narnia... where dwarfs are loyal and tough and strong-or are they?");
            }
        }
    }

    /**
     * Applying changes to the middle-state of the document to be correspondent with the final state desired document.
     *
     * @param editor the text-editor object.
     */
    private void applyFinalState(SimpleTextEditor editor) {
        editor.moveCursorToLine(0);
        while (!editor.isCursorAtLastLine()) {
            editor.cursorDown();
            if (editor.getAtCursor().equals("Susan, Edmund, and Lucy. Narnia...where horses talk and hermits like company,")) {
                editor.replaceAtCursor("Susan, Edmund, and Lucy.");
                editor.insertAfterCursor("\nNarnia... where horses talk and hermits like company,");
            } else if (editor.getAtCursor().equals("where anything can happen(and most oftan does).\n")) {
                editor.replaceAtCursor("where anything can happen (and most often does).\n");
            } else if (editor.getAtCursor().equals("Narnia... where professors are wise, where some of the giants like to")) {
                editor.replaceAtCursor("Narnia... where owls are wise, where some of the giants like to");
            } else if (editor.getAtCursor().equals("Narnia... where dwarfs are loyal and tough and strong-or are they?")) {
                editor.replaceAtCursor("Narnia... where dwarfs are loyal and tough and strong---or are they really?");
            }
        }
    }

    /**
     * A method for retrieving the text of a string.
     *
     * @param canonical the canonical string.
     * @return The retrieved text.
     */
    private String getText(String canonical) {
        return join(EditorUtilities.readResourceFile(canonical));
    }
}
