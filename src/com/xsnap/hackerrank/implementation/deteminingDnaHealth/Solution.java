package com.xsnap.hackerrank.implementation.deteminingDnaHealth;

/**
 * VERY MUCH WIP
 */
import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

// MUCH WIP. NOT READY
public class Solution {

    static int computeScore(String[] genes, int[] health, int start, int end, String sequence) {
        TrieNode root = new TrieNode();
        String g;
        TrieNode temp;
        char k;
        for (int i=start; i<=end; i++) {
            g = genes[i];
            temp = root;
            for (int j=0; j<g.length(); j++) {
                k = g.charAt(j);
                if (!temp.children.containsKey(k)) {
                    temp.children.put(k, new TrieNode());
                }
                temp = temp.children.get(k);
            }
            temp.isLeaf = true;
            if (temp.healths == null) {
                temp.healths = new LinkedList<>();
            }
            temp.healths.add(health[i]);
        }

        int score = 0;
        // now compute score
        for (int i=0; i<sequence.length(); i++) {
            temp = root;
            for (int j=i; j<sequence.length(); j++) {
                k = sequence.charAt(j);
                if (temp.children.containsKey(k)) {
                    temp = temp.children.get(k);
                } else {
                    break;
                }
                if (temp.isLeaf) {
                    for (Integer s : temp.healths) {
                        score += s;
                    }
                }
            }
        }

        return score;
    }

    static class TrieNode {
        boolean isLeaf = false;
        List<Integer> healths = null;
        Map<Character, TrieNode> children = new HashMap<>();
        @Override public String toString() {return "isLeaf: " + isLeaf + ", healths: " + healths + ", children: " + children;}
    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int n = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        String[] genes = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int[] health = new int[n];

        String[] healthItems = scanner.nextLine().split(" ");
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        for (int i = 0; i < n; i++) {
            int healthItem = Integer.parseInt(healthItems[i]);
            health[i] = healthItem;
        }

        int s = scanner.nextInt();
        scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

        int minHealth = Integer.MAX_VALUE, maxHealth = Integer.MIN_VALUE;
        int score;
        for (int sItr = 0; sItr < s; sItr++) {
            String[] firstLastd = scanner.nextLine().split(" ");

            int first = Integer.parseInt(firstLastd[0]);

            int last = Integer.parseInt(firstLastd[1]);

            String d = firstLastd[2];

            score = computeScore(genes, health, first, last, d);
            if (score < minHealth) {
                minHealth = score;
            }

            if (score > maxHealth) {
                maxHealth = score;
            }
        }

        System.out.println(minHealth + " " + maxHealth);

        scanner.close();
    }
}
