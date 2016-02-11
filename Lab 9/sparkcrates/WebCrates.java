package sparkcrates;

import java.util.ArrayList;
import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import fillingcrates.BestFit;
import fillingcrates.Crate;
import fillingcrates.FillableContainer;
import fillingcrates.FirstFit;
import fillingcrates.WorstFit;

/**
 * Submission for Java labs 9+10 with details filled by
 * 
 * @author enzoroiz
 */
public class WebCrates {
	public static final String CSS = "progress[value] {" + "width: 250px;"
			+ "	height: 30px;}";

	/**
	 * the internal state of the web application - the list of crates that we
	 * are going to organize using a predefined fitting algorithm. NOTE - this
	 * List corresponds to the 'model' in an MVC style design pattern.
	 */
	public static List<FillableContainer> crates;
	public static FirstFit firstFit;
	public static BestFit bestFit;
	public static WorstFit worstFit;

	static {
		crates = new ArrayList<FillableContainer>();
		firstFit = new FirstFit(crates);
		bestFit = new BestFit(crates);
		worstFit = new WorstFit(crates);
	}

	public static void main(String[] args) {
		// Hello World
		Spark.get("/hello", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "Hello World!";
			}
		});

		// Showcrates
		Spark.get("showcrates", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return showCrates(crates);
			}
		});

		// Add crates informing the amount
		Spark.get("/add/:capacity", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				int capacity = Integer.valueOf(request.params(":capacity"));
				crates.add(new Crate(capacity));
				return "Crate of size " + capacity + " was added.";
			}
		});

		// Filling crates using selected algorithm
		Spark.get("/fill/:algorithm/:amount", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				String algorithm = request.params(":algorithm").toLowerCase();
				int amount = Integer.valueOf(request.params(":amount"));

				return addContent(algorithm, amount);
			}

		});
	}

	/**
	 * Function to add the amount using the selected algorithm
	 * 
	 * @param algorithm
	 *            to use
	 * @param amount
	 *            to add
	 * @return message informing the amount and used algorithm
	 */
	private static String addContent(final String algorithm, final int amount) {
		switch (algorithm) {
		case "first":
			firstFit.addAmount(amount);
			break;
		case "best":
			bestFit.addAmount(amount);
			break;
		case "worst":
			worstFit.addAmount(amount);
			break;
		default:
			break;
		}

		return amount + " units added using the " + algorithm
				+ " fit algorithm!";
	}

	/**
	 * Function to show how crates are filled
	 * 
	 * @param crates
	 *            list
	 * @return page content
	 */
	private static String showCrates(List<FillableContainer> crates) {
		int i = 0;
		StringBuilder sb = new StringBuilder();
		sb.append("<style>" + CSS + "</style>");

		if (crates.isEmpty()) {
			return "<h1><i>There are no crates to show. Add them using '/fill/(first) | (best) | (worst)/(amount to add)'</i></h1>"
					+ "<br>Example: /fill/best/20";
		}

		sb.append("<h1><i>The crates have the following design:</i></h1>");

		for (FillableContainer fillableContainer : crates) {
			i++;
			sb.append("<br><b><i>Crate "
					+ i
					+ ": "
					+ String.valueOf((fillableContainer.getAvailableCapacity() * 100)
							/ fillableContainer.getTotalCapacity())
					+ "% available.</b></i><br>");
			sb.append("<progress value=\""
					+ fillableContainer.getUsedCapacity() + "\" max=\""
					+ fillableContainer.getTotalCapacity() + "\"></progress>");
		}

		return sb.toString();
	}
}
