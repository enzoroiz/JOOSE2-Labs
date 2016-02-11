package workers;

import java.util.Calendar;

/**
 * Class that represents a salaried employee.
 * 
 * @author enzoroiz
 */
public class SalariedEmployee extends Employee {
	/**
	 * Instance Fields
	 */
	private int annualSalary;
	private int employeeID;

	/**
	 * Constructor
	 * 
	 * @param givenName
	 * @param familyName
	 * @param startingDate
	 * @param annualSalary
	 */
	public SalariedEmployee(String givenName, String familyName,
			Calendar startingDate, int annualSalary) {
		this.employeeID = newID();
		this.familyName = familyName;
		this.givenName = givenName;
		this.startingDate = startingDate;
		this.annualSalary = annualSalary;
	}

	/**
	 * Calculate the monthly wage of the employee
	 */
	@Override
	public double calculateMonthlyWage() {
		return annualSalary / 12.0;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(this.givenName);
		stringBuilder.append(" ");
		stringBuilder.append(this.familyName);
		stringBuilder.append(" (" + Employee.SALARIED);
		stringBuilder.append(", #" + this.employeeID);
		stringBuilder.append(", since " + this.startingDate.get(Calendar.YEAR));
		stringBuilder.append(") ");

		return stringBuilder.toString();
	}

}
