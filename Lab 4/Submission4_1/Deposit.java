import java.text.SimpleDateFormat;

/**
 * Deposit class
 * 
 * @author enzoroiz
 */

public class Deposit extends Transaction {

	/**
	 * Constructor
	 * 
	 * @param amount
	 *            to be deposited
	 */
	public Deposit(double amount) {
		super(amount);
	}

	/**
	 * Methods
	 */

	@Override
	public boolean apply(TransactionalBankAccount account) {
		// Makes the transaction if nothing goes wrong
		try {
			account.deposit(amount);
			account.setMostRecentTransaction(this);
			return true;
		} catch (Exception e) {
			// Print the log of the error
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String toString() {
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return String.valueOf(f.format(getDate())) + " DEPOSIT "
				+ String.valueOf(getAmount());
	}

}
