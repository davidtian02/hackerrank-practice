package com.xsnap.hackerrank.stringManipulation.commonChild;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
// 4:50
public class Solution {

    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        Set<Character> set1 = createSet(s1);
        Set<Character> set2 = createSet(s2);

        StringBuilder tempSb1 = new StringBuilder();
        StringBuilder tempSb2 = new StringBuilder();
        intersectOnlyCommonChars(s1, set2, tempSb1);
        intersectOnlyCommonChars(s2, set1, tempSb2);

        System.out.println(tempSb1);
        System.out.println(tempSb2);

        return getLongest(tempSb1, tempSb2);
    }

    private static int getLongest(StringBuilder s1, StringBuilder s2) {
        int i=0;
        int j;
        int count;
        int max = 0;
        while (i<s1.length()) {
            j = 0;
            while(j<s2.length()) {
                count = 0;
                System.out.println(i + ", " + j + ", " + s1.charAt(i) + " <-> " + s2.charAt(j));
                if (s1.charAt(i)==s2.charAt(j)) {

                    int a = i;
                    int b = j;
                    while(a < s1.length() && b < s2.length()) {
                        if (s1.charAt(a) == s2.charAt(b)) {
                            count++;
                            a++;
                            b++;
                        } else {
                            b++;
                        }
                    }
                }

                j++;


                if (count > max) {
                    System.out.println("updated count: " + count);
                    max = count;
                }
            }
            i++;
        }

        return max;
    }

    private static void intersectOnlyCommonChars(String s1, Set<Character> set2, StringBuilder tempSb1) {
        for (char c : s1.toCharArray()) {
            if (set2.contains(c)) {
                tempSb1.append(c);
            }
        }
    }

    private static Set<Character> createSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray()) {
            set.add(c);
        }
        return set;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String s1 = scanner.nextLine();
//
//        String s2 = scanner.nextLine();
//
//        int result = commonChild(s1, s2);
//
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
//
//        scanner.close();
        System.out.println(commonChild("HNHAN", "NHAAAAN"));
    }
}
