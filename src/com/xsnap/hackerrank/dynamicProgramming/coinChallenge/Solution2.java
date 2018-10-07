package com.xsnap.hackerrank.dynamicProgramming.coinChallenge;

import java.io.IOException;
import java.util.*;

public class Solution2 {

    // Complete the getWays function below.
    static long getWays(long n, long[] c) {
        if (n==0 || c==null || c.length == 0) {
            return 0;
        }
        sCombinations = new HashSet<>();
        sCache = new HashMap<>();

        List<Long> list = new LinkedList<>();
        for (long e : c) {
            list.add(e);
        }
        Collections.sort(list);
        Queue<Long> queue = new LinkedList<>(list);

        return canCount(n, queue, 0);

//        System.out.println(sCache);

//        return sCombinations.size();
    }

    static Set<List<Long>> sCombinations;
    static Map<Long, Set<List<Long>>> sCache;
    static int canCount(long n, Queue<Long> coins, long previousDenomination) {
        if (n<0) {
            return 0;
        }

//        Collections.sort(previous);
        if (n == 0) {
//            sCombinations.add(previous);
            return 1;
        }

        if (coins.isEmpty()) {
            return (n == previousDenomination) ? 1 : 0;
        }

        long c = coins.remove();

        if (c == n) {
            return 1;
        }

        long temp = 0;
        int ways = 0;
        while (temp < n) {
            ways += canCount(n - temp, new LinkedList<>(coins), c);
            temp += c;
        }

        return ways;
//        if (sCache.containsKey(n)) {
//            if (sCache.get(n).contains(previous)) {
//                System.out.println("cache hit previous");
//                return;
//            }
//        }
//
//        System.out.println("n = " + n + ", previous: " + previous);
//
//        for (long l : c) {
//            List<Long> next = new LinkedList<>(previous);
//            next.add(l);
//
//            Collections.sort(next);
//            if (sCache.containsKey(n)) {
//                if (sCache.get(n).contains(next)) {
//                    System.out.println("cache hit");
//                    return;
//                } else {
//                    sCache.get(n).add(next);
//                }
//            } else {
//                sCache.put(n, new HashSet<>());
//            }
//
//            canCount(n-l, c, next);
//        }
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

        System.out.println(getWays(4, new long[]{1, 2, 3}));
    }
}

// TODO JSON parser