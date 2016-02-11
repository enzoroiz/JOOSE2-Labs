/**
 * subclass of {@link BankAccount} that keeps track of the most recent
 * {@link Transaction} on this account
 * 
 * @author enzoroiz
 */
public class TransactionalBankAccount extends BankAccount {

	/**
	 * Instance fields
	 */
	Transaction mostRecentTransaction;

	/**
	 * Constructor
	 */
	public TransactionalBankAccount() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param account
	 *            holder
	 * @param account
	 *            limit
	 */
	public TransactionalBankAccount(String holder, double limit) {
		super(holder, limit);
	}

	/**
	 * Getter and setter
	 */
	public Transaction getMostRecentTransaction() {
		return mostRecentTransaction;
	}

	public void setMostRecentTransaction(Transaction mostRecentTransaction) {
		this.mostRecentTransaction = mostRecentTransaction;
	}

	/**
	 * Methods
	 */
	@Override
	public String toString() {
		try {
			return "Account Summary:\n" + super.toString()
					+ "Most Recent Transaction:\n"
					+ this.mostRecentTransaction.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "Account Summary:\n" + super.toString()
					+ "Most Recent Transaction:\n" + "None";
		}
	}

}