package com.xsnap.hackerrank.climbingLeaderBoard;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

/**
 * TODO: actually, you only need that single arraylist with unique rankings going in descending order.
 */
public class Solution {

    // Complete the climbingLeaderboard function below.
    static int[] climbingLeaderboard(int[] scores, int[] alice) {
        // edge cases

        // ranking mapping
        List<Integer> sortedRanking = new ArrayList<>(scores.length);
        Map<Integer, Integer> rankingsMapping = createMapping(scores, sortedRanking);

        // System.out.println(sortedRanking);
        // System.out.println(rankingsMapping);

        // create tree
        BstNode root = createTree(sortedRanking, rankingsMapping);
        // printTree(root);

        // traverse tree with Alice's score. return ranking+1
        int[] rankings = findRankings(root, rankingsMapping, alice);

        return rankings;
    }

    static void printTree(BstNode root) {
        Queue<BstNode> q = new LinkedList<>();
        q.offer(root);
        while(!q.isEmpty()) {
            BstNode n = q.poll();
            System.out.print(n.score + ", ");
            if (n.left != null) {
                q.offer(n.left);
            }
            if (n.right != null) {
                q.offer(n.right);
            }
        }
        System.out.println();
    }

    static Map<Integer, Integer> createMapping(int[] scores, List<Integer> list) {
        Map<Integer, Integer> map = new HashMap<>();

        int previous = -1;
        int rank = 1;
        for (int i=0; i<scores.length; i++) {
            if (previous != scores[i]) {
                previous = scores[i];
                map.put(previous, rank);
                list.add(previous);
                rank++;
            }
        }

        return map;
    }

    static BstNode createTree(List<Integer> scores, Map<Integer, Integer> rankingsMapping) {
        Collections.reverse(scores);
        return createTreeHelper(scores, 0, scores.size() - 1, rankingsMapping);
    }

    static BstNode createTreeHelper(List<Integer> scores, int start, int end, Map<Integer, Integer> rankingsMapping) {
        if (start > end || start < 0 || end >= scores.size()) {
            return null;
        }

        int mid = ((end - start) >> 1) + start;
        BstNode root = new BstNode(scores.get(mid), rankingsMapping.get(scores.get(mid)));
        root.left = createTreeHelper(scores, start, mid-1, rankingsMapping);
        root.right = createTreeHelper(scores, mid+1, end, rankingsMapping);
        return root;
    }

    static int[] findRankings(BstNode root, Map<Integer, Integer> rankingsMapping, int[] aliceScores) {
        int[] rankings = new int[aliceScores.length];

        for (int i=0; i<aliceScores.length; i++) {
            rankings[i] = traverse(root, aliceScores[i]);
        }

        return rankings;
    }

    static int traverse(BstNode root, int target) {
        if (root.score == target) {
            return root.ranking;
        } else {
            if (root.score > target) {
                if (root.left == null) {
                    return root.ranking + 1;
                }
                return traverse(root.left, target);
            } else {
                if (root.right == null) {
                    return root.ranking;
                }
                return traverse(root.right, target);
            }
        }
    }

    static class BstNode {
        int score;
        int ranking;
        BstNode left, right;
        public BstNode(int s, int r) {
            score = s;
            ranking = r;
        }
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int scoresCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] scores = new int[scoresCount];

        String[] scoresItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < scoresCount; i++) {
            int scoresItem = Integer.parseInt(scoresItems[i]);
            scores[i] = scoresItem;
        }

        int aliceCount = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] alice = new int[aliceCount];

        String[] aliceItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < aliceCount; i++) {
            int aliceItem = Integer.parseInt(aliceItems[i]);
            alice[i] = aliceItem;
        }

        int[] result = climbingLeaderboard(scores, alice);

        for (int i = 0; i < result.length; i++) {
            bufferedWriter.write(String.valueOf(result[i]));

            if (i != result.length - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
