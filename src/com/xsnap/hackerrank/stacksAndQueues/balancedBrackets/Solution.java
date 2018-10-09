package com.xsnap.hackerrank.stacksAndQueues.balancedBrackets;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isBalanced function below.
    static String isBalanced(String s) {
        if (s == null || (s.length() & 0x01) == 1) {
            return "NO";
        }
        if (s.length() == 0) {
            return "YES";
        }

        return isValid(s) ? "YES" : "NO";
    }

    static boolean isValid(String input) {
        char c, t;
        Stack<Character> stack = new Stack<>();
        for (int i=0; i<input.length(); i++) {
            c = input.charAt(i);
            if (isOpeningChar(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                }
                t = stack.pop();
                if (!isMatching(t, c)) {
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

    private static boolean isOpeningChar(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isMatching(char open, char close) {
        if (open == '(') {
            return close == ')';
        } else if (open == '[') {
            return close == ']';
        } else if (open == '{') {
            return close == '}';
        }

        return false; // wtf?
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int tItr = 0; tItr < t; tItr++) {
            String s = scanner.nextLine();

            String result = isBalanced(s);

            bufferedWriter.write(result);
            bufferedWriter.newLine();
        }

        bufferedWriter.close();

        scanner.close();
    }
}
