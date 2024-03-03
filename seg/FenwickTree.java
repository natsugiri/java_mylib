class FenwickTree {
    long[] d;

    FenwickTree(int n) {
	d = new long[n];
    }

    void add(int i, long v) {
	while (i < d.length) {
	    d[i] += v;
	    i |= i + 1;
	}
    }

    long getPrefix(int r) {
	long ret = 0;
	while (r > 0) {
	    ret += d[r-1];
	    r &= r - 1;
	}
	return ret;
    }

    long getSum(int l, int r) {
	return getPrefix(r) - getPrefix(l);
    }
}
