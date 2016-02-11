import java.text.SimpleDateFormat;

public class Withdrawal extends Transaction {

	private boolean transactionAccepted;

	/**
	 * Constructor
	 * 
	 * @param amount
	 */
	public Withdrawal(double amount) {
		super(amount);
	}

	/**
	 * Methods
	 */

	@Override
	public boolean apply(TransactionalBankAccount account) {
		// Makes the transaction if nothing goes wrong
		try {
			transactionAccepted = account.withdraw(amount);
			account.setMostRecentTransaction(this);
			return transactionAccepted;
		} catch (Exception e) {
			// Print the log of the error
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return String.valueOf(f.format(getDate())) + " WITHDRAWAL "
				+ String.valueOf(getAmount());
	}

}
