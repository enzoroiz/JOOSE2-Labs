/**
 * @author Enzo Roiz 2161561R.student.gla.ac.uk
 */
public class BankAccount {

	/**
	 * Each BankAccount instance should have a unique account number
	 */
	// Attributes
	private int accountNumber;
	private String accountHolder;
	private double currentBalance;
	private double overdraftLimit;

	// Static Fields -> Class Fields - Used to control simple account numbers
	private static int nextId = 1;

	// Acessor methods
	public int getAccountNumber() {
		return this.accountNumber;
	}

	public String getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public double getOverdraftLimit() {
		return overdraftLimit;
	}

	public boolean setOverdraftLimit(double overdraftLimit) {
		// Case overdraft negative => false
		// Case not => Is not possible to set overdraft limit to less than the
		// absolute value of the current balance
		if (overdraftLimit < 0) {
			return false;
		} else {
			if (this.currentBalance >= 0) {
				this.overdraftLimit = overdraftLimit;
				return true;
			} else if (overdraftLimit < Math.abs(this.currentBalance)) {
				return false;
			} else {
				return true;
			}
		}

	}

	// Constructors
	public BankAccount() {
		this.accountNumber = BankAccount.nextId;
		this.accountHolder = "";
		this.currentBalance = 0;
		this.overdraftLimit = 0;
		BankAccount.nextId++;
	}

	public BankAccount(String accountHolder, double overdraftLimit) {
		this.accountHolder = accountHolder;
		this.overdraftLimit = overdraftLimit;
		this.accountNumber = BankAccount.nextId;
		this.currentBalance = 0;
		BankAccount.nextId++;
	}

	// Other Methods
	/**
	 * Deposit an amount in the bank account
	 * 
	 * @param amount
	 * @throws Exception
	 *             if amount is negative
	 */
	public void deposit(double amount) throws Exception {
		if (amount < 0) {
			throw new Exception("The amount deposited is negative");
		}

		this.currentBalance += amount;
	}

	/**
	 * Withdraw an amount from the bank account
	 * 
	 * @param amount
	 * @return true if the withdraw succeed, false otherwise
	 * @throws Exception
	 */
	public boolean withdraw(double amount) throws Exception {
		if (amount < 0) {
			throw new Exception("The withdrawal amount is negative");
		}

		double totalBalance;

		totalBalance = this.currentBalance + this.overdraftLimit;

		if (amount > totalBalance) {
			return false;
		}

		this.currentBalance -= amount;

		if (this.currentBalance < 0) {
			this.overdraftLimit += this.currentBalance;
		}

		return true;
	}

	/**
	 * Format pounds to display account summary
	 * 
	 * @param money
	 * @return
	 */
	private String formatMoney(double money) {
		if (money >= 0) {
			return java.util.Currency.getInstance("GBP").getSymbol(
					java.util.Locale.UK)
					+ String.format("%.2f", money);
		} else {
			money *= -1;
			return "-"
			+ java.util.Currency.getInstance("GBP").getSymbol(
					java.util.Locale.UK) + String.format("%.2f", money);
		}
	}

	@Override
	public String toString() {

		return "Account Summary:\n" + "\n" + "Account Number:"
				+ this.accountNumber + "\n" + "Account Holder:"
				+ this.accountHolder + "\n" + "CurrentBalance:"
				+ formatMoney(this.currentBalance) + "\n" + "Overdraft Limit:"
				+ formatMoney(this.overdraftLimit) + "\n";
	}

	@Override
	public boolean equals(Object obj) {
		BankAccount bankAccount = (BankAccount) obj;
		return bankAccount.getAccountNumber() == this.accountNumber;
	}
}
