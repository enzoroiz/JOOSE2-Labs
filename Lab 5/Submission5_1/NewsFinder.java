import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Reads the news from a supplied URL and caches the content, ready for
 * searches. This class should be completed by the student.
 * 
 * @author enzoroiz
 */
public class NewsFinder {

	//Instance fields
	private String url;
	private String content;

	//Constructor
	public NewsFinder(String url) {
		this.url = url;
		this.content = "";
		try {
			BufferedReader r = new BufferedReader(new InputStreamReader(
					new URL(this.url).openStream()));
			while (r.ready()) {
				this.content = content + r.readLine();
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//Verify if is there any new about certain country
	public boolean isInNews(Object obj) {
		if (obj instanceof Country) {
			Country country = (Country) obj;
			return content.contains(country.getName());
		}

		return this.content.contains((String) obj);
	}

}
