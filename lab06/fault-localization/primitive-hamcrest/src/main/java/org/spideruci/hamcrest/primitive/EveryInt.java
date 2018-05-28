package org.spideruci.hamcrest.primitive;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class EveryInt extends TypeSafeDiagnosingMatcher<int[]> {
  
  private final Matcher<Integer> matcher;

  public EveryInt(Matcher<Integer> matcher) {
    this.matcher = matcher;
  }
  
  @Override
  public void describeTo(Description description) {
    description.appendText("every item is ").appendDescriptionOf(matcher);
  }

  @Override
  protected boolean matchesSafely(int[] ints, Description mismatchDescription) {
    for (int t : ints) {
      if (!matcher.matches(t)) {
        mismatchDescription.appendText("an item ");
        matcher.describeMismatch(t, mismatchDescription);
        return false;
      }
    }
    return true;
  }
  
  public static Matcher<int[]> everyInt(final Matcher<Integer> itemMatcher) {
    return new EveryInt(itemMatcher);
  }
}
