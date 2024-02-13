import java.util.*;

class ShortestPathFasterAlgorithm {
    private class Edge {
	int to;
	long cost;

	Edge(int to, long cost) {
	    this.to = to;
	    this.cost = cost;
	}
    }

    int n;
    ArrayList<Edge>[] graph;

    ShortestPathFasterAlgorithm(int n) {
	this.n = n;
	graph = new ArrayList[n];
	for (int i = 0; i < n; i++) {
	    graph[i] = new ArrayList<>();
	}
    }

    void addEdge(int from, int to, long cost) {
	graph[from].add(new Edge(to, cost));
    }

    long[] solve(int start) {
	long[] dist = new long[n];
	Arrays.fill(dist, Long.MAX_VALUE);
	dist[start] = 0;
	ArrayList<Integer> stk0 = new ArrayList<>();
	ArrayList<Integer> stk1 = new ArrayList<>();
	boolean[] in = new boolean[n];
	int[] counter = new int[n];
	stk0.add(start);
	in[start] = true;
	while (!stk0.isEmpty()) {
	    while (!stk0.isEmpty()) {
		int v = stk0.get(stk0.size() - 1);
		stk0.remove(stk0.size() - 1);
		in[v] = false;
		for (Edge e : graph[v]) {
		    if (dist[e.to] > dist[v] + e.cost) {
			dist[e.to] = dist[v] + e.cost;
			if (!in[e.to]) {
			    if (counter[e.to] >= n) {
				return null;
			    }
			    counter[e.to]++;
			    stk1.add(e.to);
			    in[e.to] = true;
			}
		    }
		}
	    }
	    ArrayList<Integer> tmp = stk0;
	    stk0 = stk1;
	    stk1 = tmp;
	    Collections.reverse(stk0);
	}
	return dist;
    }
}
