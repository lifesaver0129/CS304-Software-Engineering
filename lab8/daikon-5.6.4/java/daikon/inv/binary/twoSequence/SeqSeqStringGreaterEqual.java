  // #define PAIRWISECOMP_EQ PairwiseStringEqual
  // #define PAIRWISECOMP_LT PairwiseStringLessThan
  // #define PAIRWISECOMP_GT PairwiseStringGreaterThan
  // #define PAIRWISECOMP_LE PairwiseStringLessEqual
  // #define PAIRWISECOMP_GE PairwiseStringGreaterEqual

  // #define PAIRWISEINTEQUAL PairwiseStringGreaterEqual

// ***** This file is automatically generated from SeqComparison.java.jpp

package daikon.inv.binary.twoSequence;

import daikon.*;
import daikon.Quantify.QuantFlags;
import daikon.derive.binary.*;
import daikon.inv.*;
import daikon.suppress.*;
import java.util.*;
import java.util.logging.Logger;
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
 * Represents invariants between two sequences of String values. If order matters for each
 * variable (which it does by default), then the sequences are compared lexically. Prints as
 * {@code x[] >= y[] lexically}.
 *

 * <p>If the auxiliary information (e.g., order matters) doesn't match between two variables, then
 * this invariant cannot apply to those variables.
 */
public class SeqSeqStringGreaterEqual extends TwoSequenceString

{
  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20030822L;

  // Variables starting with dkconfig_ should only be set via the
  // daikon.config.Configuration interface.
  /** Boolean. True iff SeqSeqStringGreaterEqual invariants should be considered. */
  public static boolean dkconfig_enabled = Invariant.invariantEnabledDefault;

  /** Debugging logger. */
  static final Logger debug = Logger.getLogger("daikon.inv.binary.twoSequence.SeqSeqStringGreaterEqual");

  @SuppressWarnings("interning")  // bug with generics
  static Comparator<String[]> comparator = new ArraysPlume.StringArrayComparatorLexical();

  boolean orderMatters;

  protected SeqSeqStringGreaterEqual(PptSlice ppt, boolean order) {
    super(ppt);
    orderMatters = order;
  }

  protected /*@Prototype*/ SeqSeqStringGreaterEqual(boolean order) {
    super();
    orderMatters = order;
  }

  protected SeqSeqStringGreaterEqual(SeqSeqStringLessEqual seq_swap) {
    super(seq_swap.ppt);
    orderMatters = seq_swap.orderMatters;
  }

  private static /*@Prototype*/ SeqSeqStringGreaterEqual proto = new /*@Prototype*/ SeqSeqStringGreaterEqual(true);

  /** Returns the prototype invariant for SeqSeqStringGreaterEqual */
  public static /*@Prototype*/ SeqSeqStringGreaterEqual get_proto() {
    return proto;
  }

  /** Returns whether or not this invariant is enabled */
  @Override
  public boolean enabled() {
    return dkconfig_enabled;
  }

  /** Non-Equal SeqComparison is only valid on integral types */
  @Override
  public boolean instantiate_ok(VarInfo[] vis) {

    if (!valid_types(vis)) {
      return false;
    }

    VarInfo var1 = vis[0];
    VarInfo var2 = vis[1];
    ProglangType type1 = var1.type;
    ProglangType type2 = var2.type;

      // This intentonally checks dimensions(), not pseudoDimensions.
      boolean only_eq =
        (!((type1.dimensions() == 1)
           && type1.baseIsString()
           && (type2.dimensions() == 1)
           && type2.baseIsString()));
      if (only_eq) {
        return false;
      }

      // non equality comparisons don't make sense if the arrays aren't ordered
      if (!var1.aux.hasOrder() || !var2.aux.hasOrder()) {
        return false;
      }

    return true;
  }

