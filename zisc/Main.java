import java.io.*;
import java.util.*;

class Main {
    public static void main(String[] args) {
	new Main().solve();
    }

    void solve() {
	Reader in = new Reader(System.in);
	PrintWriter out = new PrintWriter(System.out);

	out.println("-1");
	out.flush();
    }
}

class Reader {
    BufferedReader br;
    StringTokenizer st;
    Reader(InputStream is) {
	br = new BufferedReader(new InputStreamReader(is));
	st = null;
    }
    String next() {
	while (st == null || !st.hasMoreTokens()) {
	    try {
		st = new StringTokenizer(br.readLine());
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
	return st.nextToken();
    }
    int nextInt() {
	return Integer.parseInt(next());
    }
    long nextLong() {
	return Long.parseLong(next());
    }
}
