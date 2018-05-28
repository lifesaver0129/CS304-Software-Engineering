package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.AbstractMatcherTest.*;
import static org.spideruci.hamcrest.primitive.IsFloatArrayContaining.hasFloat;
import static org.spideruci.hamcrest.primitive.IsFloatArrayContaining.hasFloats;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

public class IsFloatArrayContainingTest {

  @Test
  public void copesWithNullsAndUnknownTypes() {
    Matcher<?> matcher = hasFloat(equalTo((Float)null));

    assertNullSafe(matcher);
    assertUnknownTypeSafe(matcher);
  }

  @Test
  public void matchesAnArrayThatContainsAnElementForTheGivenMatcher() {
    final Matcher<float[]> itemMatcher = hasFloat(equalTo(1f));

    assertMatches("array containing 1.0", itemMatcher, new float[] {1f, 2f, 3f});
  }

  @Test
  public void doesNotMatchArrayWithoutAnElementForGivenMatcher() {
    final Matcher<float[]> matcher = hasFloat(mismatchable(Float.valueOf(1)));

    assertMismatchDescription("mismatches were: [mismatched: 2.0, mismatched: 3.0]", matcher, new float[] {2, 3});
    assertMismatchDescription("was empty", matcher, new float[0]);
  }

  @Test
  public void doesNotMatchNull() {
    assertDoesNotMatch("doesn't match null", hasFloat(equalTo(1f)), null);
  }

  @Test
  public void hasAReadableDescription() {
    assertDescription("a primitive float array (float[]) containing mismatchable: 1.0", hasFloat(mismatchable(1f)));
  }

  @Test public void
  matchesMultipleFloatsInArray() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertMatches("array containing all floats", matcher0, new float[] {1, 2, 3});
  }

  @Test public void
  matchesMultipleFloatsInArray_InAnyOrder() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertMatches("array containing all floats in any order", matcher0, new float[] {3, 1, 2});
  }

  @Test public void
  matchesMultipleFloatsInArray_WithOtherFloats() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertMatches("array containing all floats in any order", matcher0, new float[] {1, 2, 3, 4, 5});
  }

  @Test public void
  matchesMultipleFloatsInArray_WithOtherFloats_InAnyOrder() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertMatches("array containing all floats in any order", matcher0, new float[] {5, 3, 1, 4, 2});
  }

  @Test public void
  doesNotMatchOneFloat_InArray() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertDoesNotMatch("not match array containing all floats", matcher0, new float[] {1, 2});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_InAnyOrder() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertDoesNotMatch("not match array containing all floats in any order", matcher0, new float[] {3, 1});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_WithOtherFloats() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertDoesNotMatch("not match array containing all floats with other floats", matcher0, new float[] {2, 3, 4, 5});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_WithOtherFloats_InAnyOrder() {
    final Matcher<float[]> matcher0 = hasFloats(1, 2, 3);
    assertDoesNotMatch("not match array containing all floats in any order", matcher0, new float[] {5, 3, 1, 4});
  }

  @Test public void
  matchesMultipleFloatsInArrayInAnyOrder_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertMatches("list containing all items", matcher0, new float[] {1, 2, 3});
  }

  @Test public void
  matchesMultipleFloatsInArray_InAnyOrder_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertMatches("array containing all floats in any order", matcher0, new float[] {3, 1, 2});
  }

  @Test public void
  matchesMultipleFloatsInArray_WithOtherFloats_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertMatches("array containing all floats in any order", matcher0, new float[] {1, 2, 3, 4, 5});
  }

  @Test public void
  matchesMultipleFloatsInArray_WithOtherFloats_InAnyOrder_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertMatches("array containing all floats in any order", matcher0, new float[] {5, 3, 1, 4, 2});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertDoesNotMatch("not match array containing all floats", matcher0, new float[] {1, 2});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_InAnyOrder_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertDoesNotMatch("not match array containing all floats in any order", matcher0, new float[] {3, 1});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_WithOtherFloats_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertDoesNotMatch("not match array containing all floats with other floats", matcher0, new float[] {2, 3, 4, 5});
  }

  @Test public void
  doesNotMatchOneFloat_InArray_WithOtherFloats_InAnyOrder_WithMatchers() {
    final Matcher<float[]> matcher0 = hasFloats(equalTo(1f), equalTo(2f), equalTo(3f));
    assertDoesNotMatch("not match array containing all ints in any order", matcher0, new float[] {5, 3, 1, 4});
  }

  private static Matcher<Float> mismatchable(final Float number) {
    return new TypeSafeDiagnosingMatcher<Float>() {
      @Override
      protected boolean matchesSafely(Float item, Description mismatchDescription) {
        if (number.equals(item)) 
          return true;

        mismatchDescription.appendText("mismatched: " + item);
        return false;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("mismatchable: " + number);
      }
    };
  }
}
