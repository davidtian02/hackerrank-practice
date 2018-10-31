package com.xsnap.hackerrank.friendCircleQueries;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int[] maxCircle(int[][] queries) {
        Map<Integer, Node> groups = new HashMap<>();
        int maxCount = Integer.MIN_VALUE;
        int[] result = new int[queries.length];
        int newCount;

        for (int i=0; i<queries.length; i++) {
            int a = queries[i][0];
            int b = queries[i][1];

            if (groups.containsKey(a) && groups.containsKey(b)) {
                Node pA, pB;
                pA = groups.get(a);
                pB = groups.get(b);
                Node rootA, rootB;
                rootA = pA.findRoot();
                rootB = pB.findRoot();
                Node minRoot, maxRoot;
                if (rootA == rootB) {
                    newCount = maxCount;
                } else {
                    if (rootA.val < rootB.val) {
                        minRoot = rootA;
                        maxRoot = rootB;
                    } else {
                        minRoot = rootB;
                        maxRoot = rootA;
                    }
                    maxRoot.parent = minRoot;
                    minRoot.childCount += maxRoot.childCount;
                    newCount = minRoot.childCount;
                }
            } else if (groups.containsKey(a) || groups.containsKey(b)) {
                Node existing;
                Node other;
                if (groups.containsKey(a)) {
                    existing = groups.get(a);
                    other = new Node(b);
                    groups.put(b, other);
                } else {
                    existing = groups.get(b);
                    other = new Node(a);
                    groups.put(a, other);
                }

                Node root = existing.findRoot();
                if (root.val < other.val) {
                    other.parent = root;
                    root.childCount++;
                    newCount = root.childCount;
                } else {
                    root.parent = other;
                    other.childCount += root.childCount;
                    newCount = other.childCount;
                }
            } else {
                Node minNode, maxNode;
                if (a < b) {
                    minNode = new Node(a);
                    maxNode = new Node(b);
                    groups.put(a, minNode);
                    groups.put(b, maxNode);
                } else {
                    maxNode = new Node(a);
                    minNode = new Node(b);
                    groups.put(b, minNode);
                    groups.put(a, maxNode);
                }

                maxNode.parent = minNode;
                minNode.childCount++;
                newCount = minNode.childCount;
            }

            if (newCount > maxCount) {
                maxCount = newCount;
            }

            result[i] = maxCount;
        }

        return result;
    }

    static class Node {
        int val;
        int childCount;
        Node parent = null;
        Node(int v) {
            val = v;
            childCount = 1;
        }

        Node findRoot() {
            if (parent == null) {
                return this;
            } else {
                return parent.findRoot();
            }
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int q = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[][] queries = new int[q][2];

        for (int i = 0; i < q; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 2; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        int[] ans = maxCircle(queries);

        for (int i = 0; i < ans.length; i++) {
            bufferedWriter.write(String.valueOf(ans[i]));

            if (i != ans.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
