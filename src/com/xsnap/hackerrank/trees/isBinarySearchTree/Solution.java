package com.xsnap.hackerrank.trees.isBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /* Hidden stub code will pass a root argument to the function below. Complete the function to solve the challenge. Hint: you may want to write one or more helper functions.

The Node class is defined as follows:
    class Node {
        int data;
        Node left;
        Node right;
     }
*/

    class Node {
        int data;
        Node left;
        Node right;
    }

    boolean checkBST(Node root) {
        List<Node> list = new ArrayList<>();
        helper(root, list);

        if (list.size() < 2) {
            return true;
        }

        for (int i=1; i<list.size(); i++) {
            if (list.get(i).data <= list.get(i-1).data) {
                return false;
            }
        }

        return true;
    }

    void helper(Node root, List<Node> list) {
        if (root == null) {
            return;
        }

        helper(root.left, list);
        list.add(root);
        helper(root.right, list);
    }
}
