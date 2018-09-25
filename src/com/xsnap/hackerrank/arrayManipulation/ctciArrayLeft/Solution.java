package com.xsnap.hackerrank.arrayManipulation.ctciArrayLeft;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] rotLeft3(int[] a, int d) {
        int temp, i2;
        for (int i=0; i<d; i++) {
            temp = a[i];
            i2 = (i+d)%a.length;
            a[i] = a[i2];
            a[i2] = temp;
        }
        return a;
    }

    // Complete the rotLeft function below.
    static int[] rotLeft(int[] a, int d) {
        if (d == a.length || d == 0 || a.length == 1) {
            return a;
        }

        // mod d
        int[] temp1 = new int[d];
        int[] temp2 = new int[a.length - d];
        for (int i=0; i<temp1.length; i++) {
            temp1[i] = a[i];
        }
        for (int i=0; i<temp2.length; i++) {
            temp2[i] = a[d+i];
        }

        System.out.println(Arrays.toString(temp1));
        System.out.println(Arrays.toString(temp2));
        for (int i=0; i<temp2.length; i++) {
            a[i] = temp2[i];
        }
        for (int i=0; i<temp1.length; i++) {
            a[a.length - d + i] = temp1[i];
        }
        return a;
    }

    static int[] rotLeft2(int[] a, int d) {
        Queue<Integer> queue = new LinkedList<>();
         for (int i=0; i<a.length; i++) {
             queue.add(a[i]);
         }
         int j=0;
         while (j<d) {
             queue.add(queue.remove());
             j++;
         }

         for (int i=0; i<a.length; i++) {
             a[i] = queue.remove();
         }
         return a;
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
//
//        String[] nd = scanner.nextLine().split(" ");
//
//        int n = Integer.parseInt(nd[0]);
//
//        int d = Integer.parseInt(nd[1]);
//
//        int[] a = new int[n];
//
//        String[] aItems = scanner.nextLine().split(" ");
//        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
//
//        for (int i = 0; i < n; i++) {
//            int aItem = Integer.parseInt(aItems[i]);
//            a[i] = aItem;
//        }
//
//        int[] result = rotLeft(a, d);
//
//        for (int i = 0; i < result.length; i++) {
//            bufferedWriter.write(String.valueOf(result[i]));
//
//            if (i != result.length - 1) {
//                bufferedWriter.write(" ");
//            }
//        }
//
//        bufferedWriter.newLine();
//
//        bufferedWriter.close();
//
//        scanner.close();
        int[] x = new int[]{1,2,3,4,5};
        x = rotLeft(x, 3);
        System.out.println(Arrays.toString(x));
    }
}
