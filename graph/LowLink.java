import java.util.*;

class LowLink {
    int n;
    Edges[] graph;
    int[] low;
    int[] ord;
    int[] inc;

    @SuppressWarnings("serial")
    private class Edges extends ArrayList<Integer> {}

    LowLink(int n) {
	this.n = n;
	graph = new Edges[n];
	for (int i = 0; i < n; i++) { graph[i] = new Edges(); }
    }

    void addBiEdge(int x, int y) {
	graph[x].add(y);
	graph[y].add(x);
    }

    void build() {
	@SuppressWarnings({"unchecked", "rawtypes"})
	Iterator<Integer>[] iterator = new Iterator[n];
	for (int v = 0; v < n; v++) { iterator[v] = graph[v].iterator(); }
	low = new int[n];
	ord = new int[n];
	Arrays.fill(ord, -1);
	inc = new int[n];
	int[] stack = new int[n + 2];
	stack[0] = -1;
	int len = 0;
	int count = 0;
	for (int root = 0; root < n; root++) {
	    if (ord[root] != -1) { continue; }
	    ord[root] = low[root] = count++;
	    inc[root]--;
	    len = 1;
	    stack[1] = root;
	    while (len >= 1) {
		int v = stack[len];
		int p = stack[len - 1];
		if (iterator[v].hasNext()) {
		    int w = iterator[v].next();
		    if (ord[w] == -1) {
			ord[w] = low[w] = count++;
			len++;
			stack[len] = w;
		    } else if (w != p && low[v] > ord[w]) {
			low[v] = ord[w];
		    }
		} else {
		    len--;
		    if (p != -1) {
			if (low[p] > low[v]) { low[p] = low[v]; }
			if (ord[p] <= low[v]) { inc[p]++; }
		    }
		}
	    }
	}
    }

    boolean isBridge(int x, int y) { 
	return ord[x] < low[y] || ord[y] < low[x];
    }

    boolean isArticulation(int x) { return inc[x] > 0; }

    int articulationValue(int x) { return inc[x]; }
}
