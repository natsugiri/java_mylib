import java.util.*;

class Dijkstra {
    int n;

    private class Edge {
	int to;
	long cost;
	Edge(int to, long cost) {
	    this.to = to;
	    this.cost = cost;
	}
    }

    private class Edges extends ArrayList<Edge> {}

    Edges[] graph;

    Dijkstra(int n) {
	this.n = n;
	graph = new Edges[n];
	for (int i = 0; i < n; i++) {
	    graph[i] = new Edges();
	}
    }

    void addEdge(int from, int to, long cost) {
	graph[from].add(new Edge(to, cost));
    }

    long[] solve(int start) {
	long[] dist = new long[n];
	Arrays.fill(dist, Long.MAX_VALUE);
	PriorityQueue<Edge> pq = new PriorityQueue<>(
		(Edge x, Edge y) -> Long.compare(x.cost, y.cost));
	dist[start] = 0;
	pq.add(new Edge(0, 0));
	while (!pq.isEmpty()) {
	    Edge p = pq.poll();
	    if (dist[p.to] != p.cost) {
		continue;
	    }
	    for (Edge e : graph[p.to]) {
		if (dist[e.to] > p.cost + e.cost) {
		    dist[e.to] = p.cost + e.cost;
		    pq.add(new Edge(e.to, dist[e.to]));
		}
	    }
	}
	return dist;
    }
}
