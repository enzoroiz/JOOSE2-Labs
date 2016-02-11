import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;

/**
 * set of JUnit tests for Java lab 4 TransactionalBankAccount class defined by
 * students
 * 
 * @author Jeremy.Singer@glasgow.ac.uk
 */
public class TestTransactionalBankAccount {

	private static TransactionalBankAccount account;
	private Method applyMethod;
	private Method getDateMethod;

	@Before
	public void setup() {
		// set up an empty BankAccount instance
		account = new TransactionalBankAccount("Fred Bloggs, Esquire", 0.0);
		// init methods for reflective calls
		try {
			applyMethod = Class.forName("Transaction").getMethod("apply",
					account.getClass());
			getDateMethod = Class.forName("Transaction").getMethod("getDate");
		} catch (ReflectiveOperationException e) {
			applyMethod = null;
			getDateMethod = null;
		}
	}

	@After
	public void teardown() {
		account = null;
	}

	/**
	 * check methods from parent BankAccount are still working ok
	 */
	@Test
	public void testInheritedMethodsWorkOK() {
		String name = "jeremy singer";
		account.setAccountHolder(name);
		assertTrue("accountHolder getter and setter not working", account
				.getAccountHolder().equals(name));

	}

	/**
	 * more checks on inherited fields and methods from BankAccount
	 */
	@Test
	public void testDifferentAccountNumbers() {
		for (int i = 0; i < 100; i++) {
			TransactionalBankAccount account2 = new TransactionalBankAccount();
			assertFalse(
					"no two bank accounts should have the same account number",
					account.getAccountNumber() == account2.getAccountNumber());
		}
	}

	/**
	 * check timestamping for transactions is working ok
	 */
	@Test
	public void timeStampTest() throws Exception {
		Deposit d = new Deposit(0.0);
		Withdrawal w = new Withdrawal(0.0);
		Calendar txTime = new GregorianCalendar();
		Calendar now = Calendar.getInstance();

		txTime.setTime((Date) (getDateMethod.invoke(d)));
		assertTrue("deposit day incorrect",
				now.get(Calendar.DAY_OF_YEAR) == txTime
						.get(Calendar.DAY_OF_YEAR));
		txTime.setTime((Date) (getDateMethod.invoke(w)));
		assertTrue("withdrawal day incorrect",
				now.get(Calendar.DAY_OF_YEAR) == txTime
						.get(Calendar.DAY_OF_YEAR)); /*
													 * caution - boundary
													 * condition - testing at
													 * midnight!!
													 */
	}

	/**
	 * this deposit should succeed, so return true
	 */
	@Test
	public void testGoodDeposit() throws Exception {
		double amount = 10.0;
		Deposit d = new Deposit(amount);
		Calendar depositTime;
		Calendar now;
		assertTrue("deposit positive amount should succeed",
				((Boolean) (applyMethod.invoke(d, account))).booleanValue());
		assertTrue("most recent transaction should be deposit",
				d.equals(account.getMostRecentTransaction()));
		depositTime = new GregorianCalendar();
		depositTime.setTime(account.getMostRecentTransaction().getDate());
		now = Calendar.getInstance();
		assertTrue("deposit year incorrect",
				now.get(Calendar.YEAR) == depositTime.get(Calendar.YEAR));
		assertTrue("deposit day incorrect",
				now.get(Calendar.DAY_OF_YEAR) == depositTime
						.get(Calendar.DAY_OF_YEAR));
		assertTrue("deposit time incorrect",
				now.get(Calendar.HOUR_OF_DAY) == depositTime
						.get(Calendar.HOUR_OF_DAY)); /*
													 * caution! watch for
													 * boundary conditions!
													 */
	}

	/**
	 * this deposit should fail, so return false
	 */
	@Test
	public void testBadDeposit() throws Exception {
		double amount = -99.0;
		Deposit d = new Deposit(amount);
		assertFalse("deposit negative amount should fail",
				((Boolean) (applyMethod.invoke(d, account))).booleanValue());
	}

	/**
	 * this withdrawal should succeed, so return true
	 */
	@Test
	public void testGoodWithdrawal() throws Exception {
		double amount = 10.0;
		Deposit d = new Deposit(amount);
		Withdrawal w = new Withdrawal(amount);
		assertTrue("deposit positive amount should succeed",
				((Boolean) (applyMethod.invoke(d, account))).booleanValue());
		assertTrue("most recent transaction should be deposit",
				d.equals(account.getMostRecentTransaction()));
		assertTrue("withdrawal of <= balance should succeed",
				((Boolean) applyMethod.invoke(w, account)).booleanValue());
		assertTrue("most recent transaction should be withdrawal",
				w.equals(account.getMostRecentTransaction()));
	}

