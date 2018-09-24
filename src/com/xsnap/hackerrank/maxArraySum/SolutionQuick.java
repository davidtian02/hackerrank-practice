package com.xsnap.hackerrank.maxArraySum;

public class SolutionQuick {
    static int maxSubsetSum(int[] arr) {
        if (arr.length == 0) {
            return 0;
        }

        if (arr.length == 1) {
            return arr[0];
        } else if (arr.length == 2) {
            return arr[0] > arr[1] ? arr[0] : arr[1];
        } else {
            return maxSubsetSumR(arr);
        }
    }

    static int maxSubsetSumR(int[] arr) {
        int cache[] = new int[arr.length];
        cache[0] = arr[0];
        cache[1] = arr[1];
        cache[1] = cache[0] > cache[1] ? cache[0] : cache[1];
        int val1; int val2;
        for (int i=2; i<arr.length; i++) {
            val1 = Math.max(arr[i], cache[i-2] + arr[i]);
            val2 = Math.max(cache[i-1], val1);
            cache[i] = Math.max(val1, val2);
        }

        return cache[cache.length - 1];
    }
}
