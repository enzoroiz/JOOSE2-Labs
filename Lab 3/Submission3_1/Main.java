
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double amount = 10.0;
		BankAccount conta = new BankAccount();
		try {
			conta.deposit(amount);
			System.out.println(conta.toString());
			conta.setOverdraftLimit(amount);
			System.out.println(conta.toString());
			conta.withdraw(amount);
			System.out.println(conta.toString());
			conta.withdraw(amount);
			System.out.println(conta.toString());
			conta.setOverdraftLimit(0);
			System.out.println(conta.toString());
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

}
