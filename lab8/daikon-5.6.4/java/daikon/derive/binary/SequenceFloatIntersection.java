// ***** This file is automatically generated from SequencesIntersection.java.jpp

package daikon.derive.binary;

import daikon.*;
import daikon.derive.*;
import java.util.logging.Logger;
import org.plumelib.util.ArraysPlume;
import org.plumelib.util.Intern;

/*>>>
import org.checkerframework.checker.interning.qual.*;
import org.checkerframework.dataflow.qual.*;
*/

/** Intersection between two comparable sequences. */
public final class SequenceFloatIntersection extends BinaryDerivation {
  public static final Logger debug =
    Logger.getLogger("daikon.derive.binary.SequenceFloatIntersection");

  // We are Serializable, so we specify a version to allow changes to
  // method signatures without breaking serialization.  If you add or
  // remove fields, you should change this number to the current date.
  static final long serialVersionUID = 20020122L;

  // Variables starting with dkconfig_ should only be set via the
  // daikon.config.Configuration interface.
  /** Boolean. True iff SequenceFloatIntersection derived variables should be generated. */
  public static boolean dkconfig_enabled = false;

  public SequenceFloatIntersection(VarInfo vi1, VarInfo vi2) {
    super(vi1, vi2);
  }

  @Override
  public ValueAndModified computeValueAndModifiedImpl(ValueTuple full_vt) {
    debug.fine("Computing value and modified");

    int mod1 = base1.getModified(full_vt);
    if (mod1 == ValueTuple.MISSING_NONSENSICAL) {
      return ValueAndModified.MISSING_NONSENSICAL;
    }
    int mod2 = base2.getModified(full_vt);
    if (mod2 == ValueTuple.MISSING_NONSENSICAL) {
      return ValueAndModified.MISSING_NONSENSICAL;
    }
    Object val1 = base1.getValue(full_vt);
    if (val1 == null) {
      return ValueAndModified.MISSING_NONSENSICAL;
    }
    double[] val1_array = (double[]) val1;
    Object val2 = base2.getValue(full_vt);
    if (val2 == null) {
      return ValueAndModified.MISSING_NONSENSICAL;
    }
    double[] val2_array = (double[]) val2;

    double[] tmp = new double[val1_array.length + val2_array.length];
    int size = 0;
    for (int i = 0; i < val1_array.length; i++) {
      double v = val1_array[i];
      if ((ArraysPlume.indexOf(val2_array, v) != -1)
          && (size == 0 || (ArraysPlume.indexOf(ArraysPlume.subarray(tmp, 0, size), v) == -1))) {
        tmp[size++] = v;
      }
    }

    double[] intersect = ArraysPlume.subarray(tmp, 0, size);
    intersect = Intern.intern(intersect);

    int mod =
        (((mod1 == ValueTuple.UNMODIFIED) && (mod2 == ValueTuple.UNMODIFIED))
               ? ValueTuple.UNMODIFIED
               : ValueTuple.MODIFIED);
    return new ValueAndModified(intersect, mod);
  }

  @Override
  protected VarInfo makeVarInfo() {
    debug.fine("Computing varInfo");
    return VarInfo.make_function("intersection", base1, base2);
  }

  /*@Pure*/
  @Override
  public boolean isSameFormula(Derivation other) {
    return (other instanceof SequenceFloatIntersection);
  }

  /** Returns the ESC name for sequence subsequence */
  /*@SideEffectFree*/
  @Override
  public String esc_name(String index) {
    return String.format("intersection[%s,%s]", base1.esc_name(), base2.esc_name());
  }
}
