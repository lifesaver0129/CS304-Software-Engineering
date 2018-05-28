package org.spideruci.hamcrest.primitive;

import java.util.Arrays;

import org.hamcrest.Description;
import org.hamcrest.DiagnosingMatcher;
import org.hamcrest.Matcher;

public class AllFloatsOf extends DiagnosingMatcher<float[]> {

  private final Iterable<Matcher<float[]>> matchers;
  
  public AllFloatsOf(Iterable<Matcher<float[]>> matchers) {
    this.matchers = matchers;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendList("(", " " + "and" + " ", ")", matchers);
  }

  @Override
  protected boolean matches(Object number, Description mismatchDiscription) {
    for (Matcher<float[]> matcher : matchers) {
      if (!matcher.matches(number)) {
        mismatchDiscription.appendDescriptionOf(matcher).appendText(" ");
        matcher.describeMismatch(number, mismatchDiscription);
        return false;
      }
    }
    return true;
  }
  
  public static 
  Matcher<float[]> allFloatsOf(Iterable<Matcher<float[]>> matchers) {
    return new AllFloatsOf(matchers);
  }
  
  @SafeVarargs
  public static 
  Matcher<float[]> allFloatsOf(Matcher<float[]> ... matchers) {
    return allFloatsOf(Arrays.asList(matchers));
  }

}
