import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class SegTreeTest {
    @Test
    public void test() {
	int[] a = new int[]{5, -1, -3, 2, -10, 3, 5, 2, -4, 5};

	SegTree<Long> tree = new SegTree<Long>(
		a.length,
		0L,
		(x, y) -> x + y);
	for (int i = 0; i < 10; i++) {
	    tree.modify(i, (long)a[i]);
	}
	assertEquals(Long.valueOf(4), tree.getSum(0, 10));
    }

    @Test
    public void testSecondMax() {
	int[] a = new int[]{6, -1, -3, 2, -10, 3, 5, 1, -4, 4};
	SegTree<Seg> tree = new SegTree<Seg>(
		a.length,
		new Seg(),
		this::join);
	for (int i = 0; i < 10; i++) {
	    tree.modify(i, new Seg((long)a[i]));
	}
	Seg result = tree.getSum(0, 10);
	assertEquals(6L, result.firstMax);
	assertEquals(5L, result.secondMax);
    }

    class Seg {
	long firstMax;
	long secondMax;
	Seg() {
	    this(Long.MIN_VALUE);
	}
	Seg(long value) {
	    this(value, Long.MIN_VALUE);
	}
	Seg(long firstMax, long secondMax) {
	    this.firstMax = firstMax;
	    this.secondMax = secondMax;
	}
    }

    Seg join(Seg x, Seg y) {
	long f = x.firstMax;
	long s = x.secondMax;
	if (f < y.firstMax) {
	    s = f;
	    f = y.firstMax;
	} else if (s < y.firstMax) {
	    s = y.firstMax;
	}
	if (s < y.secondMax) {
	    s = y.secondMax;
	}
	return new Seg(f, s);
    }
}

