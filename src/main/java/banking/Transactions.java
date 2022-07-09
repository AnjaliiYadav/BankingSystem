package banking;

public class Transactions extends AccountHolder implements TransactionInterface {

    private Long accountNumber;
    private Bank bank;

    public Transactions(Long accountNumber, Bank bank, int pin) throws Exception {
        super(bank.authenticateUser(accountNumber, pin) ? bank.getAccount(accountNumber).getPin() : 0);
        if (!bank.authenticateUser(accountNumber, pin))
            throw new Exception("Invalid Pin");
        this.accountNumber = accountNumber;
        this.bank = bank;
        System.out.println(" " + accountNumber + " " + bank + " " + pin);
    }

    @Override
    public double getBalance() {
        return bank.getBalance(accountNumber);
    }

    @Override
    public void credit(double amount) {
        bank.credit(accountNumber, amount);
    }

    @Override
    public boolean debit(double amount) {
        if (getBalance()>=amount)
        {
            bank.debit(accountNumber, amount);
            return true;
        }
        return false;
    }
}
