import static org.junit.Assert.*;
import org.junit.Test;

public class LowLinkTest {
    @Test
    public void test() {
	LowLink l = new LowLink(4);
	l.addBiEdge(0, 1);
	l.addBiEdge(1, 2);
	l.addBiEdge(2, 3);
	l.addBiEdge(1, 3);
	l.build();
	assertTrue(l.isBridge(0, 1));
	assertFalse(l.isBridge(1, 2));
	assertFalse(l.isBridge(2, 3));
	assertFalse(l.isBridge(3, 1));
    }
}

