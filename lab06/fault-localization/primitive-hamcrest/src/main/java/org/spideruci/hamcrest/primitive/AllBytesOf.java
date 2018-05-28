package org.spideruci.hamcrest.primitive;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

public class AllBytesOf extends DiagnosingMatcher<byte[]> {

  private final Iterable<Matcher<byte[]>> matchers;
  
  public AllBytesOf(Iterable<Matcher<byte[]>> matchers) {
    this.matchers = matchers;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendList("(", " " + "and" + " ", ")", matchers);
  }

  @Override
  protected boolean matches(Object bite, Description mismatchDiscription) {
    for (Matcher<byte[]> matcher : matchers) {
      if (!matcher.matches(bite)) {
        mismatchDiscription.appendDescriptionOf(matcher).appendText(" ");
        matcher.describeMismatch(bite, mismatchDiscription);
        return false;
      }
    }
    return true;
  }
  
  public static Matcher<byte[]> allBytesOf(Iterable<Matcher<byte[]>> matchers) {
    return new AllBytesOf(matchers);
  }
  
  @SafeVarargs
  public static Matcher<byte[]> allBytesOf(Matcher<byte[]> ... matchers) {
    return allBytesOf(Arrays.asList(matchers));
  }

}
