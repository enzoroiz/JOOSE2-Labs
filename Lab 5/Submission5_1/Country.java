import java.util.ArrayList;
import java.util.Locale;

/**
 * Represents a country. This class should be completed by the student.
 * 
 * @author enzoroiz
 */
public class Country {
	// Instance fields
	private String name;

	//Constructor
	public Country(String name) {
		this.name = name;
	}

	//Method to return all available countries
	public static ArrayList<Country> getCountries() {
		ArrayList<Country> countries = new ArrayList<Country>();
		Locale[] availableLocales = Locale.getAvailableLocales();

		for (int i = 0; i < availableLocales.length; i++) {
			String currentCountry = availableLocales[i].getDisplayCountry();
			if (!currentCountry.isEmpty()
					&& !countries.contains(currentCountry)) {
				Country country = new Country(currentCountry);
				countries.add(country);
			}
		}

		return countries;
	}

	//Gettter
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		return this.name;
	}

	@Override
	public boolean equals(Object obj) {
		Country country = (Country) obj;
		return this.name == country.name;
	}

}
