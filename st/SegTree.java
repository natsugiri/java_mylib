import java.util.Arrays;
import java.lang.SuppressWarnings;

class SegTree<S> {
    interface Join<S> { S apply(S x, S y); }

    final int n;
    private final Object[] d;

    @SuppressWarnings("unchecked")
    private S getD(int i) { return (S) d[i]; }

    final Join<S> join;

    SegTree(int n, S zero, Join<S> join) {
	this.n = n;
	d = new Object[n + n];
	Arrays.fill(d, zero);
	this.join = join;
    }

    SegTree(S[] a, S zero, Join<S> join) {
        n = a.length;
        d = new Object[n + n];
        for (int i = 0; i < n; i++) { d[i + n] = a[i]; }
        for (int i = n - 1; i >= 1; i--) { d[i] = join.apply(getD(i * 2), getD(i * 2 + 1)); }
        d[0] = zero;
	this.join = join;
    }

    void modify(int i, S v) {
	i += n;
	d[i] = v;
	i >>= 1;
	while (i >= 1) {
	    d[i] = join.apply(getD(i * 2), getD(i * 2 + 1));
	    i >>= 1;
	}
    }

    S get(int i) {
	return getD(i + n);
    }

    S getSum(int l, int r) {
	l += n;
	r += n;
	S retL = getD(0);
	S retR = getD(0);
	while (l < r) {
	    if ((l & 1) == 1) {
		retL = join.apply(retL, getD(l));
		l++;
	    }
	    if ((r & 1) == 1) {
		r--;
		retR = join.apply(getD(r), retR);
	    }
	    l >>= 1; r >>= 1;
	}
	return join.apply(retL, retR);
    }
}
