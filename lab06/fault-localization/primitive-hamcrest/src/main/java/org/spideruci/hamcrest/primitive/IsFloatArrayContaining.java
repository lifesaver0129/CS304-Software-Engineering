package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.primitive.AllFloatsOf.allFloatsOf;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsFloatArrayContaining extends TypeSafeDiagnosingMatcher<float[]> {
  private final Matcher<Float> elementMatcher;

  public IsFloatArrayContaining(Matcher<Float> elementMatcher) {
    this.elementMatcher = elementMatcher;
  }

  @Override
  protected boolean matchesSafely(float[] items, Description mismatchDescription) {
    if (isEmpty(items)) {
      mismatchDescription.appendText("was empty");
      return false;
    }

    for (float item : items) {
      if (elementMatcher.matches(item)) {
        return true;
      }
    }


    mismatchDescription.appendText("mismatches were: [");
    int lastIndex = items.length - 1;
    for (int i = 0; i <= lastIndex - 1; i += 1) {
      float item = items[i];
      elementMatcher.describeMismatch(item, mismatchDescription);
      mismatchDescription.appendText(", ");
    }
    elementMatcher.describeMismatch(items[lastIndex], mismatchDescription);
    mismatchDescription.appendText("]");
    return false;
  }

  private boolean isEmpty(float[] items) {
    return items.length == 0;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a primitive float array (float[]) containing ")
    .appendDescriptionOf(elementMatcher);
  }

  
  public static Matcher<float[]> hasFloat(Matcher<Float> elementMatcher) {
    return new IsFloatArrayContaining(elementMatcher);
  }

  public static Matcher<float[]> hasFloat(float item) {
    Matcher<Float> matcher = equalTo(Float.valueOf(item));
    return IsFloatArrayContaining.hasFloat(matcher);
  }

  @SafeVarargs
  public static Matcher<float[]> hasFloats(Matcher<Float> ... intMatchers) {
    List<Matcher<float[]>> all = new ArrayList<>(intMatchers.length);

    for(Matcher<Float> elementMatcher : intMatchers) {
      all.add(new IsFloatArrayContaining(elementMatcher));
    }

    return allFloatsOf(all);
  }

  public static Matcher<float[]> hasFloats(float ... numbers) {
    List<Matcher<float[]>> all = new ArrayList<>(numbers.length);

    for(float element : numbers) {
      all.add(hasFloat(element));
    }

    return allFloatsOf(all);
  }

}
