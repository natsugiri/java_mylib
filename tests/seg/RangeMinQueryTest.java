import static org.junit.Assert.*;
import org.junit.Test;

public class RangeMinQueryTest {
    @Test
    public void test() {
	RangeMinQuery rmq = new RangeMinQuery(new long[] {
	    10, 15, 20, 13, 10, 10, 40, 15, 10, 20
	});
	assertEquals(13, rmq.getMin(2, 4));
	assertEquals(10, rmq.getMin(4, 6));
	assertEquals(4, rmq.findFirstLessThan(2, 13));
	assertEquals(-1, rmq.findFirstLessThan(3, 1));
	assertEquals(5, rmq.findLastLessEqual(7, 39));
	rmq.modify(5, 111);
	assertEquals(4, rmq.findLastLessEqual(7, 39));
    }
}

