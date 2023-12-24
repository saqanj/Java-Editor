import list.Position;


/**
 * Custom implementation of the SimpleTextEditor class.
 * Intended to replicate a standard text editor application
 * using methods from ArraySequence and SimpleTextEditor.
 *
 * @author Saqlain Anjum
 * @version 1.0.0
 */
public class MyTextEditor implements SimpleTextEditor {


    /**
     * An ArraySequence object that represents the entire text.
     */
    private final ArraySequence<String> entireText = new ArraySequence();
    /**
     * An integer representing cursor position in the editor.
     */
    private int cursor = -1;

    /**
     * Returns true if the text is completely empty (and cursor is at line -1).
     *
     * @return true if the text is empty and false otherwise
     */
    public boolean isEmpty() {
        return entireText.isEmpty() && cursor == -1;
    }


    /**
     * Returns the current number of lines of text.
     *
     * @return the current number of lines
     */
    public int size() {
        return entireText.size();
    }


    /**
     * Returns true if the cursor is at the last line in the text or the text
     * is empty.
     *
     * @return true if the cursor is at the last line and false otherwise.
     */
    public boolean isCursorAtLastLine() {
        if (isEmpty()) {
            return true;
        } else return entireText.atIndex(cursor) == entireText.last();
    }


    /**
     * Sets the cursor to be the text line after its current position.
     */
    public void cursorDown() {
        cursor++;
        if (cursor > size()) {
            cursor = size();
        }
    }


    /**
     * Sets the cursor to be the text line before its current position.
     */
    public void cursorUp() {
        if (cursor < 0) {
            cursor = 0;
        } else {
            cursor--;
        }
    }


    /**
     * Sets the cursor to be the line ranked i (the first line is line 0).
     *
     * @param line The target line number.
     */
    public void moveCursorToLine(int line) {
        if (line < 0) {
            line = -1;
        } else if (line >= size()) {
            line = size();
        }
        cursor = line;
    }


    /**
     * Returns the line number (rank) of the current cursor line.
     */
    public int cursorLineNum() {
        return cursor;
    }


    /**
     * Inserts a given string in the line after the current cursor, moving the
     * cursor to the line inserted.
     *
     * @param toInsert The string to be inserted.
     */
    public void insertAfterCursor(String toInsert) {
        entireText.add(cursorLineNum() + 1, toInsert);
        cursorDown();
    }


    /**
     * Builds the combined and properly formatted string representation
     * of each line of text in the ArraySequence entireText.
     *
     * @return The combined string representation of each line in entireText.
     */
    @Override
    public String toString() {
        StringBuilder newString = new StringBuilder();
        for (int i = 0; i < size(); i++) {
            newString.append(entireText.get(i)).append("\n");
        }
        return newString.toString().trim();
    }


    /**
     * Inserts the given string in the line before the current cursor, moving the
     * cursor to the line inserted.
     *
     * @param toInsert The string to be inserted.
     */
    public void insertBeforeCursor(String toInsert) {
        Position<String> positionAtCursor = entireText.atIndex(cursorLineNum());
        entireText.addBefore(positionAtCursor, toInsert);
        cursorUp();
    }


    /**
     * Get the current line at the cursor.
     *
     * @return The line of text at the cursor.
     */
    public String getAtCursor() {
        return entireText.get(cursor);
    }


    /**
     * Replaces the string at the current cursor with the given string, keeping
     * the cursor at this line.
     *
     * @param replacement The string to be inserted.
     */
    public void replaceAtCursor(String replacement) {
        Position<String> positionAtCursor = entireText.atIndex(cursorLineNum());
        entireText.set(positionAtCursor, replacement);
    }


    /**
     * Removes the entire line at the current cursor, setting the cursor to now
     * be the position of the next line, unless the cursor was the last line,
     * in which case the cursor should move to the new last line.
     */
    public void removeAtCursor() {
        entireText.remove(cursorLineNum());
        if (isCursorAtLastLine()) {
            cursorDown();
        } else {
            cursorUp();
        }
    }
}