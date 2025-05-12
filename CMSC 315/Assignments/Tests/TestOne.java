import java.util.*;

/**
 *  Test Case One:
 *      This error is causes by a comment block that is not properlly closed
 *      causing all following code to be commented out.
 *
 *  Expected cmd line print out:
 *      Unclosed { (opened at Line 15, Col 44)
 *  Even though thats not the issue the DelimiterChecker doesnt find a closing {
 *  since it is commented out
 */

public class TestOne {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
/**
 *
 *          System.out.print("Enter integers for priority queue 1: ");
 *          String lineOne = scanner.nextLine();

        System.out.print("Enter integers for priority queue 1: ");
        String lineOne = scanner.nextLine();
        System.out.print("Enter integers for priority queue 2: ");
        String lineTwo = scanner.nextLine();
    }
}