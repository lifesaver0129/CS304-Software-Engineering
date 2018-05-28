package org.spideruci.hamcrest.primitive;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

public class AllBooleanOf extends DiagnosingMatcher<boolean[]> {
  
  private final Iterable<Matcher<boolean[]>> matchers;
  
  public AllBooleanOf(Iterable<Matcher<boolean[]>> matchers) {
    this.matchers = matchers;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendList("(", " " + "and" + " ", ")", matchers);
  }

  @Override
  protected boolean matches(Object bool, Description mismatchDiscription) {
    for (Matcher<boolean[]> matcher : matchers) {
      if (!matcher.matches(bool)) {
        mismatchDiscription.appendDescriptionOf(matcher).appendText(" ");
        matcher.describeMismatch(bool, mismatchDiscription);
        return false;
      }
    }
    return true;
  }
  
  public static Matcher<boolean[]> allBooleanOf(Iterable<Matcher<boolean[]>> matchers) {
    return new AllBooleanOf(matchers);
  }
  
  @SafeVarargs
  public static Matcher<boolean[]> allBooleanOf(Matcher<boolean[]> ... matchers) {
    return allBooleanOf(Arrays.asList(matchers));
  }

}
