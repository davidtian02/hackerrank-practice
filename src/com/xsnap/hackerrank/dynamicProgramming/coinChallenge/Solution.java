package com.xsnap.hackerrank.dynamicProgramming.coinChallenge;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the getWays function below.
    static long getWays(long n, long[] c) {
        if (n==0 || c==null || c.length == 0) {
            return 0;
        }

        canCount(n, c, new LinkedList<>());

        // System.out.println(sCombinations);
        // sCombinations = dedupe(sCombinations);
        // System.out.println(sCombinations.size());

        return sCombinations.size();
    }

    static Set<List<Long>> sCombinations = new HashSet<>();
    static void canCount(long n, long[] c, List<Long> previous) {
        if (n<0) {
            return;
        } else if (n == 0) {
            Collections.sort(previous);
            sCombinations.add(previous);
        }

        for (long l : c) {
            List<Long> next = new LinkedList<>(previous);
            next.add(l);
            canCount(n-l, c, next);
        }
    }

    // static Set<List<Long>> dedupe(Set<List<Long>> set) {
    //     // static Set<List<Long>> sCombinations = new HashSet<>();
    //     List<List<Long>> matrix = new LinkedList<>(set);
    //     Collections.sort(matrix, new Comparator<List<Long>>(){
    //         @Override
    //         public int compare(List<Long> o1, List<Long> o2) {
    //             if (o1.size() < o2.size()) {
    //                 return -1;
    //             } else if (o1.size() > o2.size()) {
    //                 return 1;
    //             } else {
    //                 return 0;
    //             }
    //         }
    //     });

    //     if (set.size() < 2) {
    //         return set;
    //     }

    //     Set<List<Long>> result = new HashSet<>();
    //     for (int i=0; i<matrix.size()-1; i++) {
    //         result.add(matrix.get(i));
    //         // while (i<matrix.size()-1 && eq(matrix.get(i), matrix.get(i+1))) {
    //         //     i++;
    //         // }
    //     }
    //     return result;
    // }

    // static boolean eq(List<Long> l1, List<Long> l2) {
    //     if (l1.size() != l2.size()) {
    //         return false;
    //     }

    //     Iterator<Long> iter1 = l1.iterator();
    //     Iterator<Long> iter2 = l2.iterator();

    //     while(iter1.hasNext()) {
    //         if (iter1.next() != iter2.next()) {
    //             return false;
    //         }
    //     }

    //     return true;
    // }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        long[] c = new long[m];

        String[] cItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < m; i++) {
            long cItem = Long.parseLong(cItems[i]);
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

// TODO JSON parser