import java.util.*;
import java.io.*;
import static org.junit.Assert.*;
import org.junit.Test;

public class ModIntTest extends ModInt.ModInt1000000007 {
    @Test
    public void test() {
	assertEquals(5, add(2, 3));
	assertEquals(1000000006, sub(2, 3));
	assertEquals(48, mul(6, 8));
	assertEquals(2, div(12, 6));
	assertEquals(1000000005, neg(2));
	assertEquals(81, pow(3, 4));

	assertEquals(1, mul(100, inv(100)));

	assertEquals(1, mul(1000, inv(1000)));
	assertEquals(6, comb(4, 2));
    }
}
