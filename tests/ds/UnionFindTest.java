import static org.junit.Assert.*;
import org.junit.Test;

public class UnionFindTest {
    @Test
    public void test() {
	UnionFind u = new UnionFind(3);
	u.link(1, 2);
	assertTrue(u.same(0, 0));
	assertTrue(u.same(1, 2));
	assertFalse(u.same(0, 1));
	assertEquals(0, u.root(0));
	assertEquals(1, u.countNode(0));
	assertEquals(2, u.countTree());
	u.linkParentChild(0, 1);
    }
}
