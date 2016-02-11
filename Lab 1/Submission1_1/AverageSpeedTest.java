import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Test the student's submission
 * for Java lab 1, average speed program
 */
public class AverageSpeedTest {
    
    InputStream stdin;
    PrintStream stdout;

    @Before
    public void setup() {
	stdin = System.in;
	stdout = System.out;
    }

    @After
    public void teardown() {
	System.setIn(stdin);
	System.setOut(stdout);
    }

    @Test
    public void test60Mph() throws Exception {
	String testInput = "17 23 56 17 24 56\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	AverageSpeed.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be 60 mph)", testOutput.contains("60")); 
    }

    @Test
    public void test30Mph() throws Exception {
	String testInput = "23 59 00 0 01 00\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	AverageSpeed.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result after midnight (should be 30 mph)", testOutput.contains("30")); 
    }

    @Test
    public void test1Mph() throws Exception {
	String testInput = "1 23 45 2 23 45\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	AverageSpeed.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be 1 mph)", testOutput.contains("1")); 
    }


}