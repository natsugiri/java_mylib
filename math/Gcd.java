class Gcd {
    static long gcd(long x, long y) {
	if (x < 0) { x = -x; }
	if (y < 0) { y = -y; }
	while (true) {
	    if (x == 0) { return y; }
	    y %= x;
	    if (y == 0) { return x; }
	    x %= y;
	}
    }

    static long lcm(long x, long y) {
	return x / gcd(x, y) * y;
    }

    static class ExtGcdResult {
	// ax + by == g;
	long g;
	long a;
	long b;
	ExtGcdResult(long g, long a, long b) {
	    this.g = g;
	    this.a = a;
	    this.b = b;
	}
    }

    static ExtGcdResult extGcd(long x, long y) {
	// Find a, b: (x, 1, 0)*a + (y, 0, 1)*b == (g, a, b);
	boolean xNeg = (x < 0);
	boolean yNeg = (y < 0);
	if (x < 0) { x = -x; }
	if (y < 0) { y = -y; }
	long c = 1;
	long d = 0;
	long e = 0;
	long f = 1;
	while (true) {
	    if (x == 0) { return new ExtGcdResult(y, xNeg? -e: e, yNeg? -f: f); }
	    long q = y / x;
	    y -= q * x;
	    e -= q * c;
	    f -= q * d;
	    if (y == 0) { return new ExtGcdResult(x, xNeg? -c: c, yNeg? -d: d); }
	    q = x / y;
	    x -= q * y;
	    c -= q * e;
	    d -= q * f;
	}
    }
}
