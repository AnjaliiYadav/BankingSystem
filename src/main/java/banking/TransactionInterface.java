package banking;

public interface TransactionInterface  {
    public double getBalance();
    public void credit(double amount);
    public boolean debit(double amount);
}
