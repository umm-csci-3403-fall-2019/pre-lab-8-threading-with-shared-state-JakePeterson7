package search;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        // Update this to make things go faster or take longer for timing studies.
        final int ARRAY_SIZE = 10000000;
        Random random = new Random();
        ArrayList<Integer> numbers = new ArrayList<Integer>();
        for (int i = 0; i < ARRAY_SIZE; ++i) {
            numbers.add(random.nextInt(ARRAY_SIZE));
        }
        //testing linear search time
        System.out.println("Start linear search");

        Instant startTime = Instant.now();

        System.out.println(linearSearchArray(numbers.get(1), numbers));
        System.out.println(linearSearchArray(numbers.get(5), numbers));
        System.out.println(linearSearchArray(numbers.get(900), numbers));
        System.out.println(linearSearchArray(numbers.get(3200), numbers));
        System.out.println(linearSearchArray(numbers.get(7400), numbers));
        System.out.println(linearSearchArray(numbers.get(9876), numbers));

        Instant endTime = Instant.now();
        Duration totalTime = Duration.between(startTime, endTime);
        System.out.println("Total time was " + (totalTime.getNano() / 1000000) + " milliseconds");

        startTime = Instant.now();
        System.out.println(linearSearchArray(2000000, numbers));
        System.out.println(linearSearchArray(-45, numbers));
        endTime = Instant.now();
        totalTime = Duration.between(startTime, endTime);
        System.out.println("Total time was " + (totalTime.getNano() / 1000000) + " milliseconds\n");

        //testing threaded search time
        System.out.println("Start threaded search");
        Instant startTime2 = Instant.now();
        System.out.println(threadedSearchArray(numbers.get(1), numbers));
        System.out.println(threadedSearchArray(numbers.get(5), numbers));
        System.out.println(threadedSearchArray(numbers.get(900), numbers));
        System.out.println(threadedSearchArray(numbers.get(3200), numbers));
        System.out.println(threadedSearchArray(numbers.get(7400), numbers));
        System.out.println(threadedSearchArray(numbers.get(9876), numbers));

        Instant endTime2 = Instant.now();
        Duration totalTime2 = Duration.between(startTime2, endTime2);
        System.out.println("Total time was " + (totalTime2.getNano() / 1000000) + " milliseconds");

        startTime2 = Instant.now();
        System.out.println(threadedSearchArray(2000000, numbers));
        System.out.println(threadedSearchArray(-45, numbers));
        endTime2 = Instant.now();
        totalTime2 = Duration.between(startTime2, endTime2);
        System.out.println("Total time was " + (totalTime2.getNano() / 1000000) + " milliseconds");
    }

    private static boolean linearSearchArray(int target, ArrayList<Integer> list) throws InterruptedException {
        // You can replace ThreadedSearch with LinearSearch to see this work with
        // the given linear search code.
        Searcher<Integer> searcher = new LinearSearch<>();

        return searcher.search(target, list);
    }
    private static boolean threadedSearchArray(int target, ArrayList<Integer> list) throws InterruptedException {

        Searcher<Integer> searcher = new ThreadedSearch<>(4);

        // This specifies 4 threads for the tests. It would be a good idea to play
        // with this and see how that changes things. Keep in mind that your number
        // of threads *may* need to evenly divide the length of the list being
        // searched (ARRAY_SIZE in this case).

        return searcher.search(target, list);
    }

}
