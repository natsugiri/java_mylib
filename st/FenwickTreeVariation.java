class FenwickTreeVariation {
    FenwickTree f;
    FenwickTreeVariation(int n) { f = new FenwickTree(n); }

    void addSingle(int i, long v) {
	f.add(i, v);
	f.add(i + 1, -v);
    }

    void addRange(int l, int r, long v) {
	f.add(l, v);
	f.add(r, -v);
    }

    long get(int i) {
	return f.getPrefix(i+1);
    }
}
