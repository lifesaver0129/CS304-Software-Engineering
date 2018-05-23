// ***** This file is automatically generated from CommonSequence.java.jpp

package daikon.inv.unary.sequence;

import daikon.*;
import daikon.inv.*;
import java.util.Arrays;
import org.plumelib.util.ArraysPlume;
import org.plumelib.util.Intern;

/*>>>
import org.checkerframework.checker.interning.qual.*;
import org.checkerframework.checker.lock.qual.*;
import org.checkerframework.checker.nullness.qual.*;
import org.checkerframework.dataflow.qual.*;
import typequals.*;
*/

/**
 * Represents sequences of long values that contain a common subset. Prints as
 * {@code {e1, e2, e3, ...} subset of x[]}.
 */

public class CommonSequence extends SingleScalarSequence {
  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20030822L;

  // Variables starting with dkconfig_ should only be set via the
  // daikon.config.Configuration interface.
  /** Boolean. True iff CommonSequence invariants should be considered. */
  public static boolean dkconfig_enabled = false;

  /** Boolean. Set to true to consider common sequences over hashcodes (pointers). */
  public static boolean dkconfig_hashcode_seqs = false;

  static final boolean debugCommonSequence = false;

  private int elts = 0;

  /**
   * Null means no samples have been seen yet.
   * Empty array means intersection is empty.
   */
  private long /*@MonotonicNonNull*/ [] intersect = null;

  protected CommonSequence(PptSlice ppt) {
    super(ppt);
  }

  protected /*@Prototype*/ CommonSequence() {
    super();
  }

  private static /*@Prototype*/ CommonSequence proto = new /*@Prototype*/ CommonSequence();

  /** Returns the prototype invariant for CommonSequence */
  public static /*@Prototype*/ CommonSequence get_proto() {
    return proto;
  }

  /** returns whether or not this invariant is enabled */
  @Override
  public boolean enabled() {
    return dkconfig_enabled;
  }

  /** Sequences of hashcode values won't be consistent and are thus not printed by default. */
  @Override
  public boolean instantiate_ok(VarInfo[] vis) {

    if (!valid_types(vis)) {
      return false;
    }

    return (dkconfig_hashcode_seqs || vis[0].file_rep_type.baseIsIntegral());
  }

  /** instantiate an invariant on the specified slice */
  @Override
  protected CommonSequence instantiate_dyn(/*>>> @Prototype CommonSequence this,*/ PptSlice slice) {
    return new CommonSequence(slice);
  }

  // this.intersect is read-only, so don't clone it
  // public Object clone();

  @Override
  public String repr(/*>>>@GuardSatisfied CommonSequence this*/) {
    return "CommonSequence " + varNames() + ": elts=\"" + elts;
  }

  private String printIntersect(/*>>>@GuardSatisfied CommonSequence this*/) {
    if (intersect == null) {
      return "{}";
    }

    String result = "{";
    for (int i = 0; i < intersect.length; i++) {
      result += intersect[i];
      if (i != intersect.length - 1) {
        result += ", ";
      }
    }
    result += "}";
    return result;
  }

  /*@SideEffectFree*/
  @Override
  public String format_using(/*>>>@GuardSatisfied CommonSequence this,*/ OutputFormat format) {
    if (format == OutputFormat.DAIKON) return format_daikon();
    if (format == OutputFormat.CSHARPCONTRACT) return format_csharp_contract();
    if (format == OutputFormat.SIMPLIFY) return format_simplify();

    return format_unimplemented(format);
  }

  public String format_daikon(/*>>>@GuardSatisfied CommonSequence this*/) {
    return (printIntersect() + " subset of " + var().name());
  }

  public String format_csharp_contract(/*>>>@GuardSatisfied CommonSequence this*/) {
    if (intersect == null) {
      return "()";
    }

    if (intersect.length == 1) {
      String collection = var().csharp_collection_string();
      return collection + ".Contains(" + intersect[0] + ")";
    }

    String exp = "(";
    for (int i = 0; i < intersect.length; i++) {
      exp += " " + intersect[i] + " ";
      if (i != intersect.length - 1) {
        exp += ",";
      }
    }
    exp += ")";

    String[] split = var().csharp_array_split();
    return "Contract.ForAll(" + split[0] + ", x => x" + split[1] + ".OneOf" + exp + ")";
  }

