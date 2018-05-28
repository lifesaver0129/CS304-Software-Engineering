package org.spideruci.hamcrest.primitive;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertDescription;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertDoesNotMatch;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertMatches;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertMismatchDescription;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertNullSafe;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertUnknownTypeSafe;
import static org.spideruci.hamcrest.primitive.IsBooleanArrayContaining.hasBoolean;
import static org.spideruci.hamcrest.primitive.IsBooleanArrayContaining.hasBooleans;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

public class IsBooleanArrayContainingTest {

  @Test
  public void copesWithNullsAndUnknownTypes() {
    Matcher<?> matcher = hasBoolean(equalTo((Boolean)null));

    assertNullSafe(matcher);
    assertUnknownTypeSafe(matcher);
  }

  @Test
  public void matchesAnArrayThatContainsAnElementForTheGivenMatcher() {
    boolean bool = true;
    final Matcher<boolean[]> itemMatcher = hasBoolean(equalTo(bool));

    assertMatches("array containing true", itemMatcher, new boolean[] {true, false, false});
  }

  @Test
  public void doesNotMatchArrayWithoutAnElementForGivenMatcher() {
    boolean bool = true;
    final Matcher<boolean[]> matcher = hasBoolean(mismatchable(Boolean.valueOf(bool)));

    assertMismatchDescription(
        "mismatches were: [mismatched: false, mismatched: false]", 
        matcher, new boolean[] {false, false});
    assertMismatchDescription("was empty", matcher, new boolean[0]);
  }

  @Test
  public void doesNotMatchNull() {
    boolean bool = true;
    assertDoesNotMatch("doesn't match null", hasBoolean(equalTo(bool)), null);
  }

  @Test
  public void hasAReadableDescription() {
    boolean bool = true;
    assertDescription(
        "a primitive boolean array (boolean[]) containing mismatchable: true", 
        hasBoolean(mismatchable(bool)));
  }

  @Test public void
  matchesMultipleBooleansInArray() {
    boolean[] bools = new boolean[] {true, false, true};
    final Matcher<boolean[]> matcher0 = hasBooleans(bools);
    assertMatches("array containing all booleans", matcher0, new boolean[] {true, false, true});
  }

  @Test public void
  matchesMultipleBooleansInArray_InAnyOrder() {
    boolean[] bools = new boolean[] {true, false, true, false};
    final Matcher<boolean[]> matcher0 = hasBooleans(bools);
    assertMatches("array containing all bytes in any order", matcher0, new boolean[] {false, true, false, true});
  }

  @Test public void
  matchesMultipleBooleansInArray_WithOtherBytes() {
    boolean[] bools = new boolean[] {true};
    final Matcher<boolean[]> matcher0 = hasBooleans(bools);
    assertMatches("array containing all booleans, with other booleans", matcher0, new boolean[] {true, false});
  }

  @Test public void
  matchesMultipleBytesInArray_WithOtherBytes_InAnyOrder() {
    boolean[] bools = new boolean[] {true};
    final Matcher<boolean[]> matcher0 = hasBooleans(bools);
    assertMatches("array containing all booleans in any order, with other booleans", matcher0, new boolean[] {false, true});
  }

  @Test public void
  doesNotMatchOneBoolean_InArray() {
    boolean[] bytes = new boolean[] {true, true, false};
    final Matcher<boolean[]> matcher0 = hasBooleans(bytes);
    assertDoesNotMatch(
        "not match array containing all booleans", 
        matcher0, new boolean[] {true, true});
  }

  @Test public void
  matchesMultipleBooleans_InArrayInAnyOrder_WithMatchers() {
    boolean bool1 = true, bool2 = false, bool3 = true;
    final Matcher<boolean[]> matcher0 = 
        hasBooleans(equalTo(bool1), equalTo(bool2), equalTo(bool3));
    assertMatches("list containing all items", matcher0, new boolean[] {true, false, true});
  }

  @Test public void
  matchesMultipleBooleans_InArray_InAnyOrder_WithMatchers() {
    boolean bool1 = true, bool2 = false, bool3 = true, bool4 = false;
    final Matcher<boolean[]> matcher0 = 
        hasBooleans(equalTo(bool1), equalTo(bool2), equalTo(bool3), equalTo(bool4));
    assertMatches("list containing all items", matcher0, new boolean[] {false, true, false, true});
  }

  private static Matcher<Boolean> mismatchable(final Boolean bool) {
    return new TypeSafeDiagnosingMatcher<Boolean>() {
      @Override
      protected boolean matchesSafely(Boolean item, Description mismatchDescription) {
        if (bool.equals(item))
          return true;

        mismatchDescription.appendText("mismatched: " + item);
        return false;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("mismatchable: " + bool);
      }
    };
  }

}
