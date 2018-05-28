package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.primitive.AllBytesOf.allBytesOf;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsByteArrayContaining extends TypeSafeDiagnosingMatcher<byte[]> {
  private final Matcher<Byte> elementMatcher;

  public IsByteArrayContaining(Matcher<Byte> elementMatcher) {
    this.elementMatcher = elementMatcher;
  }

  @Override
  protected boolean matchesSafely(byte[] items, Description mismatchDescription) {
    if (isEmpty(items)) {
      mismatchDescription.appendText("was empty");
      return false;
    }

    for (byte item : items) {
      if (elementMatcher.matches(item)) {
        return true;
      }
    }

    mismatchDescription.appendText("mismatches were: [");
    int lastIndex = items.length - 1;
    for (int i = 0; i <= lastIndex - 1; i += 1) {
      byte item = items[i];
      elementMatcher.describeMismatch(item, mismatchDescription);
      mismatchDescription.appendText(", ");
    }
    elementMatcher.describeMismatch(items[lastIndex], mismatchDescription);
    mismatchDescription.appendText("]");
    return false;
  }

  private boolean isEmpty(byte[] items) {
    return items.length == 0;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a primitive byte array (byte[]) containing ")
    .appendDescriptionOf(elementMatcher);
  }

  public static Matcher<byte[]> hasByte(Matcher<Byte> elementMatcher) {
    return new IsByteArrayContaining(elementMatcher);
  }

  public static Matcher<byte[]> hasByte(byte item) {
    Matcher<Byte> matcher = equalTo(Byte.valueOf(item));
    return IsByteArrayContaining.hasByte(matcher);
  }

  @SafeVarargs
  public static Matcher<byte[]> hasBytes(Matcher<Byte> ... byteMatchers) {
    List<Matcher<byte[]>> all = new ArrayList<>(byteMatchers.length);

    for (Matcher<Byte> elementMatcher : byteMatchers) {
      all.add(new IsByteArrayContaining(elementMatcher));
    }

    return allBytesOf(all);
  }

  public static Matcher<byte[]> hasBytes(byte ... bytes) {
    List<Matcher<byte[]>> all = new ArrayList<>(bytes.length);

    for (byte element : bytes) {
      all.add(hasByte(element));
    }

    return allBytesOf(all);
  }

}
