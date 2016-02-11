import java.util.Scanner;

/**
 * program to compute the day of the week for a given date supplied via console
 * input
 */
public class DayOfTheWeek {

	final static int START_YEAR = 1900;

	public static void main(String[] args) {

		Scanner input = new Scanner(System.in);

		System.out.println("Type the date:");

		// Get the date
		int day = input.nextInt();
		int month = input.nextInt();
		int year = input.nextInt();

		// Verify leap years
		boolean isLeap = (year % 4 == 0) && (month > 2);
		int leapYears = ((year - 1) - START_YEAR) / 4;

		// Add a day if the year is a leap year
		if (isLeap) {
			leapYears += 1;
		}

		int passedDaysInYear = 0;
		int daysInMonth;
		int passedYears = year - START_YEAR;

		// Count how many days has passed since 1 / 1 / 1900
		for (int i = 1; i <= 12; i++) {
			if (month == i) {
				passedDaysInYear += day;
				break;
			}

			switch (i) {
			case 4:
			case 6:
			case 9:
			case 11:
				daysInMonth = 30;
				break;
			case 2:
				daysInMonth = 28;
				break;
			default:
				daysInMonth = 31;
				break;
			}

			passedDaysInYear += daysInMonth;

		}

		// Sum all days passed
		int totalDays = (passedYears * 365) + leapYears + passedDaysInYear;
		String dayOfTheWeek = "";

		// Verify which is the day of week of the date
		switch ((totalDays - 1) % 7) {
		case 0:
			dayOfTheWeek = "Monday";
			break;
		case 1:
			dayOfTheWeek = "Tuesday";
			break;
		case 2:
			dayOfTheWeek = "Wednesday";
			break;
		case 3:
			dayOfTheWeek = "Thursday";
			break;
		case 4:
			dayOfTheWeek = "Friday";
			break;
		case 5:
			dayOfTheWeek = "Saturday";
			break;
		case 6:
			dayOfTheWeek = "Sunday";
			break;
		default:
			break;
		}

		System.out.println(dayOfTheWeek);

		input.close();
	}
}
