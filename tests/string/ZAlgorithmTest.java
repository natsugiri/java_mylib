import static org.junit.Assert.*;
import org.junit.Test;
import java.util.*;

public class ZAlgorithmTest {
    @Test
    public void test() {
	int[] z = new ZAlgorithm("abcabca").z;
	assertEquals(0, z[0]);
	assertEquals(0, z[1]);
	assertEquals(0, z[2]);
	assertEquals(4, z[3]);
    }
}
