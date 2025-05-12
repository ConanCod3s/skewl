// DelimiterChecker.java
import java.util.*;
import java.io.*;

public class DelimiterChecker {

    private static final Set<Character> LEFT  = Set.of('(', '[', '{');
    private static final Set<Character> RIGHT = Set.of(')', ']', '}');

    private static record Delim(char ch, String pos) {}

    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        SourceFileReader reader = null;

        while (reader == null) {
            System.out.print("Enter Java source file: ");
            String name = kb.nextLine();
            try {
                reader = new SourceFileReader(name);
            } catch (FileNotFoundException e) {
                System.out.println("File not found.");
            }
        }

        Deque<Delim> stack = new ArrayDeque<>();

        try {
            char ch;
            while ((ch = reader.nextChar()) != '\0') {
                if (LEFT.contains(ch)) {
                    stack.push(new Delim(ch, reader.position()));
                } else if (RIGHT.contains(ch)) {
                    if (stack.isEmpty()) {
                        printError(ch, reader.position());
                        return;
                    }
                    Delim open = stack.pop();
                    if (!matches(open.ch(), ch)) {
                        printError(open, ch, reader.position());
                        return;
                    }
                }
            }

            if (!stack.isEmpty()) {
                printError(stack.pop());
            } else {
                System.out.println("All delimiters match!");
            }
        } catch (IOException e) {
            System.out.println("I/O error while reading file: " + e);
        }
    }

    private static void printError(char found, String posFound) {
        System.out.printf(
                "Delimiter error:%n no opener for %c at %s%n", found, posFound
        );
    }

    private static void printError(Delim open, char found, String posFound) {
        System.out.printf(
                "Delimiter error:%n" +
                        "  opened %c at %s%n" +
                        "  expected %c but found %c at %s%n",
                open.ch(), open.pos(),
                partner(open.ch()), found, posFound
        );
    }

    /** opener left dangling at end of file */
    private static void printError(Delim open) {
        System.out.printf(
                "Delimiter error:%n" +
                        "  opened %c at %s but reached end-of-file without closing%n",
                open.ch(), open.pos()
        );
    }

    private static boolean matches(char o, char c) {
        return (o == '(' && c == ')') ||
                (o == '[' && c == ']') ||
                (o == '{' && c == '}');
    }

    private static char partner(char o) {
        return o == '(' ? ')' : o == '[' ? ']' : '}';
    }
}
