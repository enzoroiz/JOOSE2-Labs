package workers;

import java.util.Calendar;

/**
 * Class that represents a permanent employee.
 * 
 * @author enzoroiz
 */
public class PermanentEmployee extends HourlyEmployee {
	/**
	 * Instance Fields
	 */
	private int employeeID;

	/**
	 * Constructor
	 * 
	 * @param familyName
	 * @param givenName
	 * @param startingDate
	 * @param hourlyRate
	 * @param contractedHoursPerMonth
	 */
	public PermanentEmployee(String familyName, String givenName,
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
		if (hoursWorked > contractedHoursPerMonth) {
			return hourlyRate
					* contractedHoursPerMonth
					+ (1.5 * hourlyRate * (hoursWorked - contractedHoursPerMonth));
		} else {
			return hourlyRate * contractedHoursPerMonth;
		}
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.givenName);
		stringBuilder.append(" ");
		stringBuilder.append(this.familyName);
		stringBuilder.append(" (" + Employee.PERMANENT);
		stringBuilder.append(", #" + this.employeeID);
		stringBuilder.append(", since " + this.startingDate.get(Calendar.YEAR));
		stringBuilder.append(") ");

		return stringBuilder.toString();
	}

}
