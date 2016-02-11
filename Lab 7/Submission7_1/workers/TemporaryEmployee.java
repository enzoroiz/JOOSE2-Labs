package workers;

import java.util.Calendar;

/**
 * Abstract class that represents a temporary employee.
 * 
 * @author enzoroiz
 */
public class TemporaryEmployee extends HourlyEmployee {
	/**
	 * Instance Fields
	 */
	private int employeeID;

	/**
	 * Constructor
	 * 
	 * @param givenName
	 * @param familyName
	 * @param startingDate
	 * @param hourlyRate
	 * @param contractedHoursPerMonth
	 */
	public TemporaryEmployee(String givenName, String familyName,
			Calendar startingDate, double hourlyRate,
			int contractedHoursPerMonth) {
		this.employeeID = newID();
		this.familyName = familyName;
		this.givenName = givenName;
		this.startingDate = startingDate;
		this.hourlyRate = hourlyRate;
		this.contractedHoursPerMonth = contractedHoursPerMonth;
	}

	/**
	 * Calculate the monthly wage of the employee
	 */
	@Override
	public double calculateMonthlyWage() {
		return hourlyRate * hoursWorked;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.givenName);
		stringBuilder.append(" ");
		stringBuilder.append(this.familyName);
		stringBuilder.append(" (" + Employee.TEMPORARY);
		stringBuilder.append(", #" + this.employeeID);
		stringBuilder.append(", since " + this.startingDate.get(Calendar.YEAR));
		stringBuilder.append(") ");

		return stringBuilder.toString();
	}

}
