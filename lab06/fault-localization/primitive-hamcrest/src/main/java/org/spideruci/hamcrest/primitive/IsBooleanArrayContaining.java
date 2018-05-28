package org.spideruci.hamcrest.primitive;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.spideruci.hamcrest.primitive.AllBooleanOf.allBooleanOf;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsBooleanArrayContaining extends TypeSafeDiagnosingMatcher<boolean[]> {
  private final Matcher<Boolean> elementMatcher;

  public IsBooleanArrayContaining(Matcher<Boolean> elementMatcher) {
    this.elementMatcher = elementMatcher;
  }

  @Override
  protected boolean matchesSafely(boolean[] items, Description mismatchDescription) {
    if (isEmpty(items)) {
      mismatchDescription.appendText("was empty");
      return false;
    }

    for (boolean item : items) {
      if (elementMatcher.matches(item)) {
        return true;
      }
    }

    mismatchDescription.appendText("mismatches were: [");
    int lastIndex = items.length - 1;
    for (int i = 0; i <= lastIndex - 1; i += 1) {
      boolean item = items[i];
      elementMatcher.describeMismatch(item, mismatchDescription);
      mismatchDescription.appendText(", ");
    }
    elementMatcher.describeMismatch(items[lastIndex], mismatchDescription);
    mismatchDescription.appendText("]");
    return false;
  }

  private boolean isEmpty(boolean[] items) {
    return items.length == 0;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a primitive boolean array (boolean[]) containing ")
    .appendDescriptionOf(elementMatcher);
  }

  public static Matcher<boolean[]> hasBoolean(Matcher<Boolean> elementMatcher) {
    return new IsBooleanArrayContaining(elementMatcher);
  }

  public static Matcher<boolean[]> hasBoolean(boolean item) {
    Matcher<Boolean> matcher = equalTo(Boolean.valueOf(item));
    return IsBooleanArrayContaining.hasBoolean(matcher);
  }
  
  public static Matcher<boolean[]> hasTrue() {
    return hasBoolean(true);
  }
  
  public static Matcher<boolean[]> hasFalse() {
    return hasBoolean(false);
  }

  @SafeVarargs
  public static Matcher<boolean[]> hasBooleans(Matcher<Boolean> ... booleanMatchers) {
    List<Matcher<boolean[]>> all = new ArrayList<>(booleanMatchers.length);

    for (Matcher<Boolean> elementMatcher : booleanMatchers) {
      all.add(new IsBooleanArrayContaining(elementMatcher));
    }

    return allBooleanOf(all);
  }

  public static Matcher<boolean[]> hasBooleans(boolean ... booleans) {
    List<Matcher<boolean[]>> all = new ArrayList<>(booleans.length);

    for (boolean element : booleans) {
      all.add(hasBoolean(element));
    }

    return allBooleanOf(all);
  }

}
