package com.random.grind75;

/**
 * use AtomicBoolean instead of Boolean in method calls because Boolean is immutable
 */
public class HeightBalancedBinaryTree {

    public boolean isBalanced(TreeNode root) {

        if(root == null || (root.left == null && root.right == null))
            return true;

        Boolean isBalanced = true;

        traverse(root, isBalanced);

        System.out.println(isBalanced);

        return isBalanced;

    }

    int traverse(TreeNode root, Boolean isBalanced) {

        System.out.print(isBalanced + "         ");

        int h1 = 0;
        int h2 = 0;

        int h = 0;

        if(root == null || !isBalanced)
            return 0;

        h1 = traverse(root.left, isBalanced);
        h2 = traverse(root.right, isBalanced);


        if(Math.abs(h1-h2) > 1)
            isBalanced = false;

        System.out.println(isBalanced);

        return Math.max(h1,h2) + 1;
    }

    public static void main(String[] args) {

        TreeNode root = new TreeNode(1, new TreeNode(2, new TreeNode(3), new TreeNode(3, new TreeNode(4), new TreeNode(4))), new TreeNode(2));

        new HeightBalancedBinaryTree().isBalanced(root);
    }
}
