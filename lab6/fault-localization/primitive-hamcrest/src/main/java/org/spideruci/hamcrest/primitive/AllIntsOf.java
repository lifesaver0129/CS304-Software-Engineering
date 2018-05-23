package org.spideruci.hamcrest.primitive;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

public class AllIntsOf extends DiagnosingMatcher<int[]> {

  private final Iterable<Matcher<int[]>> matchers;
  
  public AllIntsOf(Iterable<Matcher<int[]>> matchers) {
    this.matchers = matchers;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendList("(", " " + "and" + " ", ")", matchers);
  }

  @Override
  protected boolean matches(Object integer, Description mismatchDiscription) {
    for (Matcher<int[]> matcher : matchers) {
      if (!matcher.matches(integer)) {
        mismatchDiscription.appendDescriptionOf(matcher).appendText(" ");
        matcher.describeMismatch(integer, mismatchDiscription);
        return false;
      }
    }
    return true;
  }
  
  public static Matcher<int[]> allIntsOf(Iterable<Matcher<int[]>> matchers) {
    return new AllIntsOf(matchers);
  }
  
  @SafeVarargs
  public static Matcher<int[]> allIntsOf(Matcher<int[]> ... matchers) {
    return allIntsOf(Arrays.asList(matchers));
  }

}
