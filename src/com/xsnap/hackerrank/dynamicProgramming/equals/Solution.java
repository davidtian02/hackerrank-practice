package com.xsnap.hackerrank.dynamicProgramming.equals;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the equal function below.
    static int equal(int[] arr) {
        // TODO edge cases
//        int[] arr1 = new int[arr.length];

//        System.out.println(Arrays.toString(arr1));

        minOperations(arr, 0);

        return sFinalNumSteps;
    }

    static int sFinalNumSteps = -1;
    static boolean sShouldBreak = false;
    static void minOperations(int[] arr, int stepsTakenSoFar) {
        if (sShouldBreak) {
            return;
        }

        if (isBalanced(arr)) {
            sFinalNumSteps = stepsTakenSoFar;
            return;
        }

        for (int i=0; i<arr.length; i++) {
            minOperations(addNumExcludingIndex(arr, 1, i), stepsTakenSoFar+1);
            minOperations(addNumExcludingIndex(arr, 3, i), stepsTakenSoFar+1);
            minOperations(addNumExcludingIndex(arr, 5, i), stepsTakenSoFar+1);
        }

        List<Integer> l = new LinkedList<>();
        Iterator<Integer> iterator = l.iterator();
    }

    private static int[] addNumExcludingIndex(int[] arr, int n, int indexToExclude) {
        int[] arr1 = new int[arr.length];
        System.arraycopy(arr, 0, arr1, 0, arr.length);

        for (int i=0; i<arr1.length; i++) {
            if (i != indexToExclude) {
                arr1[i] = arr1[i] + n;
            }
        }

        return arr1;
    }
//    static int minOperations(int[] arr, int increment, int indexToExclude) {
//        if (isBalanced(arr)) {
//            return 1;
//        }
//        int[] arr1 = new int[arr.length];
//        System.arraycopy(arr, 0, arr1, 0, arr.length);
//
//        for (int i=0; i<arr1.length; i++) {
//            if (minOperations(arr, 1, i)) {
//                return 1;
//            } else {
//
//            }
//        }
//
//        return 0;
//    }

    static boolean isBalanced(int[] arr) {
        if (arr.length == 0) {
            return true;
        }
        int k = arr[0];
        for (int a : arr) {
            if (a != k) {
                return false;
            }
        }
        return true;
    }
    // 1:10

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        int t = scanner.nextInt();
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int tItr = 0; tItr < t; tItr++) {
//            int n = scanner.nextInt();
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            int[] arr = new int[n];
//
//            String[] arrItems = scanner.nextLine().split(" ");
//            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//            for (int i = 0; i < n; i++) {
//                int arrItem = Integer.parseInt(arrItems[i]);
//                arr[i] = arrItem;
//            }
//
//            int result = equal(arr);
//
//            bufferedWriter.write(String.valueOf(result));
//            bufferedWriter.newLine();
//        }
//
//        bufferedWriter.close();
//
//        scanner.close();
//        System.out.println(equal(new int[]{1, 1, 1}));
        System.out.println(equal(new int[]{1, 2, 1}));
    }
}
