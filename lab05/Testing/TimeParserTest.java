import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TimeParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void parseTimeToSecondsStatementCoverage() {
        assertEquals(48225, TimeParser.parseTimeToSeconds("1:23:45 pm"));
    }

    @Test()
    public void parseTimeToSecondsBranchCoverage1() {
        try{
            int x = TimeParser.parseTimeToSeconds("1 23 45 pm");
        }catch(Exception e){
            // e.getClass().getCanonicalName()
            assertEquals("Unrecognized time format",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsBranchCoverage2() {
        try{
            int x = TimeParser.parseTimeToSeconds("1 23:45 pm");
        }catch(Exception e){
            assertEquals("Unrecognized time format",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsBranchCoverage3() {
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:23:45 am"));
    }

    @Test()
    public void parseTimeToSecondsBranchCoverage4() {
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:23:45"));
    }

    @Test()
    public void parseTimeToSecondsBranchCoverage5() {
        try{
            int x = TimeParser.parseTimeToSeconds("99:23:45 pm");
        }catch(Exception e){
            assertEquals("Unacceptable time specified",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsPathCoverage1() {
        try{
            int x = TimeParser.parseTimeToSeconds("1 23 45 pm");
        }catch(Exception e){
            // e.getClass().getCanonicalName()
            assertEquals("Unrecognized time format",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsPathCoverage2() {
        try{
            int x = TimeParser.parseTimeToSeconds("1 23:45 pm");
        }catch(Exception e){
            assertEquals("Unrecognized time format",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsPathCoverage3() {
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:23:45 am"));
    }

    @Test()
    public void parseTimeToSecondsPathCoverage4() {
        assertEquals(48225, TimeParser.parseTimeToSeconds("1:23:45 pm"));
    }

    @Test()
    public void parseTimeToSecondsPathCoverage5() {
        assertEquals(5025, TimeParser.parseTimeToSeconds("1:23:45"));
    }

    @Test()
    public void parseTimeToSecondsPathCoverage6() {
        try{
            int x = TimeParser.parseTimeToSeconds("99:23:45 am");
        }catch(Exception e){
            assertEquals("Unacceptable time specified",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsPathCoverage7() {
        try{
            int x = TimeParser.parseTimeToSeconds("99:23:45 pm");
        }catch(Exception e){
            assertEquals("Unacceptable time specified",e.getMessage());
        }
    }

    @Test()
    public void parseTimeToSecondsPathCoverage8() {
        try{
            int x = TimeParser.parseTimeToSeconds("99:23:45");
        }catch(Exception e){
            assertEquals("Unacceptable time specified",e.getMessage());
        }
    }
}