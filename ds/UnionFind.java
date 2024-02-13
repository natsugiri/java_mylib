import java.util.Arrays;

class UnionFind {
    int[] p;
    int count;

    UnionFind(int n) {
	p = new int[n];
	count = n;
	Arrays.fill(p, -1);
    }

    int root(int x) {
	if (p[x] < 0) { return x; }
	p[x] = root(p[x]);
	return p[x];
    }

    boolean same(int x, int y) {
	return root(x) == root(y);
    }

    void link(int x, int y) {
	x = root(x);
	y = root(y);
	if (x == y) { return; }
	if (p[x] < p[y]) {
	    p[x] += p[y];
	    p[y] = x;
	} else {
	    p[y] += p[x];
	    p[x] = y;
	}
	count--;
    }

    void linkParentChild(int parent, int child) {
	assert parent == root(parent);
	parent = root(parent);
	child = root(child);
	if (parent == child) { return; }
	p[parent] += p[child];
	p[child] = parent;
	count--;
    }

    int countNode(int x) {
	x = root(x);
	return -p[x];
    }

    int countTree() {
	return count;
    }
}
