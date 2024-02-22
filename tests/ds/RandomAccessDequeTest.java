import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class RandomAccessDequeTest {
    @Test
    public void test() {
	RandomAccessDeque<Long> dq = new RandomAccessDeque<>();
	assertTrue(dq.isEmpty());
	dq.addLast(1L);
	dq.addLast(2L);
	dq.addFirst(0L);
	assertEquals(3, dq.size());
	assertFalse(dq.isEmpty());
	assertEquals((Long) 0L, dq.get(0));
	assertEquals((Long) 1L, dq.get(1));
	assertEquals((Long) 2L, dq.get(2));
	assertEquals((Long) 0L, dq.getFirst());
	assertEquals((Long) 2L, dq.getLast());
	assertEquals("[0, 1, 2]", dq.toString());

	dq.removeFirst();
	assertEquals((Long) 1L, dq.getFirst());
	dq.removeLast();
	assertEquals((Long) 1L, dq.getLast());
	dq.set(0, 10L);
	assertEquals((Long) 10L, dq.getLast());

	ListIterator<Long> iterator = dq.listIterator();
	assertEquals((Long) 10L, iterator.next());
	iterator.set(20L);
	assertEquals((Long) 20L, dq.getLast());
	dq.clear();
    }
}


