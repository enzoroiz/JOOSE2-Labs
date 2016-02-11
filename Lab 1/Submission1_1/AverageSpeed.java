import java.util.Scanner;

/**
 * Program to compute average speed of a vehicle given times for two checkpoints
 * one mile apart
 */
public class AverageSpeed {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		/* Insert your code here */
		System.out.println("Type the first time:");

		// Get the first time
		int firstHours = input.nextInt();
		int firstMinutes = input.nextInt();
		int firstSeconds = input.nextInt();

		// Convert to seconds
		firstHours = firstHours * 60 * 60;
		firstMinutes = firstMinutes * 60;

		// First time in seconds
		int firstTotalSeconds = firstHours + firstMinutes + firstSeconds;

		System.out.println("Type the final time:");

		// Get the final time
		int finalHours = input.nextInt();
		int finalMinutes = input.nextInt();
		int finalSeconds = input.nextInt();

		// Convert to seconds
		finalHours = finalHours * 60 * 60;
		finalMinutes = finalMinutes * 60;

		// Final time in seconds
		int finalTotalSeconds = finalHours + finalMinutes + finalSeconds;

		// Total time in seconds
		double timeInSeconds = (finalTotalSeconds - firstTotalSeconds);

		// If midnight falls
		if (timeInSeconds <= 0) {
			final int dayInSeconds = 24 * 60 * 60;
			System.out.println(dayInSeconds);
			timeInSeconds = dayInSeconds
					- (firstTotalSeconds - finalTotalSeconds);
		}

		double mph = 1 / (timeInSeconds / (60 * 60));
		System.out.println("The average speed is: " + mph);

		input.close();

	}
}