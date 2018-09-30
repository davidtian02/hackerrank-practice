package com.xsnap.hackerrank.dynamicProgramming.equals;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Solution {

    // Complete the equal function below.
    static int equal(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        return equalize(arr);
    }

    static int equalize(int[] arr) {
        int min = Integer.MAX_VALUE;
        int ops;
        for (int i=0; i<arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        ops = computeOps(arr, min);
        ops = Math.min(ops, computeOps(arr, min-1));
        ops = Math.min(ops, computeOps(arr, min-2));

        return ops;
    }

    static int computeOps(int[] arr, int min) {
        int ops = 0;
        for (int i=0; i<arr.length; i++) {
            if (min != arr[i]) {
                ops += bringDownToBase(arr[i], min);
            }
        }
        return ops;
    }

    static int bringDownToBase(int a, int base) {
        int count = 0;
        if (a <= base) {
            throw new RuntimeException("logic error");
        }

        while (a != base) {
            if (a - 5 >= base) {
                a -= 5;
            } else if (a - 2 >= base) {
                a -= 2;
            } else if (a - 1 >= base) {
                a--;
            }
            count++;
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] arr = new int[n];

            String[] arrItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int arrItem = Integer.parseInt(arrItems[i]);
                arr[i] = arrItem;
            }

            int result = equal(arr);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
