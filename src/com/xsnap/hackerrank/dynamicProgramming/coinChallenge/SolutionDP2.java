package com.xsnap.hackerrank.dynamicProgramming.coinChallenge;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import javafx.util.Pair;

public class SolutionDP2 {

    // Complete the getWays function below.
    static long getWays(long n, Long[] c) {
        // List<Long> list = new ArrayList<>(c.length);
        List<Long> list=Arrays.asList(c);
        sCache = new HashMap<>();
        return findWays(n, list);
    }

    static Map<Pair<Long, List<Long>>, Long> sCache;

    static long findWays(long target, List<Long> coins) {
        if (target == 0) {
            return 1;
        }
        if (target < 0) {
            return 0;
        }

        Pair<Long, List<Long>> pair = new Pair<>(target, coins);
        if (sCache.containsKey(pair)) {
            return sCache.get(pair);
        }

        long sum = 0;
        for (int i=0; i<coins.size(); i++) {
            long t = target - coins.get(i);
            ArrayList<Long> remainderCoins = new ArrayList<>(coins.subList(i, coins.size()));
            long ways = findWays(t, remainderCoins);
            if (ways > 0) {
                sCache.put(new Pair<>(t, remainderCoins), ways);
            }
            sum += ways;
        }

        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        Long[] c = new Long[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            Long cItem = Long.parseLong(cItems[i]);
            c[i] = cItem;
        }

        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'

        long ways = getWays(n, c);

        bufferedWriter.write("" + ways);
        bufferedWriter.newLine();
        bufferedWriter.close();

        scanner.close();
    }
}
