package tarantula;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 * This class takes reads a compact json file produced by tacoco and
 * retrieves the program name, first line, test count, coverable lines, and coverage matrix.
 * Note that this class can only work with the compact json file produced by tacoco.
 * The matrixes M and C correspond to the fields of the same name in TarantulaSuspiciousnessCalculation.
 **/

public class CovMatrixReader {
    private File jsonFile;      // Json file to be read from
    private boolean[][] M;      // [test][stmt] matrix for each test and the lines it covers 
    private boolean[] C;        // [stmt] matrix of coverable lines from json file
    private JSONArray sources;  // key in the json file
    private Long testCount;     // number of tests, as stated by the json file
    private Long firstLine;     // first line of the program, as stated by the json file
    private String fullName;    // name of the program, as stated by the json file
    
    /** Initializes a covMatrixReader to read File f **/
    public CovMatrixReader(File f){
        // create a new JSON parser
        JSONParser parser = new JSONParser();
         
        try{
            // create an object that takes the information from the cov-matrix
            Object obj = parser.parse(new FileReader(f));
             
            // cast the obj into a JSONObject
            JSONObject jsonObject = (JSONObject) obj;
             
            // store the number of test cases
            testCount = (Long) jsonObject.get("testCount");
             
            // sources stores the value from the key 'sources' in the JSONObject
            sources = (JSONArray) jsonObject.get("sources");
             
            // sources is an array of size 1
            JSONObject source = (JSONObject) sources.get(0);
             
            // source will contain the program name, first line, and last line
            JSONObject lineInfo = (JSONObject)source.get("source");
            fullName = (String) lineInfo.get("fullName");
            firstLine = (Long) lineInfo.get("firstLine");
             
            // create a JSONArray for the testStmtMatrix
            JSONArray JSONStmtMatrix = (JSONArray) source.get("testStmtMatrix");
            int tests = JSONStmtMatrix.size();
 
            M = new boolean[tests][];
            // create the Matrix M by assigned each index another array
            for(int i = 0; i < tests; i++) {
                JSONArray stmts = (JSONArray) JSONStmtMatrix.get(i);
                M[i] = createBoolArray(stmts);
            }
            
            //create Matrix C
            JSONArray JSONCovMatrix = (JSONArray) source.get("coverableLines");
            C = createBoolArray(JSONCovMatrix);
             
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
     
    /** Return a double boolean array where the first dimension is indexed by test number
    and the second dimension is indexed by line number**/
    public boolean[][] getM(){
        return M;
    }
     
    /** Return a boolean array indexed by line number and true=line is coverable/not a blank line or comment**/
    public boolean[] getC(){
        return C;
    }
     
    /** Return a Long representing the total tests in the test program**/
    public Long getTestCount(){
        return testCount;
    }
    
    /** Return a Long representing the line number of the first coverable line**/
    public Long getFirstLine(){
        return firstLine;
    }
    
    /** Return a String representing the name of the test program**/
    public String getFullName(){
        return fullName;
    }
     
    /** Private method to create a boolean[] from a JSONArray**/
    private boolean[] createBoolArray(JSONArray array){
        boolean[] result = new boolean[array.size()];
        for(int i = 0; i < array.size(); i++) {
            Boolean bool = (Boolean) array.get(i);
            result[i] = bool;
        }
        return result;
    }
}
