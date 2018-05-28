package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.AbstractMatcherTest.*;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.Matcher;
import org.junit.Test;

public class EveryBoolTest {

  private final Matcher<boolean[]> matcher = EveryBool.everyBool(equalTo(false));
  
  @Test public void
  copesWithNullsAndUnknownTypes() {
      assertNullSafe(matcher);
      assertUnknownTypeSafe(matcher);
  }

  @Test public void
  matchesOnlyWhenEveryItemMatches() {
      assertMatches(matcher, new boolean[] { false, false, false });
      assertDoesNotMatch(matcher, new boolean[] { true, false, false });
      assertDoesNotMatch(matcher, new boolean[] { true, false, true });
      assertDoesNotMatch(matcher, new boolean[] { true, true, false });
  }

  @Test public void
  matchesEmptyLists() {
      assertMatches("didn't match empty boolean[]", matcher, new boolean[0]);
  }

  @Test public void
  describesItself() {
      assertDescription("every item is <false>", matcher);
  }

  @Test public void
  describesAMismatch() {
      assertMismatchDescription("an item was <true>", matcher, new boolean[] {true});
  }

}