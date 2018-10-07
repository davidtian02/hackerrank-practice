package com.xsnap.hackerrank.dynamicProgramming.coinChallenge;

import javafx.util.Pair;

import java.io.IOException;
import java.util.*;

public class SolutionDP {

    // Complete the getWays function below.
    static long getWays(long n, long[] c) {
        if (c == null || c.length < 1) {
            return 0;
        }

        if (n == 0) {
            return 1;
        }

        sCache = new HashMap<>();
        Queue<Long> q = new LinkedList<>();
        for (long a : c) {
            q.add(a);
        }
        return findWays(n, q);
    }

    static Map<Pair<Long, Queue<Long>>, Long> sCache;

    static long findWays(long target, Queue<Long> coins) {
        if (coins.isEmpty()) {
            return 0; // maybe?
        }

        long c = coins.remove();
        if (c == target) {
            return 1;
        } else if (c > target) {
            return 0;
        }

        long sum = 0;
        long originalTarget = target;
        while(target > 0) {
            Queue<Long> remainderCoins = new LinkedList<>(coins);
            Pair<Long, Queue<Long>> pair = new Pair<>(target, remainderCoins);
            long ways;
            if (sCache.containsKey(pair)) {
                ways = sCache.get(pair);
            } else {
                ways = findWays(target, remainderCoins);
            }
            sum += ways;
            target -= c;
        }

        if (target == 0) {
            sum++;
        }

        sCache.put(new Pair<>(originalTarget, new LinkedList<>(coins)), sum);
        return sum;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String[] nm = scanner.nextLine().split(" ");
//
//        int n = Integer.parseInt(nm[0]);
//
//        int m = Integer.parseInt(nm[1]);
//
//        long[] c = new long[m];
//
//        String[] cItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < m; i++) {
//            long cItem = Long.parseLong(cItems[i]);
//            c[i] = cItem;
//        }
//
//        // Print the number of ways of making change for 'n' units using coins having the values given by 'c'
//
//        long ways = getWays(n, c);
//
//        bufferedWriter.write("" + ways);
//        bufferedWriter.newLine();
//        bufferedWriter.close();
//
//        scanner.close();

//        System.out.println(getWays(4, new long[]{1, 2, 3}));
//        System.out.println(getWays(4, new long[]{1, 2, 3}));
//        System.out.println(getWays(10, new long[]{2, 5, 3, 6}));
//        System.out.println(getWays(10, new long[]{1, 2, 3, 4, 5}));
        System.out.println(getWays(20, new long[]{1, 5, 10, 20}));
    }
}

// TODO JSON parser
// 1, 1, 1, 1, 1, 1, 1, 1, 1, 1
// 1, 1, 1, 1, 1, 1, 1, 1, 2
// 1, 1, 1, 1, 1, 1, 1, 3
// 1, 1, 1, 1, 1, 1, 4
// 1, 1, 1, 1, 1, 5
