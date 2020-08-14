/*
First build BST by given array. Second find LCA of node1 and node2. Finally get distances from LCA to node1 and node2.
Time complexity: O(n * h), where n is the number of nodes and h is the height of the tree.
*/

public int bstDistance(int num, List<Integer> values, int node1, int node2) {
    if (num < 2) {
      return -1;
    }
    TreeNode root = buildBST(values.toArray(), node1, node2);
    if (root == null) return -1;
    TreeNode lca = lca(root, node1, node2);
    return getDistance(lca, node1) + getDistance(lca, node2);
}

private int getDistance(TreeNode src, int dest) {
    if (src.val == dest) return 0;
    TreeNode node = src.left;
    if (src.val < dest) {
        node = src.right;
    }
    return 1 + getDistance(node, dest);
}

private TreeNode lca(TreeNode root, int node1, int node2) {
    while (true) {
        if (root.val > node1 && root.val > node2) {
            root = root.left;
        } else if (root.val < node1 && root.val < node2) {
            root = root.right;
        } else {
            return root;
        }
    }
}

private TreeNode buildBST(int[] nums, int node1, int node2) {
    TreeNode root = null;
    boolean found1 = false;
    boolean found2 = false;
    for (int val : nums) {
        if (val == node1) found1 = true;
        if (val == node2) found2 = true;
        
        TreeNode node = new TreeNode(val);
        if (root == null) {
            root = node;
            continue;
        }
        addToBST(root, node);
    }
    if (!found1 || !found2) return null;
    return root;
}

private void addToBST(TreeNode root, TreeNode node) {
    for (TreeNode curr = root; true; ) {
        if (curr.val > node.val) {
            if (curr.left == null) {
                curr.left = node;
                break;
            } else {
                curr = curr.left;
            }
        } else {
            if (curr.right == null) {
                curr.right = node;
                break;
            } else {
                curr = curr.right;
            }
        }
    }
}
