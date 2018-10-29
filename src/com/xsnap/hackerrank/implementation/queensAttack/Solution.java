package com.xsnap.hackerrank.implementation.queensAttack;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import java.awt.Point;

public class Solution {

    // Complete the queensAttack function below.
    static int queensAttack(int n, int k, int r_q, int c_q, int[][] o) {
        Set<Point> obstacles = new HashSet<>();
        for (int i=0; i<k; i++) {
            obstacles.add(new Point(o[i][0], o[i][1]));
        }
        int sum = 0;
        sum += checkCardinals(r_q, c_q, n, obstacles);
        sum += checkDiagonals(r_q, c_q, n, obstacles);
        return sum;
    }

    static int checkCardinals(int x, int y, int len, Set<Point> obstacles) {
        int count = 0;
        // up
        for (int i=y-1; i>0; i--) {
            if (!obstacles.contains(new Point(x, i))) {
                count++;
            } else {
                break;
            }
        }

        // down
        for (int i=y+1; i<=len; i++) {
            if (!obstacles.contains(new Point(x, i))) {
                count++;
            } else {
                break;
            }
        }

        // left
        for (int i=x-1; i>0; i--) {
            if (!obstacles.contains(new Point(i, y))) {
                count++;
            } else {
                break;
            }
        }

        // right
        for (int i=x+1; i<=len; i++) {
            if (!obstacles.contains(new Point(i, y))) {
                count++;
            } else {
                break;
            }
        }

        return count;
    }

    static int checkDiagonals(int x, int y, int len, Set<Point> obstacles) {
        int count = 0;
        // top left
        for (int i=x-1, j=y-1; i>0 && j>0; i--, j--) {
            if (!obstacles.contains(new Point(i, j))) {
                count++;
            } else {
                break;
            }
        }

        // top right
        for (int i=x+1, j=y-1; i<=len && j>0; i++, j--) {
            if (!obstacles.contains(new Point(i, j))) {
                count++;
            } else {
                break;
            }
        }

        // bottom right
        for (int i=x+1, j=y+1; i<=len && j<=len; i++, j++) {
            if (!obstacles.contains(new Point(i, j))) {
                count++;
            } else {
                break;
            }
        }

        // bottom left
        for (int i=x-1, j=y+1; i>0 && j<=len; i--, j++) {
            if (!obstacles.contains(new Point(i, j))) {
                count++;
            } else {
                break;
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

        String[] r_qC_q = scanner.nextLine().split(" ");

        int r_q = Integer.parseInt(r_qC_q[0]);

        int c_q = Integer.parseInt(r_qC_q[1]);

        int[][] obstacles = new int[k][2];

        for (int i = 0; i < k; i++) {
            String[] obstaclesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int obstaclesItem = Integer.parseInt(obstaclesRowItems[j]);
                obstacles[i][j] = obstaclesItem;
            }
        }

        int result = queensAttack(n, k, r_q, c_q, obstacles);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
