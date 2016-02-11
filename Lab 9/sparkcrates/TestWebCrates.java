package sparkcrates;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import spark.Spark;
import spark.utils.IOUtils;

/**
 * 
 * @author jsinger
 * @author enzoroiz
 *
 */
public class TestWebCrates {

	private static final int PORT = 4567;

	@BeforeClass
	public static void beforeClass() {
		// initialize spark framework
		// running WebCrates system
		WebCrates.main(null);
		try {
			// wait 1s for spark framework to boot up...
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.err.println("interrupted!");
		}
	}

	@AfterClass
	public static void afterClass() {
		// shutdown Spark framework properly
		Spark.stop();
	}

	/**
	 * A simple example test - follow this structure for your own tests... This
	 * one checks http://localhost:4567/showcrates returns a non-empty response
	 */
	@Test
	public void testShowCrates() {
		UrlResponse response = doMethod("GET", "/showcrates", null);
		assertEquals(200, response.status);
		assertTrue("empty response body", response.body.length() > 0);
	}

	/**
	 * Test to verify /showcrates when crates List is empty
	 */
	@Test
	public void testEmptyShowCrates() {
		WebCrates.crates.clear();
		UrlResponse response = doMethod("GET", "/showcrates", null);
		assertTrue("Empty crates/No crates filled",
				response.body.contains("There are no crates to show"));
	}

	/**
	 * Test that fill crates and verify the response given by /showcrates
	 */
	@Test
	public void testFilledShowCrates() {
		WebCrates.crates.clear();
		WebCrates.bestFit.addAmount(30);
		WebCrates.bestFit.addAmount(40);
		WebCrates.worstFit.addAmount(50);
		WebCrates.bestFit.addAmount(30);
		WebCrates.firstFit.addAmount(30);
		UrlResponse response = doMethod("GET", "/showcrates", null);
		assertTrue("Crates filled",
				response.body.contains("Crate 1: 0% available."));
		assertTrue("Crates filled",
				response.body.contains("Crate 2: 20% available."));
	}

	/**
	 * Test that verify crates' addition through /add
	 */
	@Test
	public void testAddCrates() {
		WebCrates.crates.clear();
		doMethod("GET", "/add/50", null);
		assertTrue("Creating new crates", WebCrates.crates.get(0)
				.getTotalCapacity() == 50);
		doMethod("GET", "/add/30", null);
		assertTrue("Creating new crates", WebCrates.crates.get(1)
				.getTotalCapacity() == 30);
	}

	/**
	 * Test to verify best fit method using /best/(amount), crates List and
	 * /showcrates response
	 */
	@Test
	public void testBestFitMethods() {
		WebCrates.crates.clear();
		doMethod("GET", "/fill/best/80", null);
		doMethod("GET", "/fill/best/90", null);
		doMethod("GET", "/fill/best/95", null);
		UrlResponse responseFill = doMethod("GET", "/fill/best/10", null);
		UrlResponse response = doMethod("GET", "/showcrates", null);
		assertTrue(
				"Crates filled",
				responseFill.body
						.contains("10 units added using the best fit algorithm!"));
		assertTrue("Crates filled", WebCrates.crates.get(1)
				.getAvailableCapacity() == 0);
		assertTrue("Crates filled",
				response.body.contains("Crate 2: 0% available."));
	}

	/**
	 * Test to verify worst fit method using /worst/(amount), crates List and
	 * /showcrates response
	 */
	@Test
	public void testWorstFitMethods() {
		WebCrates.crates.clear();
		doMethod("GET", "/fill/worst/95", null);
		doMethod("GET", "/fill/worst/80", null);
		doMethod("GET", "/fill/worst/90", null);
		UrlResponse responseFill = doMethod("GET", "/fill/worst/10", null);
		UrlResponse response = doMethod("GET", "/showcrates", null);
		assertTrue(
				"Crates filled",
				responseFill.body
						.contains("10 units added using the worst fit algorithm!"));
		assertTrue("Crates filled", WebCrates.crates.get(1)
				.getAvailableCapacity() == 10);
		assertTrue("Crates filled",
				response.body.contains("Crate 2: 10% available."));
	}

	/**
	 * Test to verify first fit method using /first/(amount), crates List and
	 * /showcrates response
	 */
	@Test
	public void testFirstFitMethods() {
		WebCrates.crates.clear();
		doMethod("GET", "/fill/first/95", null);
		doMethod("GET", "/fill/first/80", null);
		doMethod("GET", "/fill/first/90", null);
		UrlResponse responseFill = doMethod("GET", "/fill/first/10", null);
		UrlResponse response = doMethod("GET", "/showcrates", null);
		assertTrue(
				"Crates filled",
				responseFill.body
						.contains("10 units added using the first fit algorithm!"));
		assertTrue("Crates filled", WebCrates.crates.get(1)
				.getAvailableCapacity() == 10);
		assertTrue("Crates filled",
				response.body.contains("Crate 2: 10% available."));
	}

	/**
	 * Helper methods/classes follow...
	 */
	private static UrlResponse doMethod(String requestMethod, String path,
			String body) {
		UrlResponse response = new UrlResponse();
		try {
			getResponse(requestMethod, path, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	private static void getResponse(String requestMethod, String path,
			UrlResponse response) throws MalformedURLException, IOException,
			ProtocolException {
		URL url = new URL("http://0.0.0.0:" + PORT + path);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod(requestMethod);
		connection.connect();
		String res = IOUtils.toString(connection.getInputStream());
		response.body = res;
		response.status = connection.getResponseCode();
		response.headers = connection.getHeaderFields();
	}

	private static class UrlResponse {
		public Map<String, List<String>> headers;
		private String body;
		private int status;
	}

}
