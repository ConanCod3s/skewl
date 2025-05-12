import java.util.*;

/**
 * Test Case Two:
 *      This error is caused by a missing ) on line 12
 *  Expected cmd line print out:
 *      Delimiter error:
 *          opened ( at Line 14, Col 25
 *          expected ) but found } at Line 16, Col 5
 */

public class TestOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter integers for priority queue 1: ";
        String lineOne = scanner.nextLine();
    }
}