// ***** This file is automatically generated from SeqIndexComparison.java.jpp

package daikon.inv.unary.sequence;

import daikon.*;
import daikon.Quantify.QuantFlags;
import daikon.inv.*;
import daikon.inv.binary.twoSequence.*;
import daikon.suppress.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.logging.Logger;
import org.plumelib.util.Intern;

/*>>>
import org.checkerframework.checker.interning.qual.*;
import org.checkerframework.checker.lock.qual.*;
import org.checkerframework.checker.nullness.qual.*;
import org.checkerframework.dataflow.qual.*;
import typequals.*;
*/

/**
 * Represents an invariant over sequences of long values between the index of an element of the
 * sequence and the element itself. Prints as {@code x[i] > i}.
 */
public class SeqIndexIntGreaterThan extends SingleScalarSequence {
  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20040203L;

  /** Debug tracer. */
  public static final Logger debug =
    Logger.getLogger("daikon.inv.unary.sequence.SeqIndexIntGreaterThan");

  // Variables starting with dkconfig_ should only be set via the
  // daikon.config.Configuration interface.
  /** Boolean. True iff SeqIndexIntGreaterThan invariants should be considered. */
  public static boolean dkconfig_enabled = false;

  private /*@Prototype*/ SeqIndexIntGreaterThan() {
    super();
  }

  protected SeqIndexIntGreaterThan(PptSlice slice) {
    super(slice);
    assert slice != null;
    assert var().rep_type == ProglangType.INT_ARRAY;
  }

  private static /*@Prototype*/ SeqIndexIntGreaterThan proto = new /*@Prototype*/ SeqIndexIntGreaterThan();

  /** Returns the prototype invariant for SeqIndexIntGreaterThan */
  public static /*@Prototype*/ SeqIndexIntGreaterThan get_proto() {
    return proto;
  }

  /** returns whether or not we are enabled */
  @Override
  public boolean enabled() {
    return (dkconfig_enabled && !dkconfig_SeqIndexDisableAll);
  }

  /** Check that SeqIndex comparisons make sense over these vars */
  @Override
  public boolean instantiate_ok(VarInfo[] vis) {

    if (!valid_types(vis)) {
      return false;
    }

    // Don't compare indices to object addresses.
    ProglangType elt_type = vis[0].file_rep_type.elementType();
    if (!elt_type.baseIsIntegral()) {
      return false;
    }

    // Make sure that the indices are comparable to the elements
    VarInfo seqvar = vis[0];
    assert seqvar.comparability != null;
    VarComparability elt_compar = seqvar.comparability.elementType();
    VarComparability index_compar = seqvar.comparability.indexType(0);
    if (!VarComparability.comparable(elt_compar, index_compar)) {
      return false;
    }

    return true;
  }

  /** Instantiate the invariant on the specified slice */
  @Override
  public SeqIndexIntGreaterThan instantiate_dyn(/*>>> @Prototype SeqIndexIntGreaterThan this,*/ PptSlice slice) {
    return new SeqIndexIntGreaterThan(slice);
  }

  /** returns the ni-suppressions for SeqIndexIntGreaterThan */
  /*@Pure*/
  @Override
  public /*@Nullable*/ NISuppressionSet get_ni_suppressions() {
    return null;
  }

  protected Invariant resurrect_done_swapped() {

      return new SeqIndexIntLessThan(ppt);
  }

  public String getComparator() {
    return ">";
  }

  /*@SideEffectFree*/
  @Override
  public String format_using(/*>>>@GuardSatisfied SeqIndexIntGreaterThan this,*/ OutputFormat format) {
    if (format.isJavaFamily()) return format_java_family(format);

    // TODO: Eliminate the unnecessary format_xxx() below if the
    // format_java_family() can handle all the Java family output.

    if (format == OutputFormat.DAIKON) return format_daikon();
    if (format == OutputFormat.ESCJAVA) return format_esc();
    if (format == OutputFormat.CSHARPCONTRACT) return format_csharp_contract();
    if (format == OutputFormat.SIMPLIFY) return format_simplify();

    return format_unimplemented(format);
  }

  public String format_daikon(/*>>>@GuardSatisfied SeqIndexIntGreaterThan this*/) {

    // If this is an array/container and not a subsequence
    if (var().isDerivedSubSequenceOf() == null) {
      return var().apply_subscript("i") + " > i";
    } else {
      return var().name() + " > (index)";
    }
  }

  // Bad code here: if the first index is changed from i this breaks
  public String format_esc(/*>>>@GuardSatisfied SeqIndexIntGreaterThan this*/) {
    String[] form = VarInfo.esc_quantify(var());
    return form[0] + "(" + form[1] + " > i)" + form[2];
  }

