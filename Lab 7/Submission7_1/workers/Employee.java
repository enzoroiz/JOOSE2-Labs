package workers;

/**
 * Abstract class that represents an employee.
 * 
 * @author enzoroiz
 */
public abstract class Employee implements Waged {
	/**
	 * Instance Fields
	 */
	protected String familyName;
	protected String givenName;
	protected java.util.Calendar startingDate;

	/**
	 * Final fields
	 */
	public static final String SALARIED = "salaried";
	public static final String PERMANENT = "permanent";
	public static final String TEMPORARY = "temporary";

	/**
	 * Static fields
	 */
	protected static int employeeID = 0;

	/**
	 * Method
	 */

	/**
	 * @return the next ID to an employee
	 */
	protected int newID() {
		employeeID++;
		return employeeID;
	}
}
