import static org.junit.Assert.*;
import org.junit.Test;

public class DijkstraTest {
    @Test
    public void test() {
	Dijkstra d = new Dijkstra(3);
	d.addEdge(0, 1, 3);
	d.addEdge(1, 2, 4);
	d.addEdge(0, 2, 9);
	long[] dist = d.solve(0);
	assertEquals(7L, dist[2]);
    }
}
