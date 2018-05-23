package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.primitive.AllIntsOf.allIntsOf;
import static org.hamcrest.core.IsEqual.equalTo;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;

public class IsIntArrayContaining extends TypeSafeDiagnosingMatcher<int[]> {
  private final Matcher<Integer> elementMatcher;

  public IsIntArrayContaining(Matcher<Integer> elementMatcher) {
    this.elementMatcher = elementMatcher;
  }

  @Override
  protected boolean matchesSafely(int[] items, Description mismatchDescription) {
    if (isEmpty(items)) {
      mismatchDescription.appendText("was empty");
      return false;
    }

    for (int item : items) {
      if (elementMatcher.matches(item)) {
        return true;
      }
    }

    mismatchDescription.appendText("mismatches were: [");
    int lastIndex = items.length - 1;
    for (int i = 0; i <= lastIndex - 1; i += 1) {
      int item = items[i];
      elementMatcher.describeMismatch(item, mismatchDescription);
      mismatchDescription.appendText(", ");
    }
    elementMatcher.describeMismatch(items[lastIndex], mismatchDescription);
    mismatchDescription.appendText("]");
    return false;
  }

  private boolean isEmpty(int[] items) {
    return items.length == 0;
  }

  @Override
  public void describeTo(Description description) {
    description.appendText("a primitive integer array (int[]) containing ")
    .appendDescriptionOf(elementMatcher);
  }

  /**
   * Creates a matcher for primitive arrays that matches when the examined 
   * array contains at least one item that is matched by the specified 
   * <code>elementMatcher</code>.  Whilst matching, the traversal
   * of the examined array will stop as soon as a matching element is found.
   * For example:
   * <pre>assertThat(new int[] {1, 2}, hasItemInArray(equalTo(0)))</pre>
   * 
   * @param elementMatcher
   *     the matcher to apply to elements in examined arrays
   */
  public static Matcher<int[]> hasInt(Matcher<Integer> elementMatcher) {
    return new IsIntArrayContaining(elementMatcher);
  }

  /**
   * Creates a matcher for an int array that only matches when a single pass 
   * over the examined int array yields at least one item that is equal to the 
   * specified <code>item</code>.  Whilst matching, the traversal of the 
   * examined int array will stop as soon as a matching item is found.
   * For example:
   * <pre>assertThat(new int[]{1, 2}, hasItem(1))</pre>
   * 
   * @param item
   *     the item to compare against the items provided by the examined int array
   */
  public static Matcher<int[]> hasInt(int item) {
    Matcher<Integer> matcher = equalTo(Integer.valueOf(item));
    return IsIntArrayContaining.hasInt(matcher);
  }


  /**
   * Creates a matcher for the vararg intMatchers that matches when consecutive 
   * passes over the examined int array yield at least one item that is matched 
   * by the corresponding matcher from the specified <code>intMatchers</code>. 
   * While matching, each traversal of the examined int array will stop as soon 
   * as a matching item is found.
   * For example:
   * <pre>assertThat(new int[] { 1, 2, 3 }, hasItems(3, 2))</pre>
   * 
   * @param intMatchers
   *     the matchers to apply to ints provided by the examined int array
   */
  @SafeVarargs
  public static Matcher<int[]> hasInts(Matcher<Integer> ... intMatchers) {
    List<Matcher<int[]>> all = new ArrayList<>(intMatchers.length);

    for (Matcher<Integer> elementMatcher : intMatchers) {
      all.add(new IsIntArrayContaining(elementMatcher));
    }

    return allIntsOf(all);
  }

  public static Matcher<int[]> hasInts(int ... integers) {
    List<Matcher<int[]>> all = new ArrayList<>(integers.length);

    for (int element : integers) {
      all.add(hasInt(element));
    }

    return allIntsOf(all);
  }

}
