import soot.Main;
import soot.PackManager;
import soot.Transform;

public class RunDataFlowAnalysis
{
	public static void main(String[] args) {

		if (args.length == 0) {
			System.out.println("Usage: java RunDataFlowAnalysis class_to_analyse");
			System.exit(1);
		} else {
			System.out.println("Analyzing class: "+args[0]);
		}

		String mainClass = args[0];

		// You may have to update the class Path based on your OS and Java version 
		/*** *** YOU SHOULD EDIT THIS BEFORE RUNNING *** ***/
		String classPath = "."; 			//change to appropriate path to the test class
															// if needed add path to rt.jar (or classes.jar)


		//Set up arguments for Soot
		String[] sootArgs = {
			"-cp", classPath, "-pp", 	// sets the class path for Soot
			"-w", 						// Whole program analysis, necessary for using Transformer
			"-src-prec", "java",		// Specify type of source file
			"-main-class", mainClass,	// Specify the main class 
			"-f", "J", 					// Specify type of output file
			mainClass 
		};

		// Create transformer for analysis
		AnalysisTransformer analysisTransformer = new AnalysisTransformer();

		// Add transformer to appropriate Pack in PackManager. PackManager will run all Packs when main function of Soot is called
		PackManager.v().getPack("wjtp").add(new Transform("wjtp.dfa", analysisTransformer));

		// Call main function with arguments
		Main.main(sootArgs);

	}
}
