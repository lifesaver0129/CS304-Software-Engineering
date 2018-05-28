package triangle;

import junit.framework.TestCase;
import static triangle.Triangle.Type.*;

public class TestSuite extends TestCase {

   public void test1() {
        assertEquals (triangle.Triangle.classify(0,1301,1), INVALID);
   }
   public void test2() {
        assertEquals (triangle.Triangle.classify(1108,1,1), INVALID);
   }
   public void test3() {
        assertEquals (triangle.Triangle.classify(1,0,-665), INVALID);
   }
   public void test4() {
        assertEquals (triangle.Triangle.classify(1,1,0), INVALID);
   }
   public void test5() {
        assertEquals (triangle.Triangle.classify(582,582,582), EQUILATERAL);
   }
   public void test6() {
        assertEquals (triangle.Triangle.classify(1,1088,15), INVALID);
   }
   public void test7() {
        assertEquals (triangle.Triangle.classify(1,2,450), INVALID);
   }
   public void test8() {
        assertEquals (triangle.Triangle.classify(1663,1088,823), SCALENE);
   }
   public void test9() {
        assertEquals (triangle.Triangle.classify(1187,1146,1), INVALID);
   }
   public void test10() {
        assertEquals (triangle.Triangle.classify(1640,1640,1956), ISOSCELES);
   }
   public void test11() {
        assertEquals (triangle.Triangle.classify(784,784,1956), INVALID);
   }
   public void test12() {
        assertEquals (triangle.Triangle.classify(1,450,1), INVALID);
   }
   public void test13() {
        assertEquals (triangle.Triangle.classify(1146,1,1146), ISOSCELES);
   }
   public void test14() {
        assertEquals (triangle.Triangle.classify(1640,1956,1956), ISOSCELES);
   }
   public void test15() {
        assertEquals (triangle.Triangle.classify(-1,1,1), INVALID);
   }
   public void test16() {
        assertEquals (triangle.Triangle.classify(1,-1,1), INVALID);
   }
   public void test17() {
        assertEquals (triangle.Triangle.classify(1,2,3), INVALID);
   }
   public void test18() {
        assertEquals (triangle.Triangle.classify(2,3,1), INVALID);
   }
   public void test19() {
        assertEquals (triangle.Triangle.classify(3,1,2), INVALID);
   }
   public void test20() {
        assertEquals (triangle.Triangle.classify(1,1,2), INVALID);
   }
   public void test21() {
        assertEquals (triangle.Triangle.classify(1,2,1), INVALID);
   }
   public void test22() {
        assertEquals (triangle.Triangle.classify(2,1,1), INVALID);
   }
   public void test23() {
        assertEquals (triangle.Triangle.classify(1,1,1), EQUILATERAL);
   }
   public void test24() {
        assertEquals (triangle.Triangle.classify(0,1,1), INVALID);
   }
   public void test25() {
        assertEquals (triangle.Triangle.classify(1,0,1), INVALID);
   }
   public void test26() {
        assertEquals (triangle.Triangle.classify(1,2,-1), INVALID);
   }
   public void test27() {
        assertEquals (triangle.Triangle.classify(1,1,-1), INVALID);
   }
   public void test28() {
        assertEquals (triangle.Triangle.classify(0,0,0), INVALID);
   }
   public void test29() {
        assertEquals (triangle.Triangle.classify(3,2,5), INVALID);
   }
   public void test30() {
        assertEquals (triangle.Triangle.classify(5,9,2), INVALID);
   }
   public void test31() {
        assertEquals (triangle.Triangle.classify(7,4,3), INVALID);
   }
   public void test32() {
        assertEquals (triangle.Triangle.classify(3,8,3), INVALID);
   }
   public void test33() {
        assertEquals (triangle.Triangle.classify(7,3,3), INVALID);
   }
}
