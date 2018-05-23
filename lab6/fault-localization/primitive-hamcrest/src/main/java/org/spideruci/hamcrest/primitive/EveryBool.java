package org.spideruci.hamcrest.primitive;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class EveryBool extends TypeSafeDiagnosingMatcher<boolean[]> {
    
    private final Matcher<Boolean> matcher;

    public EveryBool(Matcher<Boolean> matcher) {
      this.matcher = matcher;
    }
    
    @Override
    public void describeTo(Description description) {
      description.appendText("every item is ").appendDescriptionOf(matcher);
    }

    @Override
    protected boolean matchesSafely(boolean[] bools, Description mismatchDescription) {
      for (boolean t : bools) {
        if (!matcher.matches(t)) {
          mismatchDescription.appendText("an item ");
          matcher.describeMismatch(t, mismatchDescription);
          return false;
        }
      }
      return true;
    }
    
    public static Matcher<boolean[]> everyBool(final Matcher<Boolean> itemMatcher) {
      return new EveryBool(itemMatcher);
    }
}