  /** Instantiates the invariant on the specified slice */
  @Override
  protected SeqSeqStringGreaterEqual instantiate_dyn(/*>>> @Prototype SeqSeqStringGreaterEqual this,*/ PptSlice slice) {
    boolean has_order = slice.var_infos[0].aux.hasOrder() && slice.var_infos[1].aux.hasOrder();
    return new SeqSeqStringGreaterEqual(slice, has_order);
  }

  @Override
  protected Invariant resurrect_done_swapped() {

    return new SeqSeqStringLessEqual(this);
  }

  @Override
  public String repr(/*>>>@GuardSatisfied SeqSeqStringGreaterEqual this*/) {
    return "SeqSeqStringGreaterEqual" + varNames() + ": ,orderMatters=" + orderMatters
      + ",enoughSamples=" + enoughSamples()
      ;
  }

  /*@SideEffectFree*/
  @Override
  public String format_using(/*>>>@GuardSatisfied SeqSeqStringGreaterEqual this,*/ OutputFormat format) {
    // System.out.println("Calling SeqSeqStringGreaterEqual.format for: " + repr());

    if (format == OutputFormat.SIMPLIFY) {
      return format_simplify();
    }

    if (format == OutputFormat.DAIKON) {
      String name1 = var1().name_using(format);
      String name2 = var2().name_using(format);

      String lexically = (var1().aux.hasOrder() ? " (lexically)" : "");
      return name1 + " >= " + name2 + lexically;
    }

    if (format == OutputFormat.CSHARPCONTRACT) {
      String name1 = var1().csharp_collection_string();
      String name2 = var2().csharp_collection_string();

      if (var1().aux.hasOrder()) {
        String dbc = "L" + ("lexGTE").substring(1);
        return name1 + "." + dbc + "(" + name2 + ")";
      } else {
        return "\"SeqComparison.java.jpp: sequence comparison does not apply to unordered collections unimplemented\" != null)"; // interned
      }

    }

    if (format.isJavaFamily()) {
      String name1 = var1().name_using(format);
      String name2 = var2().name_using(format);

      return "daikon.Quant."
        + (var1().aux.hasOrder() ? "lexGTE" : "setEqual" )
        + "("
        + name1
        + ", "
        + name2
        + ")";

    }

    return format_unimplemented(format);
  }

  public String format_simplify(/*>>>@GuardSatisfied SeqSeqStringGreaterEqual this*/) {
    if (Invariant.dkconfig_simplify_define_predicates) {
      return format_simplify_defined();
    } else {
      return format_simplify_explicit();
    }
  }

  private String format_simplify_defined(/*>>>@GuardSatisfied SeqSeqStringGreaterEqual this*/) {
    String[] var1_name = var1().simplifyNameAndBounds();
    String[] var2_name = var2().simplifyNameAndBounds();
    if (var1_name == null || var2_name == null) {
      return String.format("%s.format_simplify_defined(%s): var1_name=%s, var2_name=%s, for %s",
                           getClass().getSimpleName(), this,
                           Arrays.toString(var1_name), Arrays.toString(var2_name), format());
    }
    return "(|lexical->=| "
      + var1_name[0] + " " + var1_name[1] + " " + var1_name[2] + " "
      + var2_name[0] + " " + var2_name[1] + " " + var2_name[2] + ")";
  }

  private String format_simplify_explicit(/*>>>@GuardSatisfied SeqSeqStringGreaterEqual this*/) {

      String classname = this.getClass().toString().substring(6);
      return "warning: method " + classname
        + ".format_simplify_explicit() needs to be implemented: " + format();

  }

