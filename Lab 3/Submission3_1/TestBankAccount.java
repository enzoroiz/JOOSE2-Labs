import org.junit.Before;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import java.lang.reflect.Method;
import java.util.Currency;
import java.util.Locale;

/**
 * set of JUnit tests for Java lab 3
 * BankAccount class defined by
 * students. 
 *
 * I am using reflection to invoke methods
 * on BankAccount - this should prevent
 * Eclipse code completion from automatically
 * creating the empty methods in BankAccount
 * source code.
 *
 * @author Jeremy.Singer@glasgow.ac.uk
 */
public class TestBankAccount {

    /**
     * utility BankAccount instance 
     * for tests
     */
    private static BankAccount ba;

    /**
     * canonical '£' sign for UK currency
     */
    private static String POUND_SIGN;

    @Before
    public void setup() {
	// set up an empty BankAccount instance
        ba = new BankAccount();
        POUND_SIGN = Currency.getInstance("GBP").getSymbol(Locale.UK);
    }

    @After
    public void teardown() {
        ba = null;
    }

    @Test
    public void testSetAndGetName() throws Exception {
        String name = "jeremy singer";
        Method setAccountHolderMethod = Class.forName("BankAccount").getMethod("setAccountHolder", "".getClass());
        Method getAccountHolderMethod = Class.forName("BankAccount").getMethod("getAccountHolder");
        setAccountHolderMethod.invoke(ba, name);
        Object o = getAccountHolderMethod.invoke(ba);
        assertTrue("accountHolder getter and setter methods not working properly", o.equals(name));

    }

    @Test
    public void testSetAndPrintName() throws Exception {
        String name = "jeremy singer";
Method setAccountHolderMethod = Class.forName("BankAccount").getMethod("setAccountHolder", "".getClass());
        // ba.setAccountHolder(name);
        setAccountHolderMethod.invoke(ba, name);
        assertTrue("accountHolder not appearing properly in toString() output", ba.toString().contains(name));
    }


    @Test
    public void testDifferentInstances() {
        BankAccount ba2 = new BankAccount();
        assertFalse("equals() method is faulty (two distinct accounts should not be equal)", ba.equals(ba2));
    }

    @Test
    public void testEquals() {
        BankAccount ba2 = new BankAccount();
        assertTrue("equals() method is faulty (an account should be equal to itself)", ba.equals(ba));
        assertTrue("equals() method is faulty (an account should be equal to itself)", ba2.equals(ba2));
    }
        

    @Test
    public void testDifferentAccountNumbers() throws Exception {
        for (int i=0; i<100; i++) {
            BankAccount ba2 = new BankAccount();
            Method getAccountNumberMethod = Class.forName("BankAccount").getMethod("getAccountNumber");
            assertFalse("no two bank accounts should have the same account number", getAccountNumberMethod.invoke(ba).equals(getAccountNumberMethod.invoke(ba2)));
        }
    }

    @Test
    public void testMultipleDifferentAccountNumbers() throws Exception {
        int NUM_ACCOUNTS = 100;
        Method getAccountNumberMethod = Class.forName("BankAccount").getMethod("getAccountNumber");
        BankAccount [] accounts = new BankAccount[NUM_ACCOUNTS];
        for (int i=0; i<NUM_ACCOUNTS; i++) {
            accounts[i] = new BankAccount();
            for (int j=0; j <i; j++) {
                // loop over all previously created accounts
              assertFalse("no two bank accounts should have the same account number", getAccountNumberMethod.invoke(accounts[i]).equals(getAccountNumberMethod.invoke(accounts[j])));
            }
        }
    }
    
    // all following methods declared to throw Exception
    // since they call .deposit() and .withdraw() ...
    @Test
    public void testWithdrawal() throws Exception {
        double amount1 = 10.0;
        double amount2 = 11.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        depositMethod.invoke(ba, amount1);
        assertFalse("overdraftLimit check not working properly", ((boolean)(withdrawMethod.invoke(ba, amount2))));
    }

    @Test
    public void testWithdrawal2() throws Exception {
        double amount = 10.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        // ba.deposit(amount);
        depositMethod.invoke(ba, amount);
        assertTrue("overdraftLimit check not working properly (check should be <= not <", (boolean)(withdrawMethod.invoke(ba, amount)));
    }

