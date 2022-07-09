package banking;

/***
 *Private Variables:<br>
 *
 * {@link #accountHolder}: AccountHolder<br>
 * {@link #accountNumber}: Long<br>
 * {@link #pin}: int<br>
 * {@link #balance}: double
 */
public abstract class Account {
    private final AccountHolder accountHolder;
    private final Long accountNumber;
    private final int pin;
    private double balance;

    public Account(AccountHolder accountHolder, Long accountNumber, int pin, double balance) {
        this.accountHolder = accountHolder;
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    /**
     * @return The {@link AccountHolder}.
     */
    public AccountHolder getAccountHolder() {
        return accountHolder;
    }


    public Long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public void creditAccount(double amount) {
        this.balance += amount;

    }

    public boolean debitAccount(double amount) {
        if (this.balance >= amount)
        {
            this.balance -= amount;
            return true;
        }

        return false;
    }

    public double getBalance() {
        return balance;
    }
}
