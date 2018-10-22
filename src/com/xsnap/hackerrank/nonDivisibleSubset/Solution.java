package com.xsnap.hackerrank.nonDivisibleSubset;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int nonDivisibleSubset(int k, int[] S) {
        List<Integer> arr = new ArrayList<>(S.length);
        for (int i=0; i<S.length; i++) {
            arr.add(S[i]);
        }

        int[] mods = new int[k];
        int mod;
        for (Integer e : arr) {
            mod = e%k;
            mods[mod] = mods[mod] + 1;
        }

        int count = 0;
        if (mods[0] > 0) {
            mods[0] = 1;
            count++;
        }
        int mid = k >> 1;
        if ((k&0x01) == 0) {
            mods[mid] = mods[mid] > 0 ? 1 : 0;
        }

        for (int i=1; i<=mid; i++) {
            count += mods[i] > mods[k-i] ? mods[i] : mods[k-i];
        }

        return count;
    }

    // Complete the nonDivisibleSubset function below.
    static int nonDivisibleSubset2(int k, int[] S) {
        // not this simple but
        boolean[] modArr = new boolean[k];
        int count = 0;
        int mod;
        for (int i=0; i<S.length; i++) {
            mod = S[i]%k;
            if (!modArr[mod]) {
                if (!modArr[k-mod]) {
                    count++;
                    modArr[mod] = true;
                }
            } else {
                if (mod + mod != k) {
                    count++;
                }
            }
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[] S = new int[n];

        String[] SItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int SItem = Integer.parseInt(SItems[i]);
            S[i] = SItem;
        }

        int result = nonDivisibleSubset(k, S);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
