/**
 * Assessed submission for Java lab 3 This class represents a simple bank
 * account
 * 
 * @author jsinger
 */
public class BankAccount {

	// instance fields

	/**
	 * each BankAccount instance should have a unique account number
	 */
	private int accountNumber;

	/**
	 * current balance in pounds
	 */
	private double currentBalance;

	/**
	 * overdraft limit, a positive value, which represents the negative limit of
	 * currentBalance (how low it can get) i.e. it should never be possible for
	 * (0-currentBalance) > overdraftLimit
	 */
	private double overdraftLimit;

	/**
	 * textual identifier for account
	 */
	private String accountHolder;

	// accessor methods

	public int getAccountNumber() {
		return this.accountNumber;
	}

	public double getCurrentBalance() {
		return this.currentBalance;
	}

	public double getOverdraftLimit() {
		return this.overdraftLimit;
	}

	public boolean setOverdraftLimit(double overdraftLimit) {
		// what if overdraftLimit param is -ve?
		if (overdraftLimit < (0 - this.currentBalance))
			return false;
		this.overdraftLimit = overdraftLimit;
		return true;
	}

	public String getAccountHolder() {
		return this.accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	// static field, for assigning unique accountNumbers
	private static int nextId = 0;

	// constructors
	public BankAccount() {
		this("", 0.0);
	}

	public BankAccount(String accountHolder, double overdraftLimit) {
		this.accountNumber = nextId++;
		this.accountHolder = accountHolder;
		this.currentBalance = 0.0;
		this.overdraftLimit = overdraftLimit;
	}

	// other methods
	/**
	 * @arg amount amount to deposit
	 * @throws exception
	 *             if amount is negative
	 */
	public void deposit(double amount) throws Exception {
		if (amount < 0)
			throw new Exception();
		this.currentBalance += amount;
	}

	/**
	 * @arg amount amount to withdraw
	 * @throws exception
	 *             if amount is negative
	 * @return true if withdrawal succeeds, false otherwise Note that withdraw
	 *         will fail if the amount would cause the currentBalance to exceed
	 *         the overdraftLimit
	 */
	public boolean withdraw(double amount) throws Exception {
		if (amount < 0)
			throw new Exception();
		if (0 - (this.currentBalance - amount) > this.overdraftLimit)
			return false;
		this.currentBalance -= amount;
		return true;
	}

	@Override
	public String toString() {
		String s = new String("ACCOUNT " + this.getAccountNumber() + " ("
				+ this.getAccountHolder() + ")" + " balance: "
				+ this.getCurrentBalanceAsFormattedString()
				+ " overdraft limit: " + this.getOverdraftLimit());
		return s;
	}

	public String getCurrentBalanceAsFormattedString() {
		double balance = this.getCurrentBalance();
		String POUND = java.util.Currency.getInstance("GBP").getSymbol(
				java.util.Locale.UK);
		String s;
		if (balance >= 0) {
			s = String.format(POUND + "%.2f", balance);
		} else {
			s = String.format("-" + POUND + "%.2f", (0.0 - balance));
		}
		return s;
	}
}
