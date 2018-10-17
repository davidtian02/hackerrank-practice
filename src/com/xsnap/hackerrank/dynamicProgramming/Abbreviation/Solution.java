package com.xsnap.hackerrank.dynamicProgramming.Abbreviation;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import javafx.util.*;

public class Solution {

    // Complete the abbreviation function below.
    static String abbreviation(String a, String b) {
        if (a == null) {
            // blah
        }

        if (b.length() > a.length()) {
            return "NO";
        }

        return canConstruct(a, b) ? "YES" : "NO";
    }

    static Map<Pair<String, String>, Boolean> cache = new HashMap<>();

    static boolean canConstruct(String a, String b) {
        if (b.length() == 0) {
            for (int i=0; i<a.length(); i++) {
                if (Character.isUpperCase(a.charAt(i))) {
                    return false;
                }
            }
            return true;
        } else if (b.length() > a.length()) {
            return false;
        }

        if (a.length() == 1 && b.length() == 1) {
            return b.charAt(0) == a.charAt(0) || Character.toUpperCase(a.charAt(0)) == b.charAt(0);
        }

        Pair<String, String> p = new Pair<>(a, b);
        if (cache.containsKey(p)) {
            return cache.get(p);
        }

        for (int i=0; i<a.length(); i++) {
            if (a.charAt(i) != b.charAt(0)) {
                if (Character.toUpperCase(a.charAt(i)) == b.charAt(0)) {
                    String aSub = a.substring(i+1);
                    String bSub = b.substring(1);
                    boolean shouldMoveAlong = canConstruct(aSub, bSub);
                    cache.put(new Pair<>(aSub, bSub), shouldMoveAlong);
                    if (shouldMoveAlong) {
                        return true;
                    }

                    boolean shouldMoveOnlyA = canConstruct(aSub, b);
                    cache.put(new Pair<>(aSub, b), shouldMoveOnlyA);
                    return shouldMoveOnlyA;
                } else {
                    if (Character.isUpperCase(a.charAt(i))) {
                        return false;
                    }
                }
            } else {
                String aSub = a.substring(i+1);
                String bSub = b.substring(1);
                boolean shouldMoveBoth = canConstruct(aSub, bSub);
                cache.put(new Pair<>(aSub, bSub), shouldMoveBoth);
                return shouldMoveBoth;
            }
        }

        return false;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int qItr = 0; qItr < q; qItr++) {
            String a = scanner.nextLine();

            String b = scanner.nextLine();

            String result = abbreviation(a, b);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}

