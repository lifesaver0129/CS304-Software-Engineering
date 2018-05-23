import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ArrayListTest {
    List<Integer> testArray;

    @Before
    public void setUp() {
        testArray = new ArrayList<Integer>(Arrays.asList(3, 1, 4, 1, 5));
    }


    @After
    public void tearDown() {
    }

    @Test
    public void testAdd() {
        testArray.add(9);
        List<Integer> expected =
                new ArrayList<Integer>(Arrays.asList(3, 1, 4, 1, 5, 9));
        assertEquals(testArray, expected);
    }


    @Test
    public void testRemoveObject() {
        testArray.remove(new Integer(5));
        List<Integer> expected =
                new ArrayList<Integer>(Arrays.asList(3, 1, 4, 1));
        assertEquals(testArray, expected);
    }


    @Test
    public void testIndexOf() {
        assertEquals(testArray.indexOf(4), 2);
    }

    @Test
    public void testClear(){
        testArray.clear();
        assertEquals(0,testArray.size());
    }

    @Test
    public void testContains() {
        assertEquals(true,testArray.contains(3));
    }

    @Test
    public void testNotContains() {
        assertEquals(false,testArray.contains(6));
    }

    @Test
    public void testGet() {
        assertEquals((Integer) 3,testArray.get(0));
    }
}
