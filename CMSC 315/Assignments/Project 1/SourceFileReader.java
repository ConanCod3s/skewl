import java.io.*;


public class SourceFileReader {

    private final BufferedReader br;
    private int line = 1, col = 0;

    public SourceFileReader(String fileName) throws FileNotFoundException {
        // ../Tests/[TestOne or TestTwo].java
        br = new BufferedReader(new FileReader(fileName));
    }

    public char nextChar() throws IOException {
        while (true) {
            int raw = readRaw();
            if (raw == -1) return '\0';
            char ch = (char) raw;

            // detect comment or literal openers and skip them
            if (ch == '/') {                             // maybe // or /* …
                br.mark(1);
                int peek = br.read();
                if (peek == '/')   { readRaw(); skipSingleLine(); continue; }
                if (peek == '*')   { readRaw(); skipBlock();       continue; }
                br.reset();                             // was just a plain '/'
            }
            else if (ch == '"')  { skipString(); continue; }
            else if (ch == '\'') { skipChar();   continue; }

            return ch;                                  // real delimiter candidate
        }
    }

    public String position() { return "Line " + line + ", Col " + col; }

    private int readRaw() throws IOException {
        int ch = br.read();
        if (ch == '\n') { line++; col = 0; }
        else if (ch != -1) col++;
        return ch;
    }

    private void skipSingleLine() throws IOException {
        int ch;
        while ((ch = readRaw()) != -1 && ch != '\n') { /* nothing */ }
    }

    /** Skip from /* (already consumed) to matching *\/ or EOF. */
    private void skipBlock() throws IOException {
        int prev = 0, curr;
        while ((curr = readRaw()) != -1) {
            if (prev == '*' && curr == '/') break;      // found closing */
            prev = curr;
        }
    }

    /** Skip a Java string literal that began with ". */
    private void skipString() throws IOException {
        boolean escaping = false;
        int ch;
        while ((ch = readRaw()) != -1) {
            if (escaping) escaping = false;             // consume escaped char
            else if (ch == '\\') escaping = true;       // start escape
            else if (ch == '"')  break;                 // end of literal
        }
    }

    /** Skip a Java char literal that began with '. */
    private void skipChar() throws IOException {
        boolean escaping = false;
        int ch;
        while ((ch = readRaw()) != -1) {
            if (escaping) escaping = false;
            else if (ch == '\\') escaping = true;
            else if (ch == '\'') break;
        }
    }
}
