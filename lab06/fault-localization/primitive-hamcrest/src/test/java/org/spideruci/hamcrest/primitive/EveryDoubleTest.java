package org.spideruci.hamcrest.primitive;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertDescription;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertDoesNotMatch;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertMatches;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertMismatchDescription;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertNullSafe;
import static org.spideruci.hamcrest.AbstractMatcherTest.assertUnknownTypeSafe;

import org.hamcrest.Matcher;
import org.junit.Test;

public class EveryDoubleTest {


  private final Matcher<double[]> matcher = EveryDouble.everyDouble(equalTo(0.0));
  
  @Test public void
  copesWithNullsAndUnknownTypes() {
      assertNullSafe(matcher);
      assertUnknownTypeSafe(matcher);
  }

  @Test public void
  matchesOnlyWhenEveryItemMatches() {
      assertMatches(matcher, new double[] { 0, 0, 0 });
      assertDoesNotMatch(matcher, new double[] { 3, 2, 1 });
  }

  @Test public void
  matchesEmptyLists() {
      assertMatches("didn't match empty double[]", matcher, new double[0]);
  }

  @Test public void
  describesItself() {
      assertDescription("every item is <0.0>", matcher);
  }

  @Test public void
  describesAMismatch() {
      assertMismatchDescription("an item was <3.0>", matcher, new double[] {3});
  }

}
