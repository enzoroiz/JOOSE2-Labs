package workers;

/**
 * Abstract class that represents an hourly employee.
 * 
 * @author enzoroiz
 */
public abstract class HourlyEmployee extends Employee {
	/**
	 * Instance Fields
	 */
	protected double hourlyRate;
	protected int contractedHoursPerMonth;
	protected int hoursWorked;

	/**
	 * Set the hours worked in the month by an employee
	 * 
	 * @param hoursWorked
	 */
	public void setHoursThisMonth(int hoursWorked) {
		this.hoursWorked = hoursWorked;
	}
}
