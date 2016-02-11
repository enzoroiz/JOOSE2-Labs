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
 * for Java lab 1, day of the week program
 */
public class DayOfTheWeekTest {
    
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
    public void test1Jan1900() throws Exception {
	String testInput = "1 1 1900\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	DayOfTheWeek.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be Monday)", testOutput.toLowerCase().contains("monday"));
    }


    @Test
    public void test29Feb2004() throws Exception {
	String testInput = "29 2 2004\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	DayOfTheWeek.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be Sunday)", testOutput.toLowerCase().contains("sunday"));
    }


    @Test
    public void test31Dec2000() throws Exception {
	String testInput = "31 12 2000\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	DayOfTheWeek.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be Sunday)", testOutput.toLowerCase().contains("sunday"));
    }


    @Test
    public void test13Jun1980() throws Exception {
	String testInput = "13 6 1980\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	DayOfTheWeek.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be Friday)", testOutput.toLowerCase().contains("friday"));
    }


    @Test
    public void test3Oct2014() throws Exception {
	String testInput = "3 10 2014\n";
	ByteArrayOutputStream baos = new ByteArrayOutputStream();
	PrintStream ps = new PrintStream(baos);
	System.setIn(new ByteArrayInputStream(testInput.getBytes()));
	System.setOut(ps);
	DayOfTheWeek.main(null);
	System.out.flush();
	String testOutput = baos.toString();
	assertTrue("incorrect result (should be Friday)", testOutput.toLowerCase().contains("friday"));
    }



}