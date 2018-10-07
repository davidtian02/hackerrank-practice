package com.xsnap.hackerrank.dynamicProgramming.sherlockAndCost;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the cost function below.
    static int cost(int[] B) {
        // edge cases
        if (B.length < 2) {
            return -1;
        }
        sCache1 = new int[B.length];
        sCache2 = new int[B.length];
        Arrays.fill(sCache1, -1);
        Arrays.fill(sCache2, -1);

        return Math.max(findMax(B, 1, 1), findMax(B, B[0], 1));
    }

    static int[] sCache1;
    static int[] sCache2;
    static int findMax(int arr[], int previous, int index) {
        if (index >= arr.length) {
            throw new RuntimeException("bad state");
        } else if (index == arr.length - 1) {
            int delta1 = Math.abs(previous - arr[arr.length - 1]);
            int delta2 = Math.abs(previous - 1);
            return delta1 > delta2 ? delta1 : delta2;
        }

        int current = arr[index];

        int maxRemainder1;
        if (sCache1[index+1] != -1) {
            maxRemainder1 = sCache1[index+1];
        } else {
            maxRemainder1 = findMax(arr, current, index+1);
        }
        int prevLowCurrHigh = Math.abs(previous - current) + maxRemainder1;
        sCache1[index+1] = maxRemainder1;

        int maxRemainder2;
        if (sCache2[index+1] != -1) {
            maxRemainder2 = sCache2[index+1];
        } else {
            maxRemainder2 = findMax(arr, 1, index+1);
        }
        int prevHighCurrLow = Math.abs(previous - 1) + maxRemainder2;
        sCache2[index+1] = maxRemainder2;

        return Math.max(prevLowCurrHigh, prevHighCurrLow);
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            int n = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            int[] B = new int[n];

            String[] BItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int i = 0; i < n; i++) {
                int BItem = Integer.parseInt(BItems[i]);
                B[i] = BItem;
            }

            int result = cost(B);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
