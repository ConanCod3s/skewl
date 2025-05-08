import java.util.*;

public class Exercise {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter integers for priority queue 1: ");
        String lineOne = scanner.nextLine();
        System.out.print("Enter integers for priority queue 2: ");
        String lineTwo = scanner.nextLine();

        PriorityQueue<Integer> queOne = new PriorityQueue<>(),
                queTwo = new PriorityQueue<>();
        for (String tok : lineOne.trim().split("\\s+")) {
            queOne.add(Integer.parseInt(tok));
        }
        for (String tok : lineTwo.trim().split("\\s+")) {
            queTwo.add(Integer.parseInt(tok));
        }

        PriorityQueue<Integer> union = new PriorityQueue<>();
        union.addAll(queOne);
        union.addAll(queTwo);

        PriorityQueue<Integer> difference = new PriorityQueue<>();
        for (Integer n : queOne) {
            if (!queTwo.contains(n)) {
                difference.add(n);
            }
        }

        PriorityQueue<Integer> intersection = new PriorityQueue<>();
        for (Integer n : queOne) {
            if (queTwo.contains(n)) {
                intersection.add(n);
            }
        }

        System.out.println("The union of the two priority queues is:");
        while (!union.isEmpty()) {
            System.out.print(union.poll() + " ");
        }
        System.out.println("\n");

        System.out.println("The difference of the two priority queues is:");
        while (!difference.isEmpty()) {
            System.out.print(difference.poll() + " ");
        }
        System.out.println("\n");

        System.out.println("The intersection of the two priority queues is:");
        while (!intersection.isEmpty()) {
            System.out.print(intersection.poll() + " ");
        }
        System.out.println();
    }
}