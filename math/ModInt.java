abstract class ModInt {
    abstract public long mod();

    static class ModInt998244353 extends ModInt { public long mod() { return 998244353; } }
    static class ModInt1000000007 extends ModInt { public long mod() { return 1000000007; } }

    long normal(long x) {
	x %= mod();
	return (x < 0 ? x + mod() : x);
    }

    long add(long x, long y) {
	return (x + y) % mod();
    }

    long sub(long x, long y) {
	return (x - y + mod()) % mod();
    }

    long mul(long x, long y) {
	return x * y % mod();
    }

    long neg(long x) {
	return x == 0 ? 0 : mod() - x;
    }

    long div(long x, long y) {
	return x * inv(y) % mod();
    }

    long inv(long x) {
	if (fact != null && 1 <= x && x < fact.length) {
	    return mul(factInv[(int)x], fact[(int)x - 1]);
	}
	return pow(x, mod() - 2);
    }

    long pow(long x, long y) {
	long r = ((y & 1) == 1 ? x : 1);
	while (y >= 2) {
	    y >>= 1;
	    x = mul(x, x);
	    if ((y & 1) == 1) { r = mul(r, x); }
	}
	return r;
    }

    private long[] fact = null;
    private long[] factInv = null;

    void build(int size) {
	if (fact != null && fact.length >= size) { return; }
	fact = new long[size];
	factInv = new long[size];
	fact[0] = 1;
	for (int i = 1; i < size; i++) {
	    fact[i] = mul(fact[i - 1], i);
	}
	factInv[size - 1] = pow(fact[size - 1], mod() - 2);
	for (int i = size - 2; i >= 0; i--) {
	    factInv[i] = mul(factInv[i + 1], i + 1);
	}
    }

    private void autoBuild(int n) {
	if (fact == null) {
	    build(Integer.max(200001, n + 1));
	} else if (fact.length <= n) {
	    build(Integer.max(fact.length * 2, n + 1));
	}
    }

    long fact(int n) {
	autoBuild(n);
	return fact[n];
    }

    long factInv(int n) {
	autoBuild(n);
	return factInv[n];
    }

    long comb(int n, int k) {
	autoBuild(n);
	return mul(mul(fact[n], factInv[k]), factInv[n - k]);
    }
}
