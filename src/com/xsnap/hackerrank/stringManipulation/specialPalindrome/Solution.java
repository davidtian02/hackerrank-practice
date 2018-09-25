package com.xsnap.hackerrank.stringManipulation.specialPalindrome;

import java.util.LinkedList;
import java.util.List;
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the substrCount function below.
    static long substrCount(int n, String s) {
        long count = 0;
        int j, k;
        for (int i=0; i<n; i++) {
            count++; // letter itself

            j = i+1;
            while (j<n && s.charAt(i) == s.charAt(j)) {
                count++;
                j++;
            }

            j = i+1;
            k = i-1;
            char temp = '!';
            while (j<n && k>=0) {
                if (temp == '!') {
                    temp = s.charAt(j);
                }
                if (s.charAt(j) == s.charAt(k) && temp == s.charAt(j) && s.charAt(i) != temp) {
                    count++;
                } else {
                    break;
                }
                j++;
                k--;
            }
        }

        return count;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String s = scanner.nextLine();

        long result = substrCount(n, s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
