package banking;

public interface BankInterface {
    /**
     * @param company The company details.
     * @param pin authentication pin.
     * @param startingDeposits deposited amount.
     * @return The account number for the newly created account.
     */
    public Long openCommercialAccount(Company company, int pin, double startingDeposits);

    /**
     * @param person The person details.
     * @param pin authentication pin.
     * @param startingDeposits deposited amount.
     * @return The account number for the newly created account.
     */
    public Long openPersonalAccount(Person person, int pin, double startingDeposits);

    /**
     * @param accountNumber accountNumber The account number for the transaction.
     * @param pin
     * @return true if authentication was successful.
     */
    public boolean authenticateUser(Long accountNumber, int pin);

    /**
     * @param accountNumber accountNumber The account number for the checking balance.
     * @return The balance of the account.
     */
    public double getBalance(Long accountNumber);

    /**
     * @param accountNumber accountNumber The account number for the transaction.
     * @param amount The amount of money deposited.
     */
    public void credit(Long accountNumber, double amount);

    /**
     * @param accountNumber accountNumber The account number for the transaction.
     * @param amount The amount of money withdrawn.
     * @return true if amount could be withdrawn else false.
     */
    public boolean debit(Long accountNumber, double amount);
}
