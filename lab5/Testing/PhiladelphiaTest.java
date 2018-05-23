import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PhiladelphiaTest {

    @Test
    public void testIsItSunny() {
        assertTrue(Philadelphia.isItSunny());
    }

    @Test
    public void testIsItNotSunny() {
        assertFalse(Philadelphia.isItSunny());
    }


    @Test
    public void testIsItReallyNotSunny() {
        assertFalse(!Philadelphia.isItSunny());
    }

    @Test(expected = AssertionError.class)
    public void testIsItNotSunnyButOK() {
        assertFalse(Philadelphia.isItSunny());
    }
}