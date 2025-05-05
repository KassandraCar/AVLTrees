public class AVLTree  <K extends Comparable<K>, V> extends BinarySearchTree<K,V> {

    public AVLTree(){
        super();
    }

    public V insert(K key, V value){
        // TODO
        V oldValue = (root == null) ? null : find(key);
        root = insertHelper(root, key, value);
        if(oldValue == null){
            size++;
        }
        return oldValue;
    }

    private TreeNode<K, V> insertHelper(TreeNode<K, V> node, K key, V value) {
        if(node == null) {
            return new TreeNode<>(key, value);
        }
        int cmp = key.compareTo(node.key);

        if(cmp < 0){
            node.left = insertHelper(node.left, key, value);
        } else if (cmp > 0) {
            node.right = insertHelper(node.right, key, value);
        } else {
            node.value = value;
            return node;
        }
        node.updateHeight();
        return balance(node);
    }

    private TreeNode<K, V> balance(TreeNode<K, V> node) {
        if (node == null) return null;

        int balanceFactor = getBalanceFactor(node);

        if (balanceFactor > 1) {
            if (getBalanceFactor(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        }
        if (balanceFactor < -1) {
            if (getBalanceFactor(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }
        node.updateHeight();
        return node;
    }

    private int getBalanceFactor(TreeNode<K, V> node) {
        if(node == null) return 0;
        int leftHeight = (node.left != null) ? node.left.height : -1;
        int rightHeight = (node.right != null) ? node.right.height : -1;
        return leftHeight - rightHeight;
    }

    private TreeNode<K, V> rotateRight(TreeNode<K, V> y) {
        if(y == null || y.left == null) {
            return y;
        }
        TreeNode<K, V> x = y.left;
        TreeNode<K, V> T2 = x.right;

        x.right = y;
        y.left = T2;

        y.updateHeight();
        x.updateHeight();

        return x;
    }

    private TreeNode<K, V> rotateLeft(TreeNode<K, V> x) {
        if(x == null || x.right == null) {
            return x;
        }
        TreeNode<K, V> y = x.right;
        TreeNode<K, V> T2 = y.left;

        y.left = x;
        x.right = T2;

        x.updateHeight();
        y.updateHeight();

        return y;
    }
}