  private String format_simplify(/*>>>@GuardSatisfied CommonSequence this*/) {
    if (intersect == null || intersect.length == 0) {
      return "(AND)";
    }
    String[] name = var().simplifyNameAndBounds();
    if (name == null) {
      return format_unimplemented(OutputFormat.SIMPLIFY);
    }
    String idx;
    if (!name[0].equals("|i|")) {
      idx = "i";
    } else {
      idx = "j";
    }
    StringBuilder pre_buf = new StringBuilder("");
    StringBuilder end_buf = new StringBuilder("");
    for (int i = 0; i < intersect.length; i++) {
      pre_buf.append("(EXISTS ("+idx + i + ") (AND ");
      pre_buf.append("(>= "+idx + i + " " + name[1] + ") ");
      pre_buf.append("(<= "+idx + i + " " + name[2] + ") ");

      // Based on the class name, I originally wrote this method as if
      // the invariant represented a common subsequence between two
      // sequences (i.e. where the match was required to be in
      // order). In case an invariant like that is added in the
      // future, use the following:
//       if (i == 0)
//         pre_buf.append("(>= "+idx + i + " 0) ");
//       else if (i > 0)
//          pre_buf.append("(> "+idx + i + " "+idx+(i-1) +") ");
//       if (i == intersect.length - 1)
//         pre_buf.append("(< "+idx + i + " (select arrayLength " + name[0] + ")) ");
      pre_buf.append("(EQ (select (select elems " + name[0] + ") "+idx + i + ") "
                     + simplify_format_long(intersect[i]) + ")");
      if (i == intersect.length - 1) {
        pre_buf.append(" ");
      }
      end_buf.append("))");
    }
    pre_buf.append(end_buf);
    return pre_buf.toString();
  }

  @Override
  public InvariantStatus check_modified(long /*@Interned*/ [] a, int count) {
    if (a == null) {
      return InvariantStatus.FALSIFIED;
    } else if (intersect == null) {
      return InvariantStatus.NO_CHANGE;
    } else {
      for (int i = 0; i < a.length; i++) {
        if (ArraysPlume.indexOf(intersect, a[i]) != -1) {
          return InvariantStatus.NO_CHANGE;
        }
      }
      return InvariantStatus.FALSIFIED;
    }
  }

  @Override
  public InvariantStatus add_modified(long /*@Interned*/ [] a, int count) {
    // System.out.println ("common: " + Arrays.toString (a));
    if (a == null) {
      return InvariantStatus.FALSIFIED;
    } else if (intersect == null) {
      intersect = a;
      return InvariantStatus.NO_CHANGE;
    }

    long[] tmp = new long[intersect.length];
    int size = 0;
    for (int i = 0; i < a.length; i++) {
      // if (a[i] in intersect) && !(a[i] in tmp), add a[i] to tmp
      int ii = ArraysPlume.indexOf(intersect, a[i]);
      if ((ii != -1)
        && (ArraysPlume.indexOf(ArraysPlume.subarray(tmp,0,size), a[i]) == -1)) {
        //System.out.println ("adding " + intersect[ii] + " at " + size);

        // Carefully add the existing intersect value and not a[i].  These
        // are not necessarily the same when fuzzy floating point
        // comparisons are active.
        tmp[size++] = intersect[ii];
      }
    }
    if (size == 0) {
      return InvariantStatus.FALSIFIED;
    }

    intersect = ArraysPlume.subarray(tmp, 0, size);

    intersect = Intern.intern(intersect);
    elts++;
    return InvariantStatus.NO_CHANGE;
  }

  @Override
  protected double computeConfidence() {
    return 1 - Math.pow(.9, elts);
  }

  /*@Pure*/
  @Override
  public boolean isSameFormula(Invariant other) {
    assert other instanceof CommonSequence;
    return true;
  }
}