	/**
	 * this withdrawal should fail, so return false
	 */
	@Test
	public void testBadWithdrawal() throws Exception {
		double amount = 10.0;
		Withdrawal w = new Withdrawal(amount);
		assertFalse("withdrawal of >= balance should fail",
				((Boolean) applyMethod.invoke(w, account)).booleanValue());
	}

	/**
	 * this withdrawal should fail, so return false
	 */
	@Test
	public void testBadWithdrawal2() throws Exception {
		double amount = 1.0;
		assertTrue(account.setOverdraftLimit(amount));
		double amount2 = 2.0;
		Withdrawal w = new Withdrawal(amount2);
		assertFalse("withdrawal of >= balance should fail",
				((Boolean) applyMethod.invoke(w, account)).booleanValue());
	}

	/**
	 * check toString is formatted properly, with timestamp
	 */
	@Test
	public void testDepositToString() {
		double amount = 42.0;
		Deposit d = new Deposit(amount);
		Calendar now = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH");
		assertTrue("deposit toString does not contain amount", d.toString()
				.contains((new Double(amount)).toString()));
		assertTrue("deposit toString does not contain year", d.toString()
				.contains((new Integer(now.get(Calendar.YEAR))).toString()));
		assertTrue("deposit toString does not have date formatted correctly", d
				.toString().contains(f.format(now.getTime())));
	}

	/**
	 * check toString is formatted properly, with timestamp
	 */
	@Test
	public void testWithdrawalToString() {
		double amount = 42.0;
		Withdrawal w = new Withdrawal(amount);
		Calendar now = Calendar.getInstance();
		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH");
		assertTrue("withdrawal toString does not contain amount", w.toString()
				.contains((new Double(amount)).toString()));
		assertTrue("withdrawal toString does not contain year", w.toString()
				.contains((new Integer(now.get(Calendar.YEAR))).toString()));
		assertTrue(
				"withdrawal toString does not have date formatted correctly", w
						.toString().contains(f.format(now.getTime())));
	}

	/**
	 * make sure toString doesn't throw NullPointerException if we call toString
	 * on a newly initialized TransactionalBankAccount with no recent
	 * transactions
	 */
	@Test
	public void testToStringOnTransactionalBankAccount() {
		String s = account.toString(); // may throw NullPtrExn ?
		assertTrue("toString() is empty", s.length() > 0);
	}

	/**
	 * check toString is formatted properly, with timestamp
	 */
	@Test
	public void testToStringOnTransactionalBankAccount2() throws Exception {
		Deposit d = new Deposit(5.0);
		applyMethod.invoke(d, account);
		Deposit d2 = new Deposit(5.0);
		applyMethod.invoke(d2, account);
		Calendar now = Calendar.getInstance();
		String s = account.toString();
		assertTrue("toString() is empty", s.length() > 0);

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH");

		assertTrue("DEPOSIT not recorded as recent transaction", account
				.toString().contains("DEPOSIT"));
		assertTrue("account toString does not contain year", account.toString()
				.contains((new Integer(now.get(Calendar.YEAR))).toString()));
		assertTrue(
				"account toString does not have transaction date formatted correctly",
				account.toString().contains(f.format(now.getTime())));

	}

	/**
	 * check toString is formatted properly, with timestamp
	 */
	@Test
	public void testToStringOnTransactionalBankAccount3() throws Exception {
		Deposit d = new Deposit(5.0);
		applyMethod.invoke(d, account);
		Withdrawal w = new Withdrawal(5.0);
		applyMethod.invoke(w, account);
		Calendar now = Calendar.getInstance();
		String s = account.toString();
		assertTrue("toString() is empty", s.length() > 0);

		SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH");

		assertTrue("WITHDRAWAL not recorded as recent transaction", account
				.toString().contains("WITHDRAWAL"));
		assertTrue("account toString does not contain year", account.toString()
				.contains((new Integer(now.get(Calendar.YEAR))).toString()));
		assertTrue(
				"account toString does not have transaction date formatted correctly",
				account.toString().contains(f.format(now.getTime())));

	}

	/**
	 * check toString is formatted properly, with inherited fields
	 */
	@Test
	public void testToStringOnTransactionalBankAccount4() {
		account.setAccountHolder("Mr. Bean");
		Deposit d = new Deposit(5.0);
		d.apply(account);

		String s = account.toString();
		assertTrue("toString() is empty", s.length() > 0);

		// check BankAccount inherited fields are still in toString output
		assertTrue("accountHolder not in toString",
				s.contains(account.getAccountHolder()));
	}

}
