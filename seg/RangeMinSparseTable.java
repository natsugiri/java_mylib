import java.util.*;

class RangeMinSparseTable {
    final long[][] table;

    RangeMinSparseTable(long[] a) {
	int level = 31 - Integer.numberOfLeadingZeros(a.length);
	table = new long[level + 1][a.length];
	for (int i = 0; i < a.length; i++) { table[0][i] = a[i]; }
	for (int t = 0; t < level; t++) {
	    for (int i = 0; i + (2 << t) <= a.length; i++) {
		table[t + 1][i] = Long.min(table[t][i], table[t][i + (1 << t)]);
	    }
	}
    }

    RangeMinSparseTable(int n, Iterator<Long> iterator) {
	int level = 31 - Integer.numberOfLeadingZeros(n);
	table = new long[level + 1][n];
	for (int i = 0; i < n; i++) { table[0][i] = iterator.next(); }
	for (int t = 0; t < level; t++) {
	    for (int i = 0; i + (2 << t) <= n; i++) {
		table[t + 1][i] = Long.min(table[t][i], table[t][i + (1 << t)]);
	    }
	}
    }

    RangeMinSparseTable(List<Long> list) { this(list.size(), list.iterator()); }

    long get(int i) { return table[0][i]; }

    long getMin(int l, int r) {
	if (l >= r) { return Long.MAX_VALUE; }
	int t = 31 - Integer.numberOfLeadingZeros(r - l);
	return Long.min(table[t][l], table[t][r - (1 << t)]);
    }
}
