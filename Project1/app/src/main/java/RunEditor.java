/**
 * A way to invoke the editor from the command line.
 */
public class RunEditor {
    /**
     * The main entry point
     *
     * @param args No parameters expected.
     */
    public static void main(String[] args) {
//TODO        SimpleTextEditor editor = new MyTextEditor();
        String[] expected = EditorUtilities.readResourceFile("final.txt");

        // Load content into editor
        for (String line : expected) {
//TODO            editor.insertAfterCursor(line);
        }

//TODO        System.out.println(editor);
    }
}
