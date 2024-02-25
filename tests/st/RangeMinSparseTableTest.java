import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class RangeMinSparseTableTest {
    @Test
    public void test() {
	List<Long> list = Arrays.asList(new Long[] {
	    10L, 15L, 20L, 13L, 10L, 10L, 40L, 15L, 10L, 20L
	});
	RangeMinSparseTable rmq = new RangeMinSparseTable(list);

	assertEquals(13, rmq.getMin(2, 4));
	assertEquals(10, rmq.getMin(4, 6));

	rmq = new RangeMinSparseTable(new long[] {
	    0, 1, 2, 3,
	});
	assertEquals(0, rmq.getMin(0, 4));
    }
}
