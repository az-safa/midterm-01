package Midterm;

public class SavingsAccount extends Account {
    private double interestRate;
    private static final double MIN_BALANCE = 100.0;
    private static final double TRANSACTION_FEE = 2.00;

    public SavingsAccount(String accountNumber, String customerName, double initialBalance, double interestRate) {
        super(accountNumber, customerName, initialBalance);
        this.interestRate = interestRate;
    }

    @Override
    public void withdraw(double amount) {
        if (getBalance() - amount - TRANSACTION_FEE < MIN_BALANCE) {
            System.out.println("Cannot withdraw $" + amount + ". Minimum balance of $" + MIN_BALANCE + " must be maintained.");
            return;
        }
        setBalance(getBalance() - amount - TRANSACTION_FEE);
        logTransaction("WITHDRAWAL", amount);
        logTransaction("FEE", TRANSACTION_FEE);
    }

    public void applyInterest() {
        double interest = getBalance() * (interestRate / 100);
        setBalance(getBalance() + interest);
        logTransaction("INTEREST", interest);
        System.out.println("Interest applied: $" + interest);
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Savings Account");
        System.out.println("Interest Rate: " + interestRate + "%");
        System.out.println("Minimum Balance Requirement: $" + MIN_BALANCE);
    }
}
