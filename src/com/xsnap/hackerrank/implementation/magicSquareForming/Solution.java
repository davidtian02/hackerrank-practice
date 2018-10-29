package com.xsnap.hackerrank.implementation.magicSquareForming;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the formingMagicSquare function below.
    static int formingMagicSquare(int[][] s) {
        int[][] magicSquare = new int[][]{
                {4, 9, 2},
                {3, 5, 7},
                {8, 1, 6},
        };

        compare(magicSquare, s);

        magicSquare = rotate(magicSquare);

        compare(magicSquare, s);

        magicSquare = rotate(magicSquare);

        compare(magicSquare, s);

        magicSquare = rotate(magicSquare);

        compare(magicSquare, s);

        return sCurrentMax;
    }

    static void compare(int[][] solution, int[][] s) {
        // check as is
        diff(solution, s);
        // check flip horizontal
        diff(flipHorizontal(solution), s);
        // check flip vertical
        diff(flipVertical(solution), s);
        // check flip diagonal
        diff(flipDiagonalTopLeft(solution), s);
        // check flip other diagonal
        diff(flipDiagonalTopRight(solution), s);
    }

    static int[][] flipHorizontal(int[][] s) {
        return new int[][] {
                {s[0][2], s[0][1], s[0][0]},
                {s[1][2], s[1][1], s[1][0]},
                {s[2][2], s[2][1], s[2][0]},
        };
    }

    static int[][] flipVertical(int[][] s) {
        return new int[][] {
                {s[2][0], s[2][1], s[2][2]},
                {s[1][0], s[1][1], s[1][2]},
                {s[0][0], s[0][1], s[0][2]},
        };
    }

    static int[][] flipDiagonalTopLeft(int[][] s) {
        return new int[][] {
                {s[2][2], s[1][2], s[0][2]},
                {s[2][1], s[1][1], s[0][1]},
                {s[2][0], s[1][0], s[0][0]},
        };
    }

    static int[][] flipDiagonalTopRight(int[][] s) {
        return new int[][] {
                {s[0][0], s[1][0], s[2][0]},
                {s[0][1], s[1][1], s[2][1]},
                {s[0][2], s[1][2], s[2][2]},
        };
    }

    static void diff(int[][] solution, int[][] s) {
        int sum = 0;
        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                sum += Math.abs(solution[i][j] - s[i][j]);
            }
        }
        if (sum < sCurrentMax) {
            sCurrentMax = sum;
        }
    }

    static int[][] rotate(int[][] s) {
        return new int[][] {
                {s[2][0], s[1][0], s[0][0]},
                {s[2][1], s[1][1], s[0][1]},
                {s[2][2], s[1][2], s[0][2]},
        };
    }

    static int sCurrentMax = Integer.MAX_VALUE;

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int[][] s = new int[3][3];

        for (int i = 0; i < 3; i++) {
            String[] sRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int sItem = Integer.parseInt(sRowItems[j]);
                s[i][j] = sItem;
            }
        }

        int result = formingMagicSquare(s);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
