package tarantula;

import junit.framework.TestCase;

import static tarantula.Triangle.Type.*;

public class TestSuite extends TestCase {

    public void test1() {
        assertEquals(tarantula.Triangle.classify(0, 1301, 1), INVALID);
    }

    public void test2() {
        assertEquals(tarantula.Triangle.classify(1108, 1, 1), INVALID);
    }

    public void test3() {
        assertEquals(tarantula.Triangle.classify(1, 0, -665), INVALID);
    }

    public void test4() {
        assertEquals(tarantula.Triangle.classify(1, 1, 0), INVALID);
    }

    public void test5() {
        assertEquals(tarantula.Triangle.classify(582, 582, 582), EQUILATERAL);
    }

    public void test6() {
        assertEquals(tarantula.Triangle.classify(1, 1088, 15), INVALID);
    }

    public void test7() {
        assertEquals(tarantula.Triangle.classify(1, 2, 450), INVALID);
    }

    public void test8() {
        assertEquals(tarantula.Triangle.classify(1663, 1088, 823), SCALENE);
    }

    public void test9() {
        assertEquals(tarantula.Triangle.classify(1187, 1146, 1), INVALID);
    }

    public void test10() {
        assertEquals(tarantula.Triangle.classify(1640, 1640, 1956), ISOSCELES);
    }

    public void test11() {
        assertEquals(tarantula.Triangle.classify(784, 784, 1956), INVALID);
    }

    public void test12() {
        assertEquals(tarantula.Triangle.classify(1, 450, 1), INVALID);
    }

    public void test13() {
        assertEquals(tarantula.Triangle.classify(1146, 1, 1146), ISOSCELES);
    }

    public void test14() {
        assertEquals(tarantula.Triangle.classify(1640, 1956, 1956), ISOSCELES);
    }

    public void test15() {
        assertEquals(tarantula.Triangle.classify(-1, 1, 1), INVALID);
    }

    public void test16() {
        assertEquals(tarantula.Triangle.classify(1, -1, 1), INVALID);
    }

    public void test17() {
        assertEquals(tarantula.Triangle.classify(1, 2, 3), INVALID);
    }

    public void test18() {
        assertEquals(tarantula.Triangle.classify(2, 3, 1), INVALID);
    }

    public void test19() {
        assertEquals(tarantula.Triangle.classify(3, 1, 2), INVALID);
    }

    public void test20() {
        assertEquals(tarantula.Triangle.classify(1, 1, 2), INVALID);
    }

    public void test21() {
        assertEquals(tarantula.Triangle.classify(1, 2, 1), INVALID);
    }

    public void test22() {
        assertEquals(tarantula.Triangle.classify(2, 1, 1), INVALID);
    }

    public void test23() {
        assertEquals(tarantula.Triangle.classify(1, 1, 1), EQUILATERAL);
    }

    public void test24() {
        assertEquals(tarantula.Triangle.classify(0, 1, 1), INVALID);
    }

    public void test25() {
        assertEquals(tarantula.Triangle.classify(1, 0, 1), INVALID);
    }

    public void test26() {
        assertEquals(tarantula.Triangle.classify(1, 2, -1), INVALID);
    }

    public void test27() {
        assertEquals(tarantula.Triangle.classify(1, 1, -1), INVALID);
    }

    public void test28() {
        assertEquals(tarantula.Triangle.classify(0, 0, 0), INVALID);
    }

    public void test29() {
        assertEquals(tarantula.Triangle.classify(3, 2, 5), INVALID);
    }

    public void test30() {
        assertEquals(tarantula.Triangle.classify(5, 9, 2), INVALID);
    }

    public void test31() {
        assertEquals(tarantula.Triangle.classify(7, 4, 3), INVALID);
    }

    public void test32() {
        assertEquals(tarantula.Triangle.classify(3, 8, 3), INVALID);
    }

    public void test33() {
        assertEquals(tarantula.Triangle.classify(7, 3, 3), INVALID);
    }
}
