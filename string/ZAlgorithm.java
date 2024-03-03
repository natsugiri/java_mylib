class ZAlgorithm {
    int[] z;
    ZAlgorithm(String s) {
	z = new int[s.length()];
	int l = 0, r = 0;
	for (int i = 1; i < s.length(); i++) {
	    if (z[i - l] < r - i) {
		z[i] = z[i - l];
	    } else {
		if (r < i) { r = i; }
		while (r < s.length() && s.charAt(r - i) == s.charAt(r)) { r++; }
		z[i] = r - i;
		l = i;
	    }
	}
    }
}
