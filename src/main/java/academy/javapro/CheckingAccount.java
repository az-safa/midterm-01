package Midterm;

/**
 * CheckingAccount class extending the abstract Account class.
 * Features overdraft protection and transaction fees.
 */

public class CheckingAccount extends Account {
    private double overdraftLimit;
    private static final double TRANSACTION_FEE = 1.50;

    /**
     * Constructor for CheckingAccount.
     *
     * @param accountNumber  The account number
     * @param customerName   The name of the account holder
     * @param initialBalance The initial balance
     * @param overdraftLimit The overdraft limit
     */
    public CheckingAccount(String accountNumber, String customerName, double initialBalance, double overdraftLimit) {
        super(accountNumber, customerName, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    /**
     * Withdraws an amount from the checking account.
     * Allows overdraft up to the specified limit.
     *
     * @param amount The amount to withdraw
     */
    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Withdrawal amount must be positive.");
            return;
        }

        double availableBalance = getBalance() + overdraftLimit;

        if (amount + TRANSACTION_FEE > availableBalance) {
            System.out.println("Withdrawal exceeds overdraft limit.");
            return;
        }

        // Deduct the amount and the transaction fee
        setBalance(getBalance() - amount - TRANSACTION_FEE);
        logTransaction("WITHDRAWAL", amount);
        logTransaction("FEE", TRANSACTION_FEE);

        if (getBalance() < 0) {
            System.out.println("Account is in overdraft. Current balance: $" + getBalance());
        } else {
            System.out.println("Withdrew $" + amount + " from checking account.");
        }
    }

    /**
     * Sets a new overdraft limit.
     *
     * @param overdraftLimit The new overdraft limit
     */
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        System.out.println("Overdraft limit updated to $" + overdraftLimit);
    }

    /**
     * Displays checking account-specific information.
     */
    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Account Type: Checking Account");
        System.out.println("Overdraft Limit: $" + String.format("%.2f", overdraftLimit));
        System.out.println("Transaction Fee: $" + String.format("%.2f", TRANSACTION_FEE));
    }
}
