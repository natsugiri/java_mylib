import java.util.Arrays;

class RangeMinQuery {
    final int n;
    final long[] d;

    RangeMinQuery(int n) {
	this.n = n;
	d = new long[n + n];
	Arrays.fill(d, Long.MAX_VALUE);
    }

    RangeMinQuery(long[] a) {
	n = a.length;
	d = new long[n + n];
	for (int i = 0; i < n; i++) { d[i + n] = a[i]; }
	for (int i = n - 1; i >= 1; i--) { d[i] = Long.min(d[i * 2], d[i * 2 + 1]); }
    }

    void modify(int i, long v) {
	i += n;
	d[i] = v;
	i >>= 1;
	while (i >= 1) {
	    d[i] = Long.min(d[i * 2], d[i * 2 + 1]);
	    i >>= 1;
	}
    }

    long get(int i) {
	return d[i + n];
    }

    long getMin(int l, int r) {
	l += n;
	r += n;
	long ret = Long.MAX_VALUE;
	while (l < r) {
	    if ((l & 1) == 1) { ret = Long.min(ret, d[l]); l++; }
	    if ((r & 1) == 1) { r--; ret = Long.min(ret, d[r]); }
	    l >>= 1; r >>= 1;
	}
	return ret;
    }

    private interface Property { boolean apply(long v); };

    private int findFirstTrue(int l, Property prop) {
	l += n;
	int r = n + n;
	int k = -1;
	while (l < r) {
	    if ((l & 1) == 1) {
		if (prop.apply(d[l])) { k = l; break; }
		l++;
	    }
	    if ((r & 1) == 1) {
		r--;
		if (prop.apply(d[r])) { k = r; }
	    }
	    l >>= 1; r >>= 1;
	}
	if (k == -1) { return -1; }
	while (k < n) {
	    if (prop.apply(d[k * 2])) { k = k * 2; }
	    else { k = k * 2 + 1; }
	}
	return k - n;
    }

    private int findLastTrue(int r, Property prop) {
	int l = n;
	r += n;
	int k = -1;
	while (l < r) {
	    if ((l & 1) == 1) {
		if (prop.apply(d[l])) { k = l; }
		l++;
	    }
	    if ((r & 1) == 1) {
		r--;
		if (prop.apply(d[r])) { k = r; break; }
	    }
	    l >>= 1; r >>= 1;
	}
	if (k == -1) { return -1; }
	while (k < n) {
	    if (prop.apply(d[k * 2 + 1])) { k = k * 2 + 1; }
	    else { k = k * 2; }
	}
	return k - n;
    }

    int findFirstLessThan(int l, long v) {
	return findFirstTrue(l, e -> e < v);
    }

    int findFirstLessEqual(int l, long v) {
	return findFirstTrue(l, e -> e <= v);
    }

    int findLastLessThan(int r, long v) {
	return findLastTrue(r, e -> e < v);
    }

    int findLastLessEqual(int r, long v) {
	return findLastTrue(r, e -> e <= v);
    }
}
