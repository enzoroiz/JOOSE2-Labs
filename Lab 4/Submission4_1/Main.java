import java.util.Calendar;

public class Main {

	public static void main(String[] args) {
		TransactionalBankAccount account = new TransactionalBankAccount(
				"Fred Bloggs, Esquire", 0.0);
		double amount = 42.0;
		Deposit d = new Deposit(amount);
		d.apply(account);
		Withdrawal w = new Withdrawal(amount);
		w.apply(account);
	}

}
