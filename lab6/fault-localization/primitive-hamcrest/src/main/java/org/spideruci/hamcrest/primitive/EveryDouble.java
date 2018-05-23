package org.spideruci.hamcrest.primitive;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class EveryDouble extends TypeSafeDiagnosingMatcher<double[]> {
  
  private final Matcher<Double> matcher;

  public EveryDouble(Matcher<Double> matcher) {
    this.matcher = matcher;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendText("every item is ").appendDescriptionOf(matcher);
  }

  @Override
  protected boolean matchesSafely(double[] ints, Description mismatchDescription) {
    for (double t : ints) {
      if (!matcher.matches(t)) {
        mismatchDescription.appendText("an item ");
        matcher.describeMismatch(t, mismatchDescription);
        return false;
      }
    }
    return true;
  }
  
  public static Matcher<double[]> everyDouble(final Matcher<Double> itemMatcher) {
    return new EveryDouble(itemMatcher);
  }

}
