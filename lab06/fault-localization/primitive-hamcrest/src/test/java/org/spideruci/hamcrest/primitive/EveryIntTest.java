package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.AbstractMatcherTest.*;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class EveryIntTest {

  private final Matcher<int[]> matcher = EveryInt.everyInt(equalTo(0));
  
  @Test public void
  copesWithNullsAndUnknownTypes() {
      assertNullSafe(matcher);
      assertUnknownTypeSafe(matcher);
  }

  @Test public void
  matchesOnlyWhenEveryItemMatches() {
      assertMatches(matcher, new int[] { 0, 0, 0 });
      assertDoesNotMatch(matcher, new int[] { 3, 2, 1 });
  }

  @Test public void
  matchesEmptyLists() {
      assertMatches("didn't match empty int[]", matcher, new int[0]);
  }

  @Test public void
  describesItself() {
      assertDescription("every item is <0>", matcher);
  }

  @Test public void
  describesAMismatch() {
      assertMismatchDescription("an item was <3>", matcher, new int[] {3});
  }

}
