import static org.junit.Assert.*;
import org.junit.Test;

public class GcdTest {
    @Test
    public void test() {
	assertEquals(6, Gcd.gcd(12, 30));
	assertEquals(60, Gcd.lcm(12, 30));
	Gcd.ExtGcdResult res = Gcd.extGcd(12, 30);
	assertEquals(6, res.g);
	assertEquals(6, 12 * res.a + 30 * res.b);
    }
}
