import java.util.*;
import soot.*;
import soot.toolkits.graph.*;
import soot.toolkits.scalar.BackwardFlowAnalysis;
import soot.toolkits.scalar.FlowSet;


public class AnalysisTransformer extends SceneTransformer 
{

	@Override
	protected void internalTransform(String arg0, Map arg1) {

		// Get Main Method
		SootMethod sMethod = Scene.v().getMainMethod();

		// Create graph based on the method
		UnitGraph graph = new BriefUnitGraph(sMethod.getActiveBody());

		// Perform LV Analysis on the Graph
		LiveVariableAnalysis analysis = new LiveVariableAnalysis(graph);

		// Print live variables at the entry and exit of each node
		
		Iterator<Unit> unitIt = graph.iterator();

		while (unitIt.hasNext()) {
			Unit s = unitIt.next();

			System.out.print(s);
			
			int d = 40 - s.toString().length();
			while (d > 0) {
				System.out.print(".");
				d--;
			}
			
			FlowSet<Local> set = analysis.getFlowBefore(s);

			System.out.print("\t[entry: ");
			for (Local local: set) {
				System.out.print(local+" ");
			}

			set = analysis.getFlowAfter(s);
			
			System.out.print("]\t[exit: ");
			for (Local local: set) {
				System.out.print(local+" ");
			}
			System.out.println("]");
		}

	}
}