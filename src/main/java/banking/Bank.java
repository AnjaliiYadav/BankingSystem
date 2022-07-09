package banking;

import java.util.LinkedHashMap;

public class Bank implements BankInterface {

    private final LinkedHashMap<Long, Account> accounts;

    public Bank() {
        accounts = new LinkedHashMap<>();
    }


    @Override
    public Long openCommercialAccount(Company company, int pin, double startingDeposits) {
        long accountNumber = accounts.size() + 1;
        Account commercialAccount = new CommercialAccount(company, accountNumber, pin, startingDeposits);
        accounts.put(accountNumber, commercialAccount);
        return accountNumber;
    }

    @Override
    public Long openPersonalAccount(Person person, int pin, double startingDeposits) {
        long accountNumber = accounts.size() + 1;
        Account personalAccount = new PersonalAccount(person, accountNumber, pin, startingDeposits);
        accounts.put(accountNumber, personalAccount);
        return accountNumber;
    }

    @Override
    public boolean authenticateUser(Long accountNumber, int pin) {
        Account account = getAccount(accountNumber);
        return account.getPin() == pin;
    }

    @Override
    public double getBalance(Long accountNumber) {
        Account account = getAccount(accountNumber);
        return account.getBalance();
    }

    @Override
    public void credit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        account.creditAccount(amount);
    }

    @Override
    public boolean debit(Long accountNumber, double amount) {
        Account account = getAccount(accountNumber);
        return account.debitAccount(amount);
    }

    Account getAccount(Long accountNumber) {
        return accounts.get(accountNumber);
    }
}
