
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private int size;
    private Node<K, V> root;


    public static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;
        private Node<K, V> leftMax;
        private Node<K, V> rightMin;
        private Node<K, V> removenode;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.leftMax = null;
            this.rightMin = null;
            this.removenode = null;
        }
    }

    public BSTMap() {
        root = null;
        size = 0;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        root = put(root, key, value);
        root.leftMax = findLeftMax(root);
        root.rightMin = findRightMin(root);
    }

    private Node<K, V> put(Node<K, V> x, K key, V value) {
        if (x == null) {
            size++;
            return new Node<>(key, value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        }
        else if (cmp > 0) {
            x.right = put(x.right, key, value);
        }
        else {
            x.value = value;
        }
        return x;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        return get(root, key);
    }

    private V get(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        }
        else if (cmp > 0) {
            return get(x.right, key);
        }
        else {
            return x.value;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        return null != find(root, key);
    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new NullPointerException("key is null");
        }
        root.removenode = find(root, key);
        V value = get(key);
        if (root.removenode == null) {
            return null;
        }
        else if (root.removenode.left == null && root.removenode.right == null) {
            root.removenode = null;
        }
        else if(root.removenode.left == null) {
            root.removenode = root.removenode.right;
        }
        else if(root.removenode.right == null) {
            root.removenode = root.removenode.left;
        }
        else{
            root.removenode.key = root.removenode.leftMax.key;
        }
        return value;
    }

    private Node<K,V> find(Node<K, V> x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return find(x.left, key);
        }
        else if (cmp > 0) {
            return find(x.right, key);
        }
        else {
            return x;
        }
    }

    private Node<K, V> findLeftMax(Node<K, V> x) {
        if (x == null) {
            return null;
        }
        if (x.right == null) {
            return x;
        }
        return findLeftMax(x.right);
    }

    private Node<K, V> findRightMin(Node<K, V> x) {
        if (x == null) {
            return null;
        }
        if (x.left == null) {
            return x;
        }
        return findRightMin(x.left);
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
