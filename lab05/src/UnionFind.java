import java.util.ArrayList;
import java.util.List;

public class UnionFind {
    // TODO: Instance variables
    private ArrayList<Integer> data;

    /* Creates a UnionFind data structure holding N items. Initially, all
       items are in disjoint sets. */
    public UnionFind(int N) {
        // TODO: YOUR CODE HERE
        data = new ArrayList<Integer>(N);
        for (int i = 0; i < N; i++) {
            data.add(-1);
        }
    }

    /* Returns the size of the set V belongs to. */
    public int sizeOf(int v) {
        // TODO: YOUR CODE HERE
        return Math.abs(data.get(find(v)));
    }

    /* Returns the parent of V. If V is the root of a tree, returns the
       negative size of the tree for which V is the root. */
    public int parent(int v) {
        // TODO: YOUR CODE HERE
        return data.get(v);
    }

    /* Returns true if nodes/vertices V1 and V2 are connected. */
    public boolean connected(int v1, int v2) {
        // TODO: YOUR CODE HERE
        return find(v1) == find(v2);
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. If invalid items are passed into this
       function, throw an IllegalArgumentException. */
    public int find(int v) {
        // TODO: YOUR CODE HERE
        if (v >= data.size()) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        if (data.get(v) <= -1) {
            return v;
        }
        int root = find(data.get(v));
        data.set(v, root);
        return root;
    }

    /* Connects two items V1 and V2 together by connecting their respective
       sets. V1 and V2 can be any element, and a union-by-size heuristic is
       used. If the sizes of the sets are equal, tie break by connecting V1's
       root to V2's root. Union-ing an item with itself or items that are
       already connected should not change the structure. */
    public void union(int v1, int v2) {
        // TODO: YOUR CODE HERE
        if (v1 >= data.size() || v2 >= data.size()) {
            throw new IllegalArgumentException("Some comment to describe the reason for throwing.");
        }
        if (Math.abs(data.get(find(v1))) > Math.abs(data.get(find(v2)))) {
            data.set(find(v1), data.get(find(v2)) + data.get(find(v1)));
            data.set(find(v2), find(v1));
        }
        else if ((Math.abs(data.get(find(v1))) <= Math.abs(data.get(find(v2))) && v1 != v2)) {
            data.set(find(v2), data.get(find(v2)) + data.get(find(v1)));
            data.set(find(v1), find(v2));
        }
    }
}