  @Override
  public InvariantStatus check_modified(
      String /*@Interned*/ [] v1, String /*@Interned*/ [] v2, int count) {
    /// This does not do the right thing; I really want to avoid comparisons
    /// if one is missing, but not if one is zero-length.
    // // Don't make comparisons with empty arrays.
    // if ((v1.length == 0) || (v2.length == 0)) {
    //   return;
    // }

    int comparison = 0;
    if (orderMatters) {
      // Standard element wise comparison
       comparison = comparator.compare(v1, v2);
    } else {
      // Do a double subset comparison
      comparison = (ArraysPlume.isSubset(v1, v2) && ArraysPlume.isSubset( v2, v1)) ? 0 : -1;
    }

    if (!(comparison >= 0) ) {
      return InvariantStatus.FALSIFIED;
    }
    return InvariantStatus.NO_CHANGE;
  }

  @Override
  public InvariantStatus add_modified(String /*@Interned*/ [] v1, String /*@Interned*/ [] v2, int count) {
    if (logDetail()) {
      log("add_modified (%s, %s)", Arrays.toString(v1), Arrays.toString(v2));
    }
        return check_modified(v1, v2, count);
  }

  @Override
  protected double computeConfidence() {

    return 1 - Math.pow(.5, ppt.num_values());
  }

  /*@Pure*/
  @Override
  public boolean isSameFormula(Invariant o) {
    return true;
  }

  /*@Pure*/
  @Override
  public boolean isExclusiveFormula(Invariant o) {
    return false;
  }

  /**
   * Since this invariant can be a postProcessed equality, we have to handle isObvious especially to
   * avoid circular isObvious relations.
   */
  /*@Pure*/
  @Override
  public /*@Nullable*/ DiscardInfo isObviousStatically_SomeInEquality() {
    if (var1().equalitySet == var2().equalitySet) {
      return isObviousStatically(this.ppt.var_infos);
    } else {
      return super.isObviousStatically_SomeInEquality();
    }
  }

  /**
   * Since this invariant can be a postProcessed equality, we have to handle isObvious especially to
   * avoid circular isObvious relations.
   */
  /*@Pure*/
  @Override
  public /*@Nullable*/ DiscardInfo isObviousDynamically_SomeInEquality() {
    if (logOn()) {
      log("Considering dynamically_someInEquality");
    }
    if (var1().equalitySet == var2().equalitySet) {
      return isObviousDynamically(this.ppt.var_infos);
    } else {
      return super.isObviousDynamically_SomeInEquality();
    }
  }

  /*@Pure*/
  @Override
  public /*@Nullable*/ DiscardInfo isObviousStatically(VarInfo[] vis) {

    return super.isObviousStatically(vis);
  }

  /*@Pure*/
  @Override
  public /*@Nullable*/ DiscardInfo isObviousDynamically(VarInfo[] vis) {
    DiscardInfo super_result = super.isObviousDynamically(vis);
    if (super_result != null) {
      return super_result;
    }
    assert ppt != null;

    return null;
  }

  @Override
  public void repCheck() {
    super.repCheck();
    /*
      This code is no longer needed now that the can_be_x's are gone
    if (!(this.can_be_eq || this.can_be_lt || this.can_be_gt)
        && ppt.num_samples() != 0) {
      System.err.println(this.repr());
      System.err.println(this.ppt.num_samples());
      throw new Error();
    }
    */
  }

  /*@Pure*/
  public boolean isEqual() {

    return false;
  }

  // Look up a previously instantiated invariant.
  public static /*@Nullable*/ SeqSeqStringGreaterEqual find(PptSlice ppt) {
    assert ppt.arity() == 2;
    for (Invariant inv : ppt.invs) {
      if (inv instanceof SeqSeqStringGreaterEqual) {
        return (SeqSeqStringGreaterEqual) inv;
      }
    }
    return null;
  }

  /** Returns a list of non-instantiating suppressions for this invariant. */
  /*@Pure*/
  @Override
  public /*@Nullable*/ NISuppressionSet get_ni_suppressions() {
    return suppressions;
  }

  /** Definition of this invariant (the suppressee) */
  private static NISuppressee suppressee = new NISuppressee(SeqSeqStringGreaterEqual.class, 2);

    private static /*@Nullable*/ NISuppressionSet suppressions = null;

}