  public String format_csharp_contract(/*>>>@GuardSatisfied SeqIndexIntGreaterThan this*/) {
    String[] split = var().csharp_array_split();
    return "Contract.ForAll(0, " + split[0] + ".Count(), i => " + split[0] + "[i]" + split[1] + " > i)";
  }

  public String format_java_family(/*>>>@GuardSatisfied SeqIndexIntGreaterThan this,*/ OutputFormat format) {
    return "daikon.Quant.eltsGtIndex("
      + var().name_using(format) + ")";
  }

  public String format_simplify(/*>>>@GuardSatisfied SeqIndexIntGreaterThan this*/) {
    String[] form = VarInfo.simplify_quantify(QuantFlags.include_index(),
                                               var());
    return form[0] + "(> " + form[1] + " " + form[2] + ")"
      + form[3];
  }

  @Override
  public InvariantStatus check_modified(long /*@Interned*/ [] a, int count) {
    for (int i = 0; i < a.length; i++) {
      if (!(a[i] > i)) {
        return InvariantStatus.FALSIFIED;
      }
    }
    return InvariantStatus.NO_CHANGE;
  }

  @Override
  public InvariantStatus add_modified(long /*@Interned*/ [] a, int count) {

    if (logDetail()) {
      log("Entered add_modified: ppt.num_values()==%s, sample==%s",
           ppt.num_values(), Arrays.toString(a));
    }
    InvariantStatus stat = check_modified(a, count);
    if (logDetail()) {
      log("Exiting add_modified status = %s", stat);
    }

    return stat;
  }

  @Override
  protected double computeConfidence() {

    // Make sure there have been some elements in the sequence
    ValueSet.ValueSetScalarArray vs = (ValueSet.ValueSetScalarArray) ppt.var_infos[0].get_value_set();
    if (vs.elem_cnt() == 0) {
      return Invariant.CONFIDENCE_UNJUSTIFIED;
    }

    int num_values = ppt.num_values();
    if (num_values == 0) {
      return Invariant.CONFIDENCE_UNJUSTIFIED;
    }

      return 1 - Math.pow(.5, num_values);
  }

  /*@Pure*/
  @Override
  public boolean isSameFormula(Invariant other) {
    return true;
  }

  /*@Pure*/
  @Override
  public boolean isExclusiveFormula(Invariant other) {
    return false;
  }

  // Look up a previously instantiated invariant.
  public static /*@Nullable*/ SeqIndexIntGreaterThan find(PptSlice ppt) {
    assert ppt.arity() == 1;
    for (Invariant inv : ppt.invs) {
      if (inv instanceof SeqIndexIntGreaterThan) {
        return (SeqIndexIntGreaterThan) inv;
      }
    }
    return null;
  }

  /**
   * Checks to see if this is obvious over the specified variables Implements the following checks:
   *
   * <pre>
   *    (A[] subsequence B[]) ^ (B[i] op i) &rArr; A[i] op i
   * </pre>
   *
   * JHP: Its not obvious (to me) that this is true except when the subsequence starts at index
   * 0. If B[] = {0, 1, 2, 3, 4} and A[] = {2, 3, 4}, A[] is a subsequence of B[] and B[i] == i, but
   * A[i] = i is not true.
   */
  /*@Pure*/
  @Override
  public /*@Nullable*/ DiscardInfo isObviousDynamically(VarInfo[] vis) {

    DiscardInfo super_result = super.isObviousDynamically(vis);
    if (super_result != null) {
      return super_result;
    }

    VarInfo seqvar = vis[0];

    // For each other sequence variable, if it is a supersequence of this
    // one and it has the same invariant, then this one is obvious.
    // We have to check for the same equality set here, because
    // isObviousDynamically is called for each member of the equality set.
    // We don't want other members of our own equality set to make this obvious
    PptTopLevel pptt = ppt.parent;
    for (int i = 0; i < pptt.var_infos.length; i++) {
      VarInfo vi = pptt.var_infos[i];
      if (vi.equalitySet == seqvar.equalitySet) {
        continue;
      }
      if (SubSequence.isObviousSubSequenceDynamically(this, seqvar, vi)) {
        PptSlice1 other_slice = pptt.findSlice(vi);
        if (other_slice != null) {
          SeqIndexIntGreaterThan other_sine = SeqIndexIntGreaterThan.find(other_slice);
          if ((other_sine != null) && other_sine.enoughSamples()) {
            return new DiscardInfo(this, DiscardCode.obvious,
                        "The invariant " + format() + " over var "
                       + seqvar.name() + " also holds over "
                       +" the supersequence " + vi.name());
          }
        }
      }
    }

    return null;
  }
}