    @Test
    public void testChangeOverdraft() throws Exception {
        double amount = 10.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        // ba.deposit(amount);
        depositMethod.invoke(ba, amount);
        // ba.setOverdraftLimit(amount)
        Method setOverdraftLimitMethod = Class.forName("BankAccount").getMethod("setOverdraftLimit", double.class);
        assertTrue((boolean)(setOverdraftLimitMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly", (boolean)(withdrawMethod.invoke(ba, amount)));
    }

    @Test
    public void testChangeOverdraft2() throws Exception {
        double amount = 10.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        Method setOverdraftLimitMethod = Class.forName("BankAccount").getMethod("setOverdraftLimit", double.class);

        // ba.deposit(amount);
        depositMethod.invoke(ba, amount);
        assertTrue((boolean)(setOverdraftLimitMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly", (boolean)(withdrawMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly (check for <, should be <=", (boolean)(withdrawMethod.invoke(ba,amount)));
    }

    @Test
    public void testChangeOverdraft2_2() throws Exception {
        double amount = 10.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        Method setOverdraftLimitMethod = Class.forName("BankAccount").getMethod("setOverdraftLimit", double.class);
        depositMethod.invoke(ba, amount);
        assertTrue("should be able to set overdraft to < Math.abs(currentBalance) when currentBalance>0", (boolean)(setOverdraftLimitMethod.invoke(ba, amount/2)));
    }

    @Test
    public void testChangeOverdraft3() throws Exception {
        double amount = 10.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        Method setOverdraftLimitMethod = Class.forName("BankAccount").getMethod("setOverdraftLimit", double.class);
        depositMethod.invoke(ba, amount);
        assertTrue((boolean)(setOverdraftLimitMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly", (boolean)(withdrawMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly (check for <, should be <=", (boolean)(withdrawMethod.invoke(ba, amount)));
        assertFalse("adjusted overdraftLimit not enforced properly", (boolean)(withdrawMethod.invoke(ba, amount)));
    }

    @Test
    public void testChangeOverdraft4() throws Exception {
        double amount = 10.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        Method setOverdraftLimitMethod = Class.forName("BankAccount").getMethod("setOverdraftLimit", double.class);
        depositMethod.invoke(ba, amount);
        assertTrue((boolean)(setOverdraftLimitMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly", (boolean)(withdrawMethod.invoke(ba, amount)));
        assertTrue("unable to adjust overdraftLimit properly (check for <, should be <=", (boolean)(withdrawMethod.invoke(ba, amount)));
        assertFalse("re-adjusteding overdraftLimit to less than current balance should not be allowed", (boolean)(setOverdraftLimitMethod.invoke(ba, 0)));
    }

    @Test(expected = Exception.class)
    public void testDepositNegativeAmount() throws Exception {
        double amount = -1.0;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        depositMethod.invoke(ba, amount);
    }

    @Test(expected = Exception.class)
    public void testWithdrawNegativeAmount() throws Exception {
        double amount = -1.0;
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        withdrawMethod.invoke(ba, amount);
    }

    // we want toString() to have neatly formatted balances
    // using '£' character
    @Test
    public void testToStringForPositiveBalances() throws Exception {
        double amount1 = 42.0;
        double amount2 = 0.01;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        depositMethod.invoke(ba, amount1);
        assertTrue("toString() does not format positive balance correctly", ba.toString().contains(POUND_SIGN + "42.00"));
        depositMethod.invoke(ba, amount2);
        assertTrue("toString() does not format positive balance correctly", ba.toString().contains(POUND_SIGN + "42.01"));
        withdrawMethod.invoke(ba, amount2);
        withdrawMethod.invoke(ba, amount2);
        assertTrue("toString() does not format positive balance correctly", ba.toString().contains(POUND_SIGN + "41.99"));
    }

    // we want toString() to have neatly formatted balances
    // using '£' character, with '-' sign _before_ currency sign...
    @Test
    public void testToStringForNegativeBalances() throws Exception {
        double amount1 = 10.0;
        double amount2 = 0.01;
        Method depositMethod = Class.forName("BankAccount").getMethod("deposit", double.class);
        Method withdrawMethod = Class.forName("BankAccount").getMethod("withdraw", double.class);
        Method setOverdraftLimitMethod = Class.forName("BankAccount").getMethod("setOverdraftLimit", double.class);
        assertTrue((boolean)(setOverdraftLimitMethod.invoke(ba, amount1*2)));
        withdrawMethod.invoke(ba, amount1);
        assertTrue("toString does not format negative balance correctly", ba.toString().contains("-" + POUND_SIGN + "10.00"));
        depositMethod.invoke(ba, amount2);
        assertTrue("toString does not format positive balance correctly", ba.toString().contains("-" + POUND_SIGN + "9.99"));
    }
    
}
