package org.spideruci.hamcrest.primitive;

import static org.spideruci.hamcrest.AbstractMatcherTest.*;
import static org.spideruci.hamcrest.primitive.IsByteArrayContaining.hasByte;
import static org.spideruci.hamcrest.primitive.IsByteArrayContaining.hasBytes;
import static org.hamcrest.core.IsEqual.equalTo;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.junit.Test;

public class IsByteArrayContainingTest {

  @Test
  public void copesWithNullsAndUnknownTypes() {
    Matcher<?> matcher = hasByte(equalTo((Byte)null));

    assertNullSafe(matcher);
    assertUnknownTypeSafe(matcher);
  }

  @Test
  public void matchesAnArrayThatContainsAnElementForTheGivenMatcher() {
    byte bite = 1;
    final Matcher<byte[]> itemMatcher = hasByte(equalTo(bite));

    assertMatches("array containing 1", itemMatcher, new byte[] {1, 2, 3});
  }

  @Test
  public void doesNotMatchArrayWithoutAnElementForGivenMatcher() {
    byte bite = 1;
    final Matcher<byte[]> matcher = hasByte(mismatchable(Byte.valueOf(bite)));

    assertMismatchDescription(
        "mismatches were: [mismatched: 2, mismatched: 3]", 
        matcher, new byte[] {2, 3});
    assertMismatchDescription("was empty", matcher, new byte[0]);
  }

  @Test
  public void doesNotMatchNull() {
    byte bite = 1;
    assertDoesNotMatch("doesn't match null", hasByte(equalTo(bite)), null);
  }

  @Test
  public void hasAReadableDescription() {
    byte bite = 1;
    assertDescription(
        "a primitive byte array (byte[]) containing mismatchable: 1", 
        hasByte(mismatchable(bite)));
  }

  @Test public void
  matchesMultipleBytesInArray() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertMatches("array containing all bytes", matcher0, new byte[] {1, 2, 3});
  }

  @Test public void
  matchesMultipleBytesInArray_InAnyOrder() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertMatches("array containing all bytes in any order", matcher0, new byte[] {3, 1, 2});
  }

  @Test public void
  matchesMultipleBytesInArray_WithOtherBytes() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertMatches("array containing all bytes in any order", matcher0, new byte[] {1, 2, 3, 4, 5});
  }

  @Test public void
  matchesMultipleBytesInArray_WithOtherBytes_InAnyOrder() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertMatches("array containing all bytes in any order", matcher0, new byte[] {5, 3, 1, 4, 2});
  }

  @Test public void
  doesNotMatchOneByte_InArray() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertDoesNotMatch(
        "not match array containing all bytes", 
        matcher0, new byte[] {1, 2});
  }

  @Test public void
  doesNotMatchOneByte_InArray_InAnyOrder() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertDoesNotMatch(
        "not match array containing all bytes in any order", 
        matcher0, new byte[] {3, 1});
  }

  @Test public void
  doesNotMatchOneByte_InArray_WithOtherByte() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertDoesNotMatch(
        "not match array containing all bytes with other bytes", 
        matcher0, new byte[] {2, 3, 4, 5});
  }

  @Test public void
  doesNotMatchOneByte_InArray_WithOtherBytes_InAnyOrder() {
    byte[] bytes = new byte[] {1, 2, 3};
    final Matcher<byte[]> matcher0 = hasBytes(bytes);
    assertDoesNotMatch(
        "not match array containing all bytes in any order", 
        matcher0, new byte[] {5, 3, 1, 4});
  }

  @Test public void
  matchesMultipleBytes_InArrayInAnyOrder_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertMatches("list containing all items", matcher0, new byte[] {1, 2, 3});
  }

  @Test public void
  matchesMultipleBytesInArray_InAnyOrder_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertMatches(
        "array containing all bytes in any order", 
        matcher0, new byte[] {3, 1, 2});
  }

  @Test public void
  matchesMultipleBytesInArray_WithOtherBytes_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertMatches(
        "array containing all bytes in any order", 
        matcher0, new byte[] {1, 2, 3, 4, 5});
  }

  @Test public void
  matchesMultipleBytesInArray_WithOtherBytes_InAnyOrder_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertMatches(
        "array containing all bytes in any order", 
        matcher0, new byte[] {5, 3, 1, 4, 2});
  }

  @Test public void
  doesNotMatchOneByte_InArray_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertDoesNotMatch(
        "not match array containing all bytes", matcher0, new byte[] {1, 2});
  }

  @Test public void
  doesNotMatchOneByte_InArray_InAnyOrder_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertDoesNotMatch(
        "not match array containing all bytes in any order", 
        matcher0, new byte[] {3, 1});
  }

  @Test public void
  doesNotMatchOneByte_InArray_WithOtherBytes_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertDoesNotMatch(
        "not match array containing all bytes with other bytes", 
        matcher0, new byte[] {2, 3, 4, 5});
  }

  @Test public void
  doesNotMatchOneByte_InArray_WithOtherBytes_InAnyOrder_WithMatchers() {
    byte byte1 = 1, byte2 = 2, byte3 = 3;
    final Matcher<byte[]> matcher0 = 
        hasBytes(equalTo(byte1), equalTo(byte2), equalTo(byte3));
    assertDoesNotMatch(
        "not match array containing all bytes in any order", 
        matcher0, new byte[] {5, 3, 1, 4});
  }

  private static Matcher<Byte> mismatchable(final Byte bite) {
    return new TypeSafeDiagnosingMatcher<Byte>() {
      @Override
      protected boolean matchesSafely(Byte item, Description mismatchDescription) {
        if (bite.equals(item))
          return true;

        mismatchDescription.appendText("mismatched: " + item);
        return false;
      }

      @Override
      public void describeTo(Description description) {
        description.appendText("mismatchable: " + bite);
      }
    };
  }

}