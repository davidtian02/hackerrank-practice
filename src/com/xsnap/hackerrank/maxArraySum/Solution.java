package com.xsnap.hackerrank.maxArraySum;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the maxSubsetSum function below.
    static int maxSubsetSum(int[] arr) {
        if (arr.length == 1) { // not null, or 0
            return arr[0];
        }

        cache = new int[arr.length];
        for (int i=0; i<cache.length; i++) cache[i] = Integer.MIN_VALUE;
        // List<Integer> set = new LinkedList<>();
        // generateSets(arr, set, 0);
        // System.out.println(totalSets);
        // Set<List<Integer>> finalSet = new HashSet<>();
        // for (List<Integer> l : totalSets) {
        //     if (l.size() > 1) {
        //         finalSet.add(l);
        //     }
        // }
        // totalSets.clear();

        // System.out.println(finalSet);
        // for (List<Integer> l : finalSet) {
        //     // TODO optimize for sum
        //     int sum = 0;
        //     for (int e : l) {
        //         sum += e;
        //     }
        //     if (sum > max) {
        //         max = sum;
        //     }
        // }

        generateSets(arr, 0, 0, 0);
        for (int a : allSums) {
            if (a > max) {
                max = a;
            }
        }
        return max;
    }
    static int max = Integer.MIN_VALUE;
    // static Set<List<Integer>> totalSets = new HashSet<>();
    static List<Integer> allSums = new LinkedList<>();
    static int[] cache;
    static void generateSets(int[] arr, int previousNumElementsVisited, int previousSum, int index) {
        if (index >= arr.length) {
            return;
        }

        if (cache[index] != Integer.MIN_VALUE) {
            return;
        }

        for (int i=index; i<arr.length; i++) {
            // List<Integer> set = new LinkedList<>(previousSet);
            // set.add(arr[i]);
            // totalSets.add(set);
            int newSum = previousSum + arr[i];
            if (previousNumElementsVisited > 1) {
                allSums.add(newSum);
            }
            generateSets(arr, 2, newSum, i+2);
        }
    }
    // static int findMax(int[] arr, int sumOfPreviousElements, int previousIndex, int index) {
    //     if (index >= arr.length) {
    //         return 0;
    //     }

    //     if (previousIndex + 1 == index) {
    //         return 0;
    //     }

    //     int val1 = Integer.MIN_VALUE;
    //     int val2 = Integer.MIN_VALUE;
    //     for (int i=index; i<arr.length; i++) {
    //     // if (canInclude()) {
    //         val1 = sumOfPreviousElements + findMax(arr, sumOfPreviousElements, index, i);
    //         val2 = sumOfPreviousElements + findMax(arr, sumOfPreviousElements, index, i+2);
    //     // }
    //     }
    //     return (val1 > val2) ? val1 : val2;

    //     // if (index == arr.length - 1) {
    //     //     int tempMax = sumOfPreviousElements + arr[index];
    //     //     if (tempMax > max) {
    //     //         max = tempMax;
    //     //     }
    //     // } else {
    //     //     sumOfPreviousElements += arr[index];
    //     //     findMax(arr, sumOfPreviousElements, index + 1);
    //     // }
    // }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] arr = new int[n];

        String[] arrItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int arrItem = Integer.parseInt(arrItems[i]);
            arr[i] = arrItem;
        }

        int res = maxSubsetSum(arr);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
