package com.xsnap.hackerrank.dictionaries.frequencyQueries;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the freqQuery function below.
    static List<Integer> freqQuery(List<int[]> queries) {
        List<Integer> result = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>(); // TODO should i use long?
        Map<Integer, Set<Integer>> freqMap = new HashMap();
        int operation;
        int operand;
        boolean found;
        int frequency;
        for (int[] q : queries) {
            operation = q[0];
            operand = q[1];
            switch(operation) {
                case 1:
                    if (!map.containsKey(operand)) {
                        map.put(operand, 1);
                        frequency = 0;
                    } else {
                        frequency = map.get(operand);
                        map.put(operand, frequency+1);
                    }

                    if (frequency > 0) {
                        freqMap.get(frequency).remove(operand);
                    }
                    if (!freqMap.containsKey(frequency+1)) {
                        freqMap.put(frequency+1, new HashSet<>());
                    }
                    freqMap.get(frequency+1).add(operand);

                    break;
                case 2:
                    if (map.containsKey(operand)) {
                        frequency = map.get(operand);
                        if (frequency > 0) {
                            map.put(operand, frequency - 1);

                            freqMap.get(frequency).remove(operand);
                            if (!freqMap.containsKey(frequency-1)) {
                                freqMap.put(frequency-1, new HashSet<>());
                            }
                            freqMap.get(frequency-1).add(operand);
                        }
                    }
                    break;
                case 3:
                    found = false;
                    if (freqMap.containsKey(operand)) {
                        if (!freqMap.get(operand).isEmpty()) {
                            found = true;
                        }
                    }
                    result.add( found ? 1 : 0);
                    break;
                default:
                    throw new RuntimeException("shouldnt happen");
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
            int q = Integer.parseInt(bufferedReader.readLine().trim());
            List<int[]> queries = new ArrayList<>(q);
            Pattern p  = Pattern.compile("^(\\d+)\\s+(\\d+)\\s*$");
            for (int i = 0; i < q; i++) {
                int[] query = new int[2];
                Matcher m = p.matcher(bufferedReader.readLine());
                if (m.matches()) {
                    query[0] = Integer.parseInt(m.group(1));
                    query[1] = Integer.parseInt(m.group(2));
                    queries.add(query);
                }
            }
            List<Integer> ans = freqQuery(queries);
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")))) {
                bufferedWriter.write(
                        ans.stream()
                                .map(Object::toString)
                                .collect(joining("\n"))
                                + "\n");
            }
        }
    }

    // public static void main(String[] args) throws IOException {
    //     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
    //     BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

    //     int q = Integer.parseInt(bufferedReader.readLine().trim());

    //     List<List<Integer>> queries = new ArrayList<>();

    //     IntStream.range(0, q).forEach(i -> {
    //         try {
    //             queries.add(
    //                 Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
    //                     .map(Integer::parseInt)
    //                     .collect(toList())
    //             );
    //         } catch (IOException ex) {
    //             throw new RuntimeException(ex);
    //         }
    //     });

    //     List<Integer> ans = freqQuery(queries);

    //     bufferedWriter.write(
    //         ans.stream()
    //             .map(Object::toString)
    //             .collect(joining("\n"))
    //         + "\n"
    //     );

    //     bufferedReader.close();
    //     bufferedWriter.close();
    // }
}